package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BitmaskedSpriteRendererComponent extends SpriteRendererComponent{

    private SpriteSheet spriteSheet;
    protected int bitmask;

    public BitmaskedSpriteRendererComponent(GameObject obj, CanadaPainter painter, Color color, int layer, boolean transparent, SpriteSheet sprite) {
        super(obj, painter, color, layer, transparent, sprite.getSprite(0));

        this.spriteSheet = sprite;
        this.bitmask = 0;
    }

    public void setBitmask(int bitmask){
        this.bitmask = bitmask;
    }

    @Override
    public void update(double dt) {

        this.sprite = spriteSheet.getSprite(bitmask);

        super.update(dt);
    }
}
