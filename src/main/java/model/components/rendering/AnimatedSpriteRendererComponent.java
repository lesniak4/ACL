package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import utils.AnimatedSprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSpriteRendererComponent extends SpriteRendererComponent{

    private AnimatedSprite animatedSprite;
    private int rowIndex;
    private int currentFrame;

    public AnimatedSpriteRendererComponent(GameObject obj, CanadaPainter painter, Color color, int layer, AnimatedSprite sprite) {
        super(obj, painter, color, layer, sprite.getSprite(0));

        animatedSprite = sprite;
        rowIndex = 0;
        currentFrame = 0;
    }

    @Override
    public void update(double dt) {

        this.sprite = animatedSprite.getSprite(currentFrame);
        int startFrame = rowIndex * animatedSprite.getCols();

        currentFrame = startFrame + ((currentFrame + 1) % animatedSprite.getCols());

        super.update(dt);
    }

    public void setRowIndex(int row){
        this.rowIndex = row;
    }
}
