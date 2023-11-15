package model.fsm.states.game;

import engine.UIPanel;
import model.CanadaGame;

public class PauseState extends GameState {

    private double pausedTime;

    public PauseState(CanadaGame game, UIPanel ui) {
        super(game, ui);
    }

    @Override
    public void tick(float dt) {
    }

    @Override
    public void onEnter() {
        super.onEnter();
        pausedTime = System.currentTimeMillis();
    }

    @Override
    public void onExit() {
        double timePlayed = pausedTime - game.getStartTime();
        game.setStartTime(System.currentTimeMillis() - timePlayed);
        super.onExit();
    }
}
