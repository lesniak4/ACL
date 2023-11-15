package controllers;

import engine.Cmd;
import model.CanadaGame;
import views.ButtonId;
import views.UIView;

public class InGameController extends Controller{
    public InGameController(CanadaGame game, UIView view) {
        super(game, view);
    }

    public void notifyKeyEscapePressed(){
        game.setLastKeyPressed(Cmd.PAUSE);
    }

    public void notifyGameFinished() { game.setLastButtonPressed(ButtonId.END);}

}
