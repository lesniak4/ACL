package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

import engine.Cmd;
import engine.IGameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class CanadaController implements IGameController {

	private static final int Q = 0;
	private static final int Z = 1;
	private static final int D = 2;
	private static final int S = 3;

	private Boolean[] keyPressed;


	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public CanadaController() {
		this.keyPressed = new Boolean[]{false,false,false,false};
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 *
	 * @return commande faite par le joueur
	 */
	public LinkedList<Cmd> getCommands() {
		LinkedList<Cmd> commandList = new LinkedList();

		if(keyPressed[Q])
			commandList.add(Cmd.LEFT);
		if(keyPressed[Z])
			commandList.add(Cmd.UP);
		if(keyPressed[D])
			commandList.add(Cmd.RIGHT);
		if(keyPressed[S])
			commandList.add(Cmd.DOWN);

		return commandList;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		char key = Character.toUpperCase(e.getKeyChar());
		switch(key) {
			case 'Q': // si on appuie sur 'q'
				this.keyPressed[Q] = true;
				break;
			case 'Z': // si on appuie sur 'z'
				this.keyPressed[Z] = true;
				break;
			case 'D': // si on appuie sur 'd'
				this.keyPressed[D] = true;
				break;
			case 'S': // si on appuie sur 's'
				this.keyPressed[S] = true;
				break;
			default: // Si une autre touche est appuyée, on l'ignore
				break;
		}
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches relachées
	 */
	public void keyReleased(KeyEvent e) {
		char key = Character.toUpperCase(e.getKeyChar());
		switch(key) {
			case 'Q': // si on relâche 'q'
				this.keyPressed[Q] = false;
				break;
			case 'Z': // si on relâche 'z'
				this.keyPressed[Z] = false;
				break;
			case 'D': // si on relâche 'd'
				this.keyPressed[D] = false;
				break;
			case 'S': // si on relâche 's'
				this.keyPressed[S] = false;
				break;
			default: // Si une autre touche est appuyée, on l'ignore
				break;
		}
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}
