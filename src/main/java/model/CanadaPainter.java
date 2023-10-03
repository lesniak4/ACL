package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;

import engine.IGamePainter;
import model.components.GraphicsComponent;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class CanadaPainter implements IGamePainter {

	/**
	 * la taille de la fenêtre
	 */
	protected static final int WIDTH = 500;
	protected static final int HEIGHT = 500;

	protected Collection<GraphicsComponent> drawQueue;

	/**
	 * appelle constructeur parent
	 */
	public CanadaPainter() {

		this.drawQueue = new ArrayList<>();
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		for(GraphicsComponent g : drawQueue) {
			crayon.setColor(g.getColor());
			crayon.fill(g.getShape());
		}
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	public void addToDrawQueue(GraphicsComponent graphics){

		drawQueue.add(graphics);
	}

	public void clearDrawQueue(){
		drawQueue.clear();
	}

}
