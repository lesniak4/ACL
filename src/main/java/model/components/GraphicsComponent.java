package model.components;

import model.CanadaPainter;
import model.GameObject;

import java.awt.*;

public abstract class GraphicsComponent implements IComponent{

    protected CanadaPainter painter;
    protected Shape shape;
    protected Color color;

    public GraphicsComponent(CanadaPainter painter, Color color){
        this.painter = painter;
        this.color = color;
    }

    @Override
    public void update(GameObject obj, double dt) {

        painter.addToDrawQueue(this);
    }

    public Shape getShape(){
        return this.shape;
    }

    public Color getColor(){
        return this.color;
    }
}
