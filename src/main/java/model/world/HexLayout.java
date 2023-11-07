package model.world;

import utils.Vector2;

import java.util.ArrayList;

public class HexLayout
{
    private final HexOrientation orientation;
    private final Vector2 size;
    private final Vector2 origin;

    static public HexOrientation pointy = new HexOrientation(Math.sqrt(3.0), Math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0, Math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0, 0.5);
    static public HexOrientation flat = new HexOrientation(3.0 / 2.0, 0.0, Math.sqrt(3.0) / 2.0, Math.sqrt(3.0), 2.0 / 3.0, 0.0, -1.0 / 3.0, Math.sqrt(3.0) / 3.0, 0.0);

    public HexLayout(HexOrientation orientation, Vector2 size, Vector2 origin)
    {
        this.orientation = orientation;
        this.size = size;
        this.origin = origin;
    }

    public Vector2 getSize(){
        return this.size;
    }

    public Vector2 hexToWorldPos(Hex h)
    {
        HexOrientation M = orientation;
        double x = (M.f0 * h.getQ() + M.f1 * h.getR()) * size.X();
        double y = (M.f2 * h.getQ() + M.f3 * h.getR()) * size.Y();
        return new Vector2(x + origin.X(), y + origin.Y());
    }

    public ArrayList<Vector2> polygonCorners(Hex h)
    {
        ArrayList<Vector2> corners = new ArrayList<Vector2>(){{}};
        Vector2 center = hexToWorldPos(h);
        for (int i = 0; i < 6; i++)
        {
            Vector2 offset = hexCornerOffset(i);
            corners.add(new Vector2(center.X() + offset.X(), center.Y() + offset.Y()));
        }
        return corners;
    }

    private Vector2 hexCornerOffset(int corner)
    {
        HexOrientation M = orientation;
        double angle = 2.0 * Math.PI * (M.startAngle - corner) / 6.0;
        return new Vector2(size.X() * Math.cos(angle), size.Y() * Math.sin(angle));
    }

}
