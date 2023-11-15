package model.fsm.states.game;

import engine.UIPanel;
import model.CanadaGame;

public class ExitState extends GameState{

    public ExitState(CanadaGame game, UIPanel ui) {
        super(game, ui);
    }

    @Override
    public void tick(float dt) {

    }

    @Override
    public void onEnter() {
        super.onEnter();
        System.exit(0);
    }

    @Override
    public void onExit() {

        super.onExit();
    }
}
