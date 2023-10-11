package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import model.components.Component;

import java.awt.*;

public abstract class GraphicsComponent extends Component {

    protected CanadaPainter painter;
    protected Shape shape;
    protected Color color;

    protected boolean isVisible;

    public GraphicsComponent(GameObject obj, CanadaPainter painter, Color color, boolean isVisible){
        super(obj);
        this.painter = painter;
        this.color = color;
        this.isVisible = isVisible;
    }

    @Override
    public void update(double dt) {

        if(isVisible)
            painter.addToDrawQueue(this);
    }

    public Shape getShape(){
        return this.shape;
    }

    public Color getColor(){
        return this.color;
    }

    public void setInvisible() { this.isVisible = false; }
}
