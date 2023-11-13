package views;

import model.CanadaGame;

import javax.swing.*;
import java.awt.*;

public abstract class UIView extends JPanel {

    protected CanadaGame game;

    public UIView(CanadaGame game){

        super();

        this.game = game;

        buildView();
        this.setVisible(false);
    }

    public abstract void buildView();
    public abstract void update();

}
