import model.CanadaPainter;
import engine.GameEngineGraphical;
import model.CanadaController;
import model.CanadaGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		CanadaGame game = new CanadaGame("helpFilePacman.txt");
		CanadaPainter painter = new CanadaPainter();
		CanadaController controller = new CanadaController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}
