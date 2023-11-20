package views;

import model.CanadaGame;
import utils.GameConfig;
import utils.SpriteLoader;

import javax.swing.*;
import java.awt.*;

public class LaunchGameView extends UIView {

    public LaunchGameView(CanadaGame game){

        super(game);
    }

    @Override
    public void buildView(){

        GameConfig gc = GameConfig.getInstance();

        this.setSize(gc.getWinWidth(), gc.getWinHeight());
        this.setBackground(new Color(0,0,0,255));

    }

    @Override
    public void update() {
    }
}
