package model.fsm.states.game;

import engine.UIPanel;
import model.CanadaGame;
import model.fsm.State;
import views.UIView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public abstract class GameState extends State {

    protected CanadaGame game;
    protected UIPanel ui;
    protected ArrayList<UIView> views;

    public GameState(CanadaGame game, UIPanel ui){

        this.game = game;
        this.ui = ui;
        this.views = new ArrayList<>();
    }

    @Override
    public void onEnter() {

        game.resetLastPlayerInputs();
        for(UIView view : views){
            view.setVisible(true);
        }
    }

    @Override
    public void onExit() {

        game.resetLastPlayerInputs();
        for(UIView view : views){
            view.setVisible(false);
        }
    }

    public void addView(UIView view){

        views.add(view);
        ui.addView(view, JLayeredPane.PALETTE_LAYER);
    }

    public void removeView(UIView view){
        if(view != null) {
            views.remove(view);
            ui.removeView(view);
        }
    }

    public void notifyViews(){
        for(UIView view : views){
            view.update();
        }
    }


}
