package model.fsm.states.game;

import engine.UIPanel;
import model.CanadaGame;

public class EndMenuState extends GameState{

    public EndMenuState(CanadaGame game, UIPanel ui) {
        super(game, ui);
    }

    @Override
    public void tick() {

    }

    @Override
    public void onEnter() {
        super.onEnter();
        notifyViews();
    }

    @Override
    public void onExit() {

        super.onExit();
    }
}
