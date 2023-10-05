package model;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import engine.Cmd;
import engine.IGameController;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class CanadaController implements IGameController {

	/**
	 * commande en cours
	 */
	private LinkedList<Cmd> commandesEnCours;

	private Cmd lastPressed;
	private Cmd lastReleased;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public CanadaController() {
		this.commandesEnCours = new LinkedList<>();
		this.lastPressed = Cmd.IDLE;
		this.lastReleased = Cmd.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 *
	 * @return commande faite par le joueur
	 */
	public LinkedList<Cmd> getCommands() {
		return commandesEnCours;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		char key = Character.toUpperCase(e.getKeyChar());
		switch(key) {
			case 'Q': // si on appuie sur 'q'
				if(lastPressed != Cmd.LEFT) {
					this.commandesEnCours.add(Cmd.LEFT);
					lastPressed = Cmd.LEFT;
				}
				break;
			case 'Z': // si on appuie sur 'z'
				if(lastPressed != Cmd.UP) {
					this.commandesEnCours.add(Cmd.UP);
					lastPressed = Cmd.UP;
				}
				break;
			case 'D': // si on appuie sur 'd'
				if(lastPressed != Cmd.RIGHT) {
					this.commandesEnCours.add(Cmd.RIGHT);
					lastPressed = Cmd.RIGHT;
				}
				break;
			case 'S': // si on appuie sur 's'
				if(lastPressed != Cmd.DOWN) {
					this.commandesEnCours.add(Cmd.DOWN);
					lastPressed = Cmd.DOWN;
				}
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
				if(lastReleased != Cmd.STOP_LEFT) {
					this.commandesEnCours.add(Cmd.STOP_LEFT);
					lastReleased = Cmd.STOP_LEFT;
				}
				break;
			case 'Z': // si on relâche 'z'
				if(lastReleased != Cmd.STOP_UP) {
					this.commandesEnCours.add(Cmd.STOP_UP);
					lastReleased = Cmd.STOP_UP;
				}
				break;
			case 'D': // si on relâche 'd'
				if(lastReleased != Cmd.STOP_RIGHT) {
					this.commandesEnCours.add(Cmd.STOP_RIGHT);
					lastReleased = Cmd.STOP_RIGHT;
				}
				break;
			case 'S': // si on relâche 's'
				if(lastReleased != Cmd.STOP_DOWN) {
					this.commandesEnCours.add(Cmd.STOP_DOWN);
					lastReleased = Cmd.STOP_DOWN;
				}
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
