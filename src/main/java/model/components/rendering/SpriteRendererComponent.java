package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;

import java.awt.*;

public class SpriteRendererComponent extends GraphicsComponent{

    public SpriteRendererComponent(GameObject obj, CanadaPainter painter, Color color, Image sprite) {
        super(obj, painter, color, true);

        this.sprite = sprite;

    }

    @Override
    public void update(double dt) {
        super.update(dt);
    }
}
