package engine;

import utils.GameConfig;

import javax.swing.*;
import java.awt.*;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * interface graphique avec son controller et son afficheur
 *
 */
public class GraphicalInterface  {

	/**
	 * le Panel pour l'afficheur
	 */
	private DrawingPanel gamePanel;
	private UIPanel uiPanel;

	/**
	 * la construction de l'interface graphique: JFrame avec panel pour le game
	 * 
	 * @param gamePainter l'afficheur a utiliser dans le moteur
	 * @param gameController l'afficheur a utiliser dans le moteur
	 * 
	 */
	public GraphicalInterface(IGamePainter gamePainter, IGameController gameController){
		GameConfig gc = GameConfig.getInstance();
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setPreferredSize(new Dimension(gc.getWinWidth(), gc.getWinHeight()));

		// création du panel pour l'interface
		this.uiPanel = new UIPanel(gc.getWinWidth(), gc.getWinHeight());
		f.getLayeredPane().add(uiPanel, JLayeredPane.PALETTE_LAYER);

		// attacher le panel contenant l'afficheur du game
		this.gamePanel = new DrawingPanel(gamePainter);
		f.setContentPane(gamePanel);

		// attacher controller au panel du game
		gamePanel.addKeyListener(gameController);
		
		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}
	
	/**
	 * mise a jour du dessin
	 */
	public void paint() {
		this.gamePanel.drawGame();
	}

	public UIPanel getUIPanel(){
		return this.uiPanel;
	}
	
}
