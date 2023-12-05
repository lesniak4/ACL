package model.items;

import javax.swing.*;
import java.awt.image.BufferedImage;

public abstract class ItemData {

    protected String name;
    protected BufferedImage sprite;
    protected ImageIcon uiIcon;
    protected double colliderRadius;
    protected int scoreValue;

    public ItemData(String name, BufferedImage sprite, ImageIcon uiIcon, double colliderRadius, int scoreValue){

        this.name = name;
        this.sprite = sprite;
        this.uiIcon = uiIcon;
        this.colliderRadius = colliderRadius;
        this.scoreValue = scoreValue;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public ImageIcon getIcon() {
        return uiIcon;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public double getColliderRadius() {
        return colliderRadius;
    }

    public int getScoreValue() { return this.scoreValue; }
}
