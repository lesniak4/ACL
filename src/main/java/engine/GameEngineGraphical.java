package engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	private int FPS = 60;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		long lastTime = System.nanoTime();
		double nanoSecondsPerTick = 1000000000D / FPS;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double dt = 0;

		// boucle de game
		while (!this.game.isFinished()) {

			long now = System.nanoTime();
			dt += (now - lastTime) / nanoSecondsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			// demande controle utilisateur
			Cmd c = this.gameController.getCommand();

			while (dt >= 1) {
				ticks++;
				// fait evoluer le game
				this.game.evolve(c, dt);
				dt -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				// affiche le game
				this.gui.paint();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				frames = 0;
				ticks = 0;
			}
		}
	}

}
