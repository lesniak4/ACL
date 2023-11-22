package views;

import model.CanadaGame;
import model.CanadaPainter;
import utils.GameConfig;
import utils.SpriteLoader;

import javax.swing.*;
import java.awt.*;

public class ScoreView extends UIView {

    private JLabel coinsIcon;
    private JLabel coinsLabel;

    private JLabel axeIcon;

    public ScoreView(CanadaGame game){

        super(game);
    }

    @Override
    public void buildView(){

        GameConfig gc = GameConfig.getInstance();

        this.setSize(gc.getWinWidth()/6, (int)(gc.getWinHeight()* 0.07));
        this.setBackground(new Color(40,40,40,190));

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        coinsIcon = new JLabel(new ImageIcon(SpriteLoader.getInstance().getGoldCoinsUI()));

        coinsLabel = new JLabel("0");
        coinsLabel.setForeground(Color.WHITE);

        axeIcon = new JLabel(new ImageIcon(SpriteLoader.getInstance().getAxeUI()));
        axeIcon.setVisible(false);

        this.add(coinsIcon);
        this.add(coinsLabel);
        this.add(axeIcon);

        layout.putConstraint(SpringLayout.WEST, coinsIcon, 15, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, coinsLabel, 5, SpringLayout.EAST, coinsIcon);
        layout.putConstraint(SpringLayout.WEST, axeIcon, 15, SpringLayout.EAST, coinsLabel);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, coinsIcon, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, coinsLabel, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, axeIcon, 0, SpringLayout.VERTICAL_CENTER, this);
    }

    @Override
    public void update() {

        coinsLabel.setText(Integer.toString(game.getScore()));
        axeIcon.setVisible(game.playerOwnsKey());
    }
}
