import model.CanadaPainter;
import engine.GameEngineGraphical;
import model.CanadaController;
import model.CanadaGame;
import model.CanadaPhysics;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		CanadaPainter painter = new CanadaPainter();
		CanadaController controller = new CanadaController();
		CanadaPhysics physics = new CanadaPhysics();
		CanadaGame game = new CanadaGame("helpFileCanadaCamp.txt", painter, physics, controller);


		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller, physics);
		engine.run();
	}

}
