package views;

import controllers.MenuController;
import controllers.PauseController;
import model.CanadaGame;
import utils.GameConfig;
import utils.SpriteLoader;
import utils.UIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        int width = (int)(gc.getWinWidth() * 0.5);
        int height = (int)(gc.getWinHeight() * 0.8);
        this.setBounds((gc.getWinWidth() - width) / 2 , (gc.getWinHeight() - height) / 2, width, height);
        this.setBackground(new Color(40,40,40,190));
        this.setLayout(null);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(40,40,40,190));

        int titleWidth = (int)(gc.getWinWidth() * 0.4);
        int titleHeight = (int)(gc.getWinHeight() * 0.15);
        titlePanel.setBounds((width - titleWidth) / 2 , (height - titleHeight) / 8, titleWidth, titleHeight);
        titlePanel.setBorder(new EmptyBorder(20,25,20,25));
        titleLabel = new JLabel("GAME PAUSED");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(CENTER_ALIGNMENT);

        titlePanel.add(titleLabel);

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.PAGE_AXIS));
        int buttonsWidth = (int)(gc.getWinWidth() * 0.3);
        int buttonsHeight = (int)(gc.getWinHeight() * 0.5);
        buttonContainer.setBounds((width - buttonsWidth) / 2 , (height - buttonsHeight) / 2 + 80, buttonsWidth, buttonsHeight);
        buttonContainer.setBackground(new Color(0,0,0,0));

        resumeButton = new JButton("RESUME");
        UIStyle.setButtonStyle(resumeButton);

        mainButton = new JButton("MAIN MENU");
        UIStyle.setButtonStyle(mainButton);

        exitButton = new JButton("EXIT");
        UIStyle.setButtonStyle(exitButton);

        resumeButton.addActionListener(e -> {
            controller.notifyButtonResumePressed();
        });

        mainButton.addActionListener(e -> {
            controller.notifyButtonMainPressed();
        });

        exitButton.addActionListener(e -> {
            controller.notifyButtonExitPressed();
        });


        buttonContainer.add(resumeButton);
        buttonContainer.add(Box.createRigidArea(new Dimension(0,20)));
        buttonContainer.add(mainButton);
        buttonContainer.add(Box.createRigidArea(new Dimension(0,20)));
        buttonContainer.add(exitButton);

        this.add(titlePanel);
        this.add(buttonContainer);
    }

    @Override
    public void update() {
    }
}
