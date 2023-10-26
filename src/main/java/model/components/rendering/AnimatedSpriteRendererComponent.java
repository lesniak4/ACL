package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import utils.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSpriteRendererComponent extends SpriteRendererComponent{

    private AnimatedSprite animatedSprite;
    private int rowIndex;
    private double currentFrame;
    private double timeScale;

    public AnimatedSpriteRendererComponent(GameObject obj, CanadaPainter painter, Color color, int layer, AnimatedSprite sprite, double timeScale) {
        super(obj, painter, color, layer, sprite.getSprite(0));

        this.animatedSprite = sprite;
        this.timeScale = timeScale;
        rowIndex = 0;
        currentFrame = 0;
    }

    @Override
    public void update(double dt) {

        this.sprite = animatedSprite.getSprite((int)currentFrame);
        int startFrame = rowIndex * animatedSprite.getCols();

        currentFrame = startFrame + ((currentFrame + timeScale) % animatedSprite.getCols());

        super.update(dt);
    }

    public void setRowIndex(int row){
        this.rowIndex = row;
    }

    public void setSprite(AnimatedSprite sprite){
        this.animatedSprite = sprite;
    }
}
