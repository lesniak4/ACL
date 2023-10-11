package model;

import java.awt.event.KeyEvent;
import java.util.*;

import engine.Cmd;
import engine.IGameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class CanadaController implements IGameController {

	private Set<Cmd> keysPressed;

	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public CanadaController() {
		this.keysPressed = new HashSet<>();
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 *
	 * @return commande faite par le joueur
	 */
	public Set<Cmd> getCommands() {
		return this.keysPressed;
	}

	/**
	 * Add commands to keyPresses
	 *
	 * @param keysPressed commands to add
	 */
	public void setKeysPressed(Cmd... keysPressed) {
		for(Cmd cmd : keysPressed)
			this.keysPressed.add(cmd);
	}

	/**
	 * Remove commands to keyPresses
	 *
	 * @param keysPressed commands to remove
	 */
	public void removeCommands(Cmd... keysPressed) {
		for(Cmd cmd : keysPressed)
			this.keysPressed.remove(cmd);
	}
	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_Q: // si on appuie sur 'q'
				keysPressed.add(Cmd.LEFT);
				break;
			case KeyEvent.VK_Z: // si on appuie sur 'z'
				keysPressed.add(Cmd.UP);
				break;
			case KeyEvent.VK_D: // si on appuie sur 'd'
				keysPressed.add(Cmd.RIGHT);
				break;
			case KeyEvent.VK_S: // si on appuie sur 's'
				keysPressed.add(Cmd.DOWN);
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
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_Q: // si on appuie sur 'q'
				keysPressed.remove(Cmd.LEFT);
				break;
			case KeyEvent.VK_Z: // si on appuie sur 'z'
				keysPressed.remove(Cmd.UP);
				break;
			case KeyEvent.VK_D: // si on appuie sur 'd'
				keysPressed.remove(Cmd.RIGHT);
				break;
			case KeyEvent.VK_S: // si on appuie sur 's'
				keysPressed.remove(Cmd.DOWN);
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
