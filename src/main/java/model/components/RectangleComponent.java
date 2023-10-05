package model.components;

import model.CanadaPainter;
import model.GameObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class RectangleComponent extends GraphicsComponent{

    protected float width;
    protected float height;

    public RectangleComponent(GameObject obj, CanadaPainter painter, Color color, float width, float height) {
        super(obj, painter, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double dt) {

        this.shape = new Rectangle2D.Float(this.gameObject.getX(), this.gameObject.getY(), width, height);
        super.update(dt);
    }
}
