package controllers;

import model.CanadaGame;
import views.UIView;

public abstract class UIController {

    protected CanadaGame game;
    protected UIView view;

    public UIController(CanadaGame game, UIView view){
        this.game = game;
        this.view = view;
    }
}
