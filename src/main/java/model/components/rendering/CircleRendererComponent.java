package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleRendererComponent extends GraphicsComponent{

    protected float radius;

    public CircleRendererComponent(GameObject obj, CanadaPainter painter, Color color, float radius) {
        super(obj, painter, color);
        this.radius = radius;
    }

    @Override
    public void update(double dt) {

        this.shape = new Ellipse2D.Float(this.gameObject.getX(), this.gameObject.getY(), 2*radius, 2*radius);
        super.update(dt);
    }
}
