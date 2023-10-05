package model.components;

import model.CanadaPainter;
import model.GameObject;

import java.awt.*;

public abstract class GraphicsComponent extends Component {

    protected CanadaPainter painter;
    protected Shape shape;
    protected Color color;

    public GraphicsComponent(GameObject obj, CanadaPainter painter, Color color){
        super(obj);
        this.painter = painter;
        this.color = color;
    }

    @Override
    public void update(double dt) {

        painter.addToDrawQueue(this);
    }

    public Shape getShape(){
        return this.shape;
    }

    public Color getColor(){
        return this.color;
    }
}
