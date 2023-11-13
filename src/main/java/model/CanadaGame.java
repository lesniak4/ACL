package model;

import engine.Cmd;
import engine.IGame;
import engine.IGameController;
import engine.UIPanel;
import model.fsm.ICondition;
import model.fsm.StateMachine;
import model.fsm.states.game.EndMenuState;
import model.fsm.states.game.MainMenuState;
import model.fsm.states.game.PauseState;
import model.fsm.states.game.PlayingState;
import model.world.HexLayout;
import model.world.World;
import utils.Vector2;
import views.InGameView;

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

	public enum ButtonId{

		PLAY,
		EXIT,
		MAIN_MENU,
		NONE
	}

	private CanadaPainter painter;
	private CanadaPhysics physics;
	private IGameController controller;
	private StateMachine stateMachine;
	private ButtonId lastButtonPressed;
	private Cmd lastKeyPressed;

	private List<GameObject> gameObjects;
	private Vector2 cameraPosition;
	private double startTime;
	private double maxTime;
	private boolean hasKey;
	private boolean playerWin;
	private boolean playerLose;

	private int niveauActuel;
	private final int maxLevel = 2;

	private int score;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public CanadaGame(String source, CanadaPainter painter, CanadaPhysics physics, IGameController controller, double maxTime) {
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

		this.stateMachine = new StateMachine();

		this.playerLose = false;
		this.gameObjects = new ArrayList<>();
		this.startTime = System.currentTimeMillis();
		this.maxTime = maxTime;

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

		playing.addView(new InGameView(this));

		ICondition clickOnPlayButton = () -> lastButtonPressed == ButtonId.PLAY;
		ICondition clickOnExitButton = () -> lastButtonPressed == ButtonId.EXIT;
		ICondition clickOnMainMenuButton = () -> lastButtonPressed == ButtonId.MAIN_MENU;
		ICondition pressPauseCommand = () -> lastKeyPressed == Cmd.PAUSE;
		//ICondition gameFinished = () -> isFinished();

		stateMachine.addTransition(mainMenu, playing, clickOnPlayButton);
		stateMachine.addTransition(playing, pause, pressPauseCommand);
		stateMachine.addTransition(pause, playing, clickOnPlayButton);
		stateMachine.addTransition(pause, mainMenu, clickOnMainMenuButton);
		//stateMachine.addTransition(playing, endMenu, gameFinished);
		stateMachine.addTransition(endMenu, mainMenu, clickOnMainMenuButton);

		stateMachine.setState(playing);
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

	public void incrScore(){ this.score++; }

	public void resetLastPlayerInputs(){

		lastButtonPressed = ButtonId.NONE;
		lastKeyPressed = Cmd.NONE;
	}

	/**
	 * charge le niveau suivant
	 */
	public void loadNextLevel(){

		/* on réinitialise les listes d'objets connus */
		if (!this.gameObjects.isEmpty()) {
			gameObjects.clear();
			this.physics.reset();
		}

		this.niveauActuel++;

		if(this.niveauActuel <= maxLevel) {
			if (this.niveauActuel != 1) {
				this.maxTime += 30;
			}
			this.hasKey = false;
			this.playerWin = false;

			World world = new World(this, this.painter, this.physics);
			gameObjects.addAll(world.buildWorld("/map" + this.niveauActuel + ".txt", HexLayout.pointy));

			GameObject player = GameObjectFactory.getInstance().createPlayerObject(this, 180, 180, painter, controller, physics);
			world.createRandomMonsters(5, gameObjects, player);
			gameObjects.add(player);
			this.setCameraPosition(player.getPosition());
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {

		long currentTime = (System.currentTimeMillis() - (long)startTime);
		long timeRemaining = ((long)maxTime * 1000) - currentTime;
		if((timeRemaining) % 2000 == 0){
			System.out.println(timeRemaining / 1000 + " secondes restantes !");
		}
		return niveauActuel > maxLevel || playerLose || timeRemaining <= 0;
	}

	public Vector2 getCameraPosition() {
		return this.cameraPosition;
	}

	public void setCameraPosition(Vector2 cameraPosition) {
		this.cameraPosition = cameraPosition;
	}

}
