package model;

import engine.Cmd;
import engine.IGame;
import engine.IGameController;
import engine.UIPanel;
import model.components.player.skills.PlayerSkillsShopComponent;
import model.fsm.ICondition;
import model.fsm.StateMachine;
import model.fsm.states.game.*;
import model.world.HexLayout;
import model.world.World;
import utils.GameConfig;
import utils.ScoreSaver;
import utils.Vector2;
import views.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class CanadaGame implements IGame {

	private CanadaPainter painter;
	private CanadaPhysics physics;

	private ScoreSaver scoreSaver;
	private IGameController controller;
	private StateMachine stateMachine;
	private ButtonId lastButtonPressed;
	private Cmd lastKeyPressed;

	private List<GameObject> gameObjects;
	private Vector2 cameraPosition;
	private boolean hasKey;
	private boolean playerWin;
	private boolean playerLose;

	private int niveauActuel;
	private final int maxLevel = 2;

	private int score;

	private PlayerSkillsShopComponent skills;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public CanadaGame(String source, CanadaPainter painter, CanadaPhysics physics, IGameController controller) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}

		this.painter = painter;
		this.physics = physics;
		this.controller = controller;
		this.scoreSaver = new ScoreSaver();

		this.stateMachine = new StateMachine();
	}

	public void initGame(){
		this.playerLose = false;
		this.gameObjects = new ArrayList<>();

		this.niveauActuel = 0;
		this.score = 0;

		this.loadNextLevel();
	}

	@Override
	public void init(UIPanel ui) {
		initStateMachine(ui);
	}

	public void initStateMachine(UIPanel ui) {

		MainMenuState mainMenu = new MainMenuState(this, ui);
		PlayingState playing = new PlayingState(this, ui);
		PauseState pause = new PauseState(this, ui);
		EndMenuState endMenu = new EndMenuState(this, ui);
		ExitState exit = new ExitState(this, ui);
		LaunchGameState launchGame = new LaunchGameState(this,ui);
		NextLevelState nextLevel = new NextLevelState(this, ui);

		ScoreView igView = new ScoreView(this);

		mainMenu.addView(new MenuView(this));
		playing.addView(igView);
		playing.addView(new SkillsView(this));
		launchGame.addView(new LaunchGameView(this));
		pause.addView(new PauseView(this));
		endMenu.addView(new EndMenuView(this));

		ICondition clickOnPlayButton = () -> lastButtonPressed == ButtonId.PLAY;
		ICondition clickOnExitButton = () -> lastButtonPressed == ButtonId.EXIT;
		ICondition clickOnMainMenuButton = () -> lastButtonPressed == ButtonId.MAIN_MENU;
		ICondition pressPauseCommand = () -> lastKeyPressed == Cmd.PAUSE;
		ICondition gameFinished = () -> isFinished();
		ICondition levelFinished = () -> !isFinished() && hasPlayerWon();

		stateMachine.addTransition(mainMenu, launchGame, clickOnPlayButton);
		stateMachine.addTransition(launchGame, playing, () -> true);
		stateMachine.addTransition(mainMenu, exit, clickOnExitButton);

		stateMachine.addTransition(playing, pause, pressPauseCommand);
		stateMachine.addTransition(playing, endMenu, gameFinished);
		stateMachine.addTransition(playing, nextLevel, levelFinished);
		stateMachine.addTransition(nextLevel, playing, () -> true);

		stateMachine.addTransition(pause, playing, clickOnPlayButton);
		stateMachine.addTransition(pause, mainMenu, clickOnMainMenuButton);
		stateMachine.addTransition(pause, exit, clickOnExitButton);

		stateMachine.addTransition(endMenu, mainMenu, clickOnMainMenuButton);
		stateMachine.addTransition(endMenu, launchGame, clickOnPlayButton);
		stateMachine.addTransition(endMenu, exit, clickOnExitButton);

		stateMachine.setState(mainMenu);
	}

	/**
	 * faire evoluer le jeu
	 *
	 */
	@Override
	public void evolve() {

		stateMachine.tick();
	}

	public void update(){

		painter.clearDrawQueue();
		for(GameObject obj : gameObjects){
			obj.update();
		}
		painter.setCameraPosition(this.cameraPosition);

	}

	@Override
	public boolean hasPlayerWon(){
		return this.playerWin;
	}

	@Override
	public boolean hasPlayerLost(){return this.playerLose;}

	@Override
	public int getScore() {
		return this.score;
	}

	public boolean playerOwnsKey() {return this.hasKey;}

	public void setHasKey(boolean hasKey) {this.hasKey = hasKey;}

	public void setPlayerWin(boolean playerWin){
		this.playerWin = playerWin;
	}

	public void setPlayerLose(boolean playerLose) {this.playerLose = playerLose;}

	public void removeGameObject(GameObject obj){
		gameObjects.remove(obj);
	}

	public void incrScore(int value){ this.score+=value; }

	public void decrScore(int value){ this.score-=value; }

	public void setLastButtonPressed(ButtonId id){
		lastButtonPressed = id;
	}

	public void setLastKeyPressed(Cmd lastKeyPressed) {
		this.lastKeyPressed = lastKeyPressed;
	}

	public void updateMaxScore(){
		if(score > getMaxScore()){
			scoreSaver.setMaxScore(score);
			scoreSaver.writeScoreFile();
		}
	}

	public int getMaxScore(){
		return scoreSaver.getMaxScore();
	}

	public void resetLastPlayerInputs(){

		lastButtonPressed = ButtonId.NONE;
		lastKeyPressed = Cmd.NONE;
	}

	/**
	 * charge le niveau suivant
	 */
	public void loadNextLevel(){

		GameConfig gc = GameConfig.getInstance();

		/* on réinitialise les listes d'objets connus */
		if (!this.gameObjects.isEmpty()) {
			gameObjects.clear();
		}

		this.physics.reset();

		this.niveauActuel++;

		if(this.niveauActuel <= maxLevel) {
			this.hasKey = false;
			this.playerWin = false;

			World world = new World(this, this.painter, this.physics);
			gameObjects.addAll(world.buildWorld("/map" + this.niveauActuel + ".txt", HexLayout.pointy));

			GameObject player = GameObjectFactory.getInstance().createPlayerObject(this, 180, 180, painter, controller, physics);
			world.createRandomMonsters(gc.getMonsterNb(), gameObjects, player);
			gameObjects.add(player);
			this.setCameraPosition(player.getPosition());

			this.skills = player.getComponent(PlayerSkillsShopComponent.class);
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {

		return niveauActuel > maxLevel || playerLose;
	}

	public Vector2 getCameraPosition() {
		return this.cameraPosition;
	}

	public void setCameraPosition(Vector2 cameraPosition) {
		this.cameraPosition = cameraPosition;
	}

	public PlayerSkillsShopComponent getSkills(){
		return this.skills;
	}
}
