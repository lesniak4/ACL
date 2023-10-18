package model.components.rendering;

import model.CanadaPainter;
import model.GameObject;
import utils.Vector2;
import model.world.Hex;
import model.world.HexLayout;

import java.awt.*;
import java.util.ArrayList;

public class HexRendererComponent extends GraphicsComponent {

    protected Hex hex;
    protected HexLayout layout;


    public HexRendererComponent(GameObject obj, CanadaPainter painter, Color color, Hex hex, HexLayout layout, boolean isVisible) {
        super(obj, painter, color, isVisible);

        this.hex = hex;
        this.layout = layout;
    }

    @Override
    public void update(double dt) {

        Polygon p = new Polygon();
        ArrayList<Vector2> corners = layout.polygonCorners(hex);
        for(Vector2 c : corners){
            Vector2 screenPos = Vector2.worldToScreenIso(c);
            p.addPoint((int)screenPos.X(), (int)screenPos.Y());
        }
        this.shape = p;
        super.update(dt);
    }
}
