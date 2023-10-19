package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engine.IGame;
import engine.IGameController;
import model.world.HexLayout;
import model.world.World;

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
	private IGameController controller;

	private List<GameObject> gameObjects;
	private double startTime;
	private double maxTime;
	private boolean hasKey;
	private boolean playerWin;

	private int niveauActuel;

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

		this.gameObjects = new ArrayList<>();
		this.startTime = System.currentTimeMillis();
		this.maxTime = maxTime;

		this.niveauActuel = 0;
		this.score = 0;

		this.loadNextLevel();
	}

	/**
	 * faire evoluer le jeu
	 *
	 * @param dt
	 */
	@Override
	public void evolve(double dt) {

		painter.clearDrawQueue();
		for(GameObject obj : gameObjects){
			obj.update(dt);
		}
	}

	@Override
	public boolean hasPlayerWon(){
		return this.playerWin;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	public boolean playerOwnsKey() {return this.hasKey;}

	public void setHasKey(boolean hasKey) {this.hasKey = hasKey;}

	public void setPlayerWin(boolean playerWin){
		this.playerWin = playerWin;
	}

	public void removeGameObject(GameObject obj){
		gameObjects.remove(obj);
	}

	public void incrScore(){ this.score++; }

	/**
	 * charge le niveau suivant
	 */
	public void loadNextLevel(){

		/* on réinitialise les listes d'objets connus */
		if (!this.gameObjects.isEmpty()) {
			gameObjects.clear();
			this.physics.reset();
		}

		this.niveauActuel += 1;
		if (this.niveauActuel!=1) {this.maxTime += 30;}
		this.hasKey = false;
		this.playerWin = false;

		World world = new World(this, this.painter, this.physics);
		gameObjects.addAll(world.buildWorld("/map" + this.niveauActuel + ".txt", HexLayout.pointy));

		GameObject player = GameObjectFactory.getInstance().createPlayerObject(this,20,20, painter, controller, physics);
		gameObjects.add(player);
		GameObject monster = GameObjectFactory.getInstance().createMonsterObject(this, 35,35, painter, physics);
		gameObjects.add(monster);
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
		return playerWin || timeRemaining <= 0;
	}

}
