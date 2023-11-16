package model.fsm.states.game;

import engine.UIPanel;
import model.CanadaGame;

public class LaunchGameState extends GameState{

    public LaunchGameState(CanadaGame game, UIPanel ui) {
        super(game, ui);
    }

    @Override
    public void tick() {
    }

    @Override
    public void onEnter() {
        super.onEnter();
        game.initGame();
    }

    @Override
    public void onExit() {
        super.onExit();
    }
}
