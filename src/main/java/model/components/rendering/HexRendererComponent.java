package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import model.Vector2;
import model.world.Hex;
import model.world.HexLayout;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class HexRendererComponent extends GraphicsComponent {

    protected Hex hex;
    protected HexLayout layout;


    public HexRendererComponent(GameObject obj, CanadaPainter painter, Color color, Hex hex, HexLayout layout) {
        super(obj, painter, color);

        this.hex = hex;
        this.layout = layout;
    }

    @Override
    public void update(double dt) {

        Polygon p = new Polygon();
        ArrayList<Vector2> corners = layout.polygonCorners(hex);
        for(Vector2 c : corners){
            p.addPoint((int)c.X(), (int)c.Y());
        }
        this.shape = p;
        super.update(dt);
    }
}
