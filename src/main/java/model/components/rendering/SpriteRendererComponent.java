package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRendererComponent extends GraphicsComponent{

    public SpriteRendererComponent(GameObject obj, CanadaPainter painter, Color color, int layer, BufferedImage sprite) {
        super(obj, painter, color, layer,true);

        this.sprite = sprite;

    }

    @Override
    public void update(double dt) {
        super.update(dt);
    }
}
