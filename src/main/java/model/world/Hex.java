package model.world;

import model.Vector2;

public class Hex {

    private final int q;
    private final int r;
    private final int s;

    public Hex(int q, int r) {
        this.q = q;
        this.r = r;
        this.s = -q - r;
        if (q + r + s != 0) throw new IllegalArgumentException("q + r + s doit être égal à 0");
    }

    public static Hex gridToHexCoord(int offset, int col, int row) { // evenr to axial ~ OffsetCoord
        int q = col - ((row + offset * (row & 1)) / 2);
        int r = row;
        return new Hex(q, r);
    }

    public static Vector2 hexCoordToGrid(int offset, Hex hex) {
        int q = hex.getQ() + ((hex.getR() + offset * (hex.getR() & 1)) / 2);
        int r = hex.getR();
        return new Vector2(q, r);
    }

    public static Hex hexOfNeighbor(Hex hex, int direction) {
        if(direction < 0 || direction > 5 )
            return null;

        int[][][] evenR_direc_diff = {
                {{-1, 0}, {0, +1}, {+1, -1}, {+1, 0}, {0, -1}, {-1, +1}},
        };

        var diff = evenR_direc_diff[0][direction];
        return new Hex(hex.getQ() + diff[0], hex.getR() + diff[1]);
    }

    public static Vector2 hexToWorldPos(Hex h, double size)
    {
        HexOrientation M = HexLayout.pointy;
        double x = (M.f0 * h.getQ() + M.f1 * h.getR()) * size;
        double y = (M.f2 * h.getQ() + M.f3 * h.getR()) * size;
        return new Vector2(x + size, y + size);
    }

    public static Hex worldPosToHex(Vector2 pos, double size)
    {
        // axial to cube
        double q = (1./Math.sqrt(3) * (pos.X() - size) - 1./3 * (pos.Y() - size)) / size;
        double r = (2./3 * (pos.Y() - size)) / size;
        double s = -q - r;

        // cube round
        long qi = Math.round(q);
        long ri = Math.round(r);
        long si = Math.round(s);

        double q_diff = Math.abs(qi - q);
        double r_diff = Math.abs(ri - r);
        double s_diff = Math.abs(si - s);

        if (q_diff > r_diff && q_diff > s_diff)
            qi = -ri - si;
        else if (r_diff > s_diff)
            ri = -qi - si;

        return new Hex((int)qi, (int)ri);
    }

    public static boolean isInHex(Hex hex, Vector2 pos, double size){
        return hex.equals(worldPosToHex(pos, size));
    }


    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public int getS() {
        return s;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object hex){
        return hex instanceof Hex && q == ((Hex)hex).getQ() && r == ((Hex)hex).getR() && s == ((Hex)hex).getS();
    }

    @Override
    public String toString() {
        return "Hex{" +
                "q=" + q +
                ", r=" + r +
                ", s=" + s +
                '}';
    }
}
