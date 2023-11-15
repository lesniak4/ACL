package engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         un jeu qui peut evoluer (avant de se terminer) sur un plateau width x
 *         height
 */
public interface IGame {

	public void init(UIPanel ui);

	/**
	 * methode qui contient l'evolution du jeu
	 */
	public void evolve(float dt);

	public boolean hasPlayerWon();

	public boolean hasPlayerLost();

	public int getScore();

	public void loadNextLevel();

	/**
	 * @return true si et seulement si le jeu est fini
	 */
	public boolean isFinished();

}
