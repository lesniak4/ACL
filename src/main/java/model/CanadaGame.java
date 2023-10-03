package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import engine.Cmd;
import engine.IGame;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class CanadaGame implements IGame {

	private List<GameObject> gameObjects;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public CanadaGame(String source) {
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

		this.gameObjects = new ArrayList<>();
	}

	/**
	 * faire evoluer le jeu
	 *
	 */
	@Override
	public void evolve(double dt) {

		for(GameObject obj : gameObjects){
			obj.update(dt);
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

}
