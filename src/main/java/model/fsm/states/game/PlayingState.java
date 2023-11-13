package model.fsm.states.game;

import engine.UIPanel;
import model.CanadaGame;
import model.GameObject;
import views.InGameView;

import javax.swing.*;

public class PlayingState extends GameState {

    public PlayingState(CanadaGame game, UIPanel ui) {

        super(game, ui);
    }

    @Override
    public void tick() {

        game.update();

        // Pendant la partie on met à jour l'interface à chaque frame
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
