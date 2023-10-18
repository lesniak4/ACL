package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engine.IGame;
import model.world.HexLayout;
import model.world.World;
import utils.Vector2;

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

	private List<GameObject> gameObjects;
	private Vector2 cameraPosition;
	private double startTime;
	private double maxTime;
	private boolean playerWin;

	private int niveauActuel;

	private int score;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public CanadaGame(String source, CanadaPainter painter, CanadaPhysics physics, CanadaController controller, double maxTime) {
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
		this.gameObjects = new ArrayList<>();

		this.playerWin = false;
		this.startTime = System.currentTimeMillis();
		this.maxTime = maxTime;

		this.niveauActuel = 1;
		this.score = 0;

		World world = new World(this, this.painter, this.physics);
		gameObjects.addAll(world.buildWorld("/map.txt", HexLayout.pointy));

		GameObject player = GameObjectFactory.getInstance().createPlayerObject(this,20,20, painter, controller, physics);
		gameObjects.add(player);
		this.setCameraPosition(player.getPosition());
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
		painter.setCameraPosition(this.cameraPosition);
	}

	@Override
	public boolean hasPlayerWon(){
		return this.playerWin;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	public void setPlayerWin(boolean playerWin){
		this.playerWin = playerWin;
	}

	public void removeGameObject(GameObject obj){
		gameObjects.remove(obj);
	}

	public void incrScore(){ this.score++; }

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

	public Vector2 getCameraPosition() {
		return this.cameraPosition;
	}

	public void setCameraPosition(Vector2 cameraPosition) {
		this.cameraPosition = cameraPosition;
	}

}
