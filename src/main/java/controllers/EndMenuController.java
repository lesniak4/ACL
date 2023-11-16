package controllers;

import model.CanadaGame;
import views.ButtonId;
import views.UIView;

public class EndMenuController extends UIController {
    public EndMenuController(CanadaGame game, UIView view) {
        super(game, view);
    }

    public void notifyButtonPlayAgainPressed(){
        this.game.setLastButtonPressed(ButtonId.PLAY);
    }

    public void notifyButtonMainPressed(){
        this.game.setLastButtonPressed(ButtonId.MAIN_MENU);
    }

    public void notifyButtonExitPressed(){
        this.game.setLastButtonPressed(ButtonId.EXIT);
    }
}
