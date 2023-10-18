package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import engine.IGamePainter;
import model.components.rendering.GraphicsComponent;
import utils.Vector2;

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
	protected static final int WIDTH = 700;
	protected static final int HEIGHT = (int)(Math.sqrt(3f) * WIDTH * 0.5f );

	protected Collection<GraphicsComponent> drawQueue;

	protected Vector2 cameraPosition;

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
			Vector2 pos = Vector2.worldToScreenIso(new Vector2(g.getGameObject().getPosition().X() - cameraPosition.X(),
					g.getGameObject().getPosition().Y() - cameraPosition.Y()));
			if(g.getSprite() != null) {
				int x = (int) pos.X() - (g.getSprite().getWidth(null) - WIDTH) / 2;
				int y = (int) pos.Y() - (g.getSprite().getHeight(null) - 64) + HEIGHT / 2;
				crayon.drawImage(g.getSprite(), x, y, null);
			}else{
				crayon.setColor(g.getColor());
				crayon.fill(g.getShape());
			}
		}

		// Pour afficher les hitboxes
		/*
		for(GraphicsComponent g : drawQueue) {
			ColliderComponent collider = g.getGameObject().getComponent(ColliderComponent.class);
			if(collider != null){
				crayon.setColor(Color.red);
				crayon.draw(new Ellipse2D.Double(g.getGameObject().getX()-collider.getRadius(), g.getGameObject().getY()-collider.getRadius(),collider.getRadius()*2, collider.getRadius()*2));
			}
		}
		 */
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

	public void setCameraPosition(Vector2 cameraPosition){
		this.cameraPosition = cameraPosition;
	}

}
