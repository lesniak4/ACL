package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RectangleRendererComponent extends GraphicsComponent{

    protected double width;
    protected double height;

    public RectangleRendererComponent(GameObject obj, CanadaPainter painter, Color color, int layer, double width, double height, boolean isVisible) {
        super(obj, painter, color, layer, isVisible);
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double dt) {

        this.shape = new Rectangle2D.Double(this.gameObject.getX(), this.gameObject.getY(), width, height);
        super.update(dt);
    }
}
