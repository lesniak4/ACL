package model.fsm.states.game;

import engine.UIPanel;
import model.CanadaGame;

public class NextLevelState extends GameState{

    public NextLevelState(CanadaGame game, UIPanel ui) {
        super(game, ui);
    }

    @Override
    public void tick(float dt) {
    }

    @Override
    public void onEnter() {
        super.onEnter();
        game.loadNextLevel();
    }

    @Override
    public void onExit() {
        super.onExit();
    }
}
