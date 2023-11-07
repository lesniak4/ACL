package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import utils.SpriteSheet;

import java.awt.*;

public class AnimatedSpriteRendererComponent extends SpriteRendererComponent{

    private SpriteSheet spriteSheet;
    private int rowIndex;
    private double currentFrame;
    private double timeScale;

    public AnimatedSpriteRendererComponent(GameObject obj, CanadaPainter painter, Color color, int layer, boolean transparent, SpriteSheet sprite, double timeScale) {
        super(obj, painter, color, layer, transparent, sprite.getSprite(0));

        this.spriteSheet = sprite;
        this.timeScale = timeScale;
        rowIndex = 0;
        currentFrame = 0;
    }

    @Override
    public void update(double dt) {

        this.sprite = spriteSheet.getSprite((int)currentFrame);
        int startFrame = rowIndex * spriteSheet.getCols();

        currentFrame = startFrame + ((currentFrame + timeScale) % spriteSheet.getCols());

        super.update(dt);
    }

    public void setRowIndex(int row){
        this.rowIndex = row;
    }

    public void setSprite(SpriteSheet sprite){
        this.spriteSheet = sprite;
    }
}
