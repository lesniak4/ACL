package views;

import controllers.MenuController;
import controllers.PauseController;
import model.CanadaGame;
import utils.GameConfig;
import utils.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PauseView extends UIView{

    private PauseController controller;
    private JButton resumeButton;
    private JButton mainButton;
    private JButton exitButton;
    private JLabel titleLabel;

    public PauseView(CanadaGame game){
        super(game);
        controller = new PauseController(game, this);
    }

    @Override
    public void buildView(){

        GameConfig gc = GameConfig.getInstance();

        this.setSize(gc.getWinWidth(), gc.getWinHeight());
        this.setBackground(new Color(40,40,40,255));

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        titleLabel = new JLabel("Game Paused");
        titleLabel.setForeground(Color.WHITE);


        resumeButton = new JButton("Resume");
        mainButton = new JButton("Main Menu");
        exitButton = new JButton("Exit");

        resumeButton.addActionListener(e -> {
            controller.notifyButtonResumePressed();
        });

        mainButton.addActionListener(e -> {
            controller.notifyButtonMainPressed();
        });

        exitButton.addActionListener(e -> {
            controller.notifyButtonExitPressed();
        });

        this.add(titleLabel);
        this.add(resumeButton);
        this.add(mainButton);
        this.add(exitButton);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, resumeButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, mainButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, exitButton, 0, SpringLayout.HORIZONTAL_CENTER, this);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, titleLabel, -75, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, resumeButton, -25, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, mainButton, 25, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, exitButton, 75, SpringLayout.VERTICAL_CENTER, this);
    }

    @Override
    public void update() {
    }
}
