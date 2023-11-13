package views;

import model.CanadaGame;
import model.CanadaPainter;
import utils.SpriteLoader;

import javax.swing.*;
import java.awt.*;

public class InGameView extends UIView {

    private JLabel coinsIcon;
    private JLabel coinsLabel;

    public InGameView(CanadaGame game){

        super(game);
    }

    @Override
    public void buildView(){

        this.setSize(1024/4, (int)(576 * 0.07));
        this.setBackground(new Color(40,40,40,190));

        coinsIcon = new JLabel(new ImageIcon(SpriteLoader.getInstance().getGoldCoinsUI()));

        coinsLabel = new JLabel("0");
        coinsLabel.setForeground(Color.WHITE);

        this.add(coinsIcon);
        this.add(coinsLabel);
    }

    @Override
    public void update() {

        coinsLabel.setText(Integer.toString(game.getScore()));
    }
}
