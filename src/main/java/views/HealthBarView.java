package views;

import model.CanadaGame;
import model.GameObject;
import model.components.attacks.HealthComponent;
import utils.GameConfig;
import utils.Vector2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class HealthBarView extends UIView {

    private HealthComponent health;
    private GameObject entity;
    private JProgressBar healthBar;

    public HealthBarView(CanadaGame game, GameObject entity, HealthComponent health){
        super(game);
        this.entity = entity;
        this.health = health;
    }

    @Override
    public void buildView(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        healthBar = new JProgressBar(0,100);
        healthBar.setBorderPainted(true);
        healthBar.setValue(100);
        Dimension dimension = new Dimension(75,10);
        healthBar.setMaximumSize(dimension);
        healthBar.setPreferredSize(dimension);

        this.setBounds(0, 0, healthBar.getWidth(), healthBar.getHeight());
        this.setBackground(new Color(0,0,0,0));
        healthBar.setVisible(false);
        healthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        healthBar.setUI(new BasicProgressBarUI());
        this.setVisible(false);

        this.add(healthBar);
    }

    @Override
    public void update() {
        GameConfig gc = GameConfig.getInstance();

        Vector2 entityPos = entity.getPosition();
        Vector2 camPos = game.getCameraPosition();
        Vector2 diffPos = new Vector2(entityPos.X() - camPos.X(), entityPos.Y() - camPos.Y());
        Vector2 hexLayoutCorrection = Vector2.worldToScreenIso(new Vector2(diffPos.X() - gc.getTileSize(), diffPos.Y() - gc.getTileSize()));
        Vector2 layoutPos = new Vector2(gc.getWinWidth()/2 + hexLayoutCorrection.X() - healthBar.getWidth()/2, gc.getWinHeight()/2 + hexLayoutCorrection.Y() - healthBar.getHeight()/2 - 5); // -5 pour que la barre soit un peu plus au dessus de l'entité

        if(layoutPos.X() > 0 && layoutPos.X() < gc.getWinWidth() && layoutPos.Y() > 0 && layoutPos.Y() < gc.getWinHeight()) {
            this.setBounds((int)layoutPos.X(), (int)layoutPos.Y(), healthBar.getWidth(), healthBar.getHeight());
            healthBar.setVisible(true);
            this.setVisible(true);
        } else {
            healthBar.setVisible(false);
            this.setVisible(false);
        }

        healthBar.setValue(health.getHealthOn100());

        if(health.getCurrentHealth() <= 0){
            healthBar.setVisible(false);
            this.setVisible(false);
        }
        else{
            healthBar.setForeground(new Color((255* (100-health.getHealthOn100())) / 100,255 * health.getHealthOn100() /100, 0 ));
        }
    }
}
