package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import utils.GameConfig;
import utils.Vector2;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleRendererComponent extends GraphicsComponent{

    protected double radius;

    public CircleRendererComponent(GameObject obj, CanadaPainter painter, Color color, int layer, double radius,boolean isVisible) {
        super(obj, painter, color, layer, isVisible, false);
        this.radius = radius;
    }

    @Override
    public void update() {

        GameConfig gc = GameConfig.getInstance();

        double x = getGameObject().getX() - radius - getGameObject().getGame().getCameraPosition().X();
        double y = getGameObject().getY() - radius - getGameObject().getGame().getCameraPosition().Y();
        Vector2 pos = Vector2.worldToScreenIso(new Vector2(x, y));

        this.shape = new Ellipse2D.Double(pos.X() + gc.getWinWidth() / 2d, pos.Y() + gc.getWinHeight() / 2d, 2*radius, 2*radius);

        super.update();
    }
}
