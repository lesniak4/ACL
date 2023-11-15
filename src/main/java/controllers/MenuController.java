package controllers;

import model.CanadaGame;
import views.ButtonId;
import views.UIView;

public class MenuController extends Controller{
    public MenuController(CanadaGame game, UIView view) {
        super(game, view);
    }

    public void notifyButtonStartPressed(){
        this.game.setLastButtonPressed(ButtonId.PLAY);
    }

    public void notifyButtonExitPressed(){
        this.game.setLastButtonPressed(ButtonId.EXIT);
    }
}
