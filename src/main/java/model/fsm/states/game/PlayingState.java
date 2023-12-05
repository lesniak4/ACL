package model.fsm.states.game;

import engine.UIPanel;
import model.CanadaGame;
import model.GameObject;
import views.UIView;

import java.util.ArrayList;
import java.util.Collection;

public class PlayingState extends GameState {

    public PlayingState(CanadaGame game, UIPanel ui) {
        super(game, ui);
    }

    @Override
    public void tick() {

        game.update();

        // Pendant la partie on met à jour l'interface qui en a besoin à chaque frame
        notifyViews();
    }

    @Override
    public void onEnter() {
        super.onEnter();
    }

    @Override
    public void onExit() {
        super.onExit();
    }
}
