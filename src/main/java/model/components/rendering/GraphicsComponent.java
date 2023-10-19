package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import model.components.Component;
import utils.Vector2;

import java.awt.*;

public abstract class GraphicsComponent extends Component {

    protected CanadaPainter painter;
    protected Shape shape;
    protected Image sprite;
    protected Color color;

    protected boolean isVisible;
    protected Vector2 screenPos;

    public GraphicsComponent(GameObject obj, CanadaPainter painter, Color color, boolean isVisible){
        super(obj);
        this.painter = painter;
        this.color = color;
        this.isVisible = isVisible;
    }

    @Override
    public void update(double dt) {

        Vector2 camPos = getGameObject().getGame().getCameraPosition();
        this.screenPos = Vector2.worldToScreenIso(
                new Vector2(getGameObject().getPosition().X() - camPos.X(),
                            getGameObject().getPosition().Y() - camPos.Y()));

        if(isVisible && isInsideViewport())
            painter.addToDrawQueue(this);
    }

    public boolean isInsideViewport() {

        double xLimit = painter.getWidth() * 0.5d;
        double yLimit = painter.getHeight() * 0.5d;
        if(shape != null){
            xLimit += shape.getBounds2D().getWidth();
            yLimit += shape.getBounds2D().getHeight();
        }else if(sprite != null){
            xLimit += sprite.getWidth(null);
            yLimit += sprite.getHeight(null);
        }

        if(screenPos.X() > -xLimit && screenPos.X() < xLimit){
            if(screenPos.Y() > -yLimit && screenPos.Y() < yLimit){
                return true;
            }
        }
        return false;
    }

    public Shape getShape(){
        return this.shape;
    }

    public Image getSprite(){
        return this.sprite;
    }

    public Color getColor(){
        return this.color;
    }

    public Vector2 getScreenPos(){
        return this.screenPos;
    }

    public double getDepth(){
        return getGameObject().getX() + getGameObject().getY();
    }

    public void setInvisible() { this.isVisible = false; }
}
