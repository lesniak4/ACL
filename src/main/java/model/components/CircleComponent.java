package model.components;

import model.CanadaPainter;
import model.GameObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleComponent extends GraphicsComponent{

    protected int radius;

    public CircleComponent(CanadaPainter painter, Color color, int radius) {
        super(painter, color);
        this.radius = radius;
    }

    @Override
    public void update(GameObject obj, double dt) {

        this.shape = new Ellipse2D.Float(obj.getX(), obj.getY(), 2*radius, 2*radius);
        super.update(obj, dt);
    }
}
