package views;

import controllers.MenuController;
import model.CanadaGame;
import utils.GameConfig;
import utils.SpriteLoader;

import javax.swing.*;
import java.awt.*;

public class MenuView extends UIView{

    private MenuController controller;
    private JButton startButton;
    private JButton exitButton;
    private JLabel titleLabel;

    public MenuView(CanadaGame game){
        super(game);
        controller = new MenuController(game, this);
    }

    @Override
    public void buildView(){

        GameConfig gc = GameConfig.getInstance();

        this.setSize(gc.getWinWidth(), gc.getWinHeight());
        this.setBackground(new Color(40,40,40,255));

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        titleLabel = new JLabel("CanadaCamp");
        titleLabel.setForeground(Color.WHITE);


        startButton = new JButton("Start");
        exitButton = new JButton("Exit");

        startButton.addActionListener(e -> {
            controller.notifyButtonStartPressed();
        });

        exitButton.addActionListener(e -> {
            controller.notifyButtonExitPressed();
        });

        this.add(startButton);
        this.add(exitButton);
        this.add(titleLabel);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, startButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, exitButton, 0, SpringLayout.HORIZONTAL_CENTER, this);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, titleLabel, -50, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, startButton, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, exitButton, 50, SpringLayout.VERTICAL_CENTER, this);
    }

    @Override
    public void update() {
    }
}
