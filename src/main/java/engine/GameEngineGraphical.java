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
	private IGame game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private IGamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private IGameController gameController;

	private IGamePhysics gamePhysics;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

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
	public GameEngineGraphical(IGame game, IGamePainter gamePainter, IGameController gameController, IGamePhysics gamePhysics) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
		this.gamePhysics = gamePhysics;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		long lastTime = System.nanoTime();
		final int FPS = 60;
		double nanoSecondsPerTick = 1000000000D / FPS;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		float dt = 0;

		// boucle de game
		while (!this.game.isFinished()) {

			long now = System.nanoTime();
			dt += (now - lastTime) / nanoSecondsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while (dt >= 1) {
				ticks++;
				// fait evoluer le game
				this.game.evolve(dt);
				this.gamePhysics.updatePhysics(dt);
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
				//System.out.println(ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
		}
	}

}
