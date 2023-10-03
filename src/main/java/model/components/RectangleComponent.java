package model.components;

import model.CanadaPainter;
import model.GameObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class RectangleComponent extends GraphicsComponent{

    protected float width;
    protected float height;

    public RectangleComponent(CanadaPainter painter, Color color, float width, float height) {
        super(painter, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(GameObject obj, double dt) {

        this.shape = new Rectangle2D.Float(obj.getX(), obj.getY(), width, height);
        super.update(obj, dt);
    }
}
