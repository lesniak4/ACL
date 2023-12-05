package views;

import controllers.EndMenuController;
import controllers.MenuController;
import controllers.PauseController;
import model.CanadaGame;
import utils.GameConfig;
import utils.SpriteLoader;
import utils.UIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EndMenuView extends UIView{

    private EndMenuController controller;
    private JButton playAgainButton;
    private JButton mainButton;
    private JButton exitButton;
    private JLabel titleLabel;
    private JLabel scoreLabel;

    private BufferedImage background;

    public EndMenuView(CanadaGame game){
        super(game);
        controller = new EndMenuController(game, this);
        this.background = SpriteLoader.getSprite("/sprites/ui/mainmenu_background.png");
    }

    @Override
    public void buildView(){

        GameConfig gc = GameConfig.getInstance();

        this.setSize(gc.getWinWidth(), gc.getWinHeight());
        this.setLayout(null);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(40,40,40,190));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));

        int titleWidth = (int)(gc.getWinWidth() * 0.6);
        int titleHeight = (int)(gc.getWinHeight() * 0.15);
        titlePanel.setBounds((gc.getWinWidth() - titleWidth) / 2 , (gc.getWinHeight() - titleHeight) / 8, titleWidth, titleHeight);
        titlePanel.setBorder(new EmptyBorder(20,25,20,25));
        titleLabel = new JLabel("Finished");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Score");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(scoreLabel);

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.PAGE_AXIS));
        int buttonsWidth = (int)(gc.getWinWidth() * 0.3);
        int buttonsHeight = (int)(gc.getWinHeight() * 0.5);
        buttonContainer.setBounds((gc.getWinWidth() - buttonsWidth) / 2 , (gc.getWinHeight() - buttonsHeight) / 2 + 80, buttonsWidth, buttonsHeight);
        buttonContainer.setBackground(new Color(0,0,0,0));

        playAgainButton = new JButton("PLAY AGAIN");
        UIStyle.setButtonStyle(playAgainButton);

        mainButton = new JButton("MAIN MENU");
        UIStyle.setButtonStyle(mainButton);

        exitButton = new JButton("EXIT");
        UIStyle.setButtonStyle(exitButton);

        playAgainButton.addActionListener(e -> {
            controller.notifyButtonPlayAgainPressed();
        });

        mainButton.addActionListener(e -> {
            controller.notifyButtonMainPressed();
        });

        exitButton.addActionListener(e -> {
            controller.notifyButtonExitPressed();
        });

        buttonContainer.add(playAgainButton);
        buttonContainer.add(Box.createRigidArea(new Dimension(0,20)));
        buttonContainer.add(mainButton);
        buttonContainer.add(Box.createRigidArea(new Dimension(0,20)));
        buttonContainer.add(exitButton);

        this.add(titlePanel);
        this.add(buttonContainer);
    }

    @Override
    public void update() {
        if(game.hasPlayerWon())
            titleLabel.setText("You won !");
        else{
            titleLabel.setText("You have been killed by a monster.");
        }
        scoreLabel.setText("Score : " + game.getScore());
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
