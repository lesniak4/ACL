package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import model.components.Component;
import utils.GameConfig;
import utils.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GraphicsComponent extends Component {

    protected CanadaPainter painter;
    protected Shape shape;
    protected BufferedImage sprite;
    protected Color color;
    protected int layer;
    protected double depth;
    protected float opacity;
    protected boolean isVisible;
    protected boolean transparent;
    protected Vector2 screenPos;

    public GraphicsComponent(GameObject obj, CanadaPainter painter, Color color, int layer, boolean isVisible, boolean transparent){
        super(obj);
        this.painter = painter;
        this.color = color;
        this.isVisible = isVisible;
        this.layer = layer;
        this.depth = this.layer*10000 + getGameObject().getX() + getGameObject().getY();
        this.opacity = 1f;
        this.transparent = transparent;
    }

    @Override
    public void update() {

        this.depth = this.layer*10000 + getGameObject().getX() + getGameObject().getY();

        Vector2 camPos = getGameObject().getGame().getCameraPosition();

        double dstToCam = Vector2.distance(camPos, getGameObject().getPosition());

        if(transparent && layer == 1 && dstToCam > 5 && dstToCam < 200){
            this.opacity = 0.2f + ((float)dstToCam / 200f) * 0.8f;
        }else{
            this.opacity = 1f;
        }

        this.screenPos = Vector2.worldToScreenIso(
                new Vector2(getGameObject().getPosition().X() - camPos.X(),
                            getGameObject().getPosition().Y() - camPos.Y()));

        if(isVisible && isInsideViewport()) {
            painter.addToDrawQueue(this);
        }
    }

    public boolean isInsideViewport() {

        GameConfig gc = GameConfig.getInstance();

        double xLimit = gc.getWinWidth() * 0.5d;
        double yLimit = gc.getWinHeight() * 0.5d;
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
        return this.depth;
    }

    public float getOpacity(){
        return this.opacity;
    }

    public void setInvisible() { this.isVisible = false; }
}
