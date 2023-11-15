package controllers;

import model.CanadaGame;
import views.ButtonId;
import views.UIView;

public class PauseController extends Controller{
    public PauseController(CanadaGame game, UIView view) {
        super(game, view);
    }

    public void notifyButtonResumePressed(){
        this.game.setLastButtonPressed(ButtonId.PLAY);
    }

    public void notifyButtonMainPressed(){
        this.game.setLastButtonPressed(ButtonId.MAIN_MENU);
    }

    public void notifyButtonExitPressed(){
        this.game.setLastButtonPressed(ButtonId.EXIT);
    }
}
