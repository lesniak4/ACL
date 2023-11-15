package views;

import controllers.EndMenuController;
import controllers.MenuController;
import controllers.PauseController;
import model.CanadaGame;
import utils.GameConfig;
import utils.SpriteLoader;

import javax.swing.*;
import java.awt.*;

public class EndMenuView extends UIView{

    private EndMenuController controller;
    private JButton playAgainButton;
    private JButton mainButton;
    private JButton exitButton;
    private JLabel titleLabel;

    public EndMenuView(CanadaGame game){
        super(game);
        controller = new EndMenuController(game, this);
    }

    @Override
    public void buildView(){

        GameConfig gc = GameConfig.getInstance();

        this.setSize(gc.getWinWidth(), gc.getWinHeight());
        this.setBackground(new Color(40,40,40,255));

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        titleLabel = new JLabel("Finished");
        titleLabel.setForeground(Color.WHITE);


        playAgainButton = new JButton("Play Again");
        mainButton = new JButton("Main Menu");
        exitButton = new JButton("Exit");

        playAgainButton.addActionListener(e -> {
            controller.notifyButtonPlayAgainPressed();
        });

        mainButton.addActionListener(e -> {
            controller.notifyButtonMainPressed();
        });

        exitButton.addActionListener(e -> {
            controller.notifyButtonExitPressed();
        });

        this.add(titleLabel);
        this.add(playAgainButton);
        this.add(mainButton);
        this.add(exitButton);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, playAgainButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, mainButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, exitButton, 0, SpringLayout.HORIZONTAL_CENTER, this);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, titleLabel, -75, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, playAgainButton, -25, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, mainButton, 25, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, exitButton, 75, SpringLayout.VERTICAL_CENTER, this);
    }

    @Override
    public void update() {
        if(game.hasPlayerWon())
            titleLabel.setText("You won !");
        else{
            String text;
            if(game.hasPlayerLost())
                text = "You have been killed by a monster";
            else
                text = "Time limit exceeded";

            titleLabel.setText(text);
        }
    }
}
