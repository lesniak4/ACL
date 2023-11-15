package controllers;

import model.CanadaGame;
import views.UIView;

public abstract class Controller {

    protected CanadaGame game;
    protected  UIView view;

    public Controller(CanadaGame game, UIView view){
        this.game = game;
        this.view = view;
    }
}
