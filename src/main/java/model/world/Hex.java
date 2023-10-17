package model.world;

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

    public static Hex hexCoordToGrid(int offset, Hex hex) {
        int q = hex.getQ() + ((hex.getR() + offset * (hex.getR() & 1)) / 2);
        int r = hex.getR();
        return new Hex(q, r);
    }

    public static Hex hexOfNeighbor(int offset, Hex hex, int direction) {
        int[][][] evenR_direc_diff = {
                {{+1, 0}, {+1, -1}, {0, -1}, {-1, 0}, {0, +1}, {+1, +1}},
                {{+1, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, +1}, {0, +1}}
        };

        var diff = evenR_direc_diff[offset][direction];
        return new Hex(hex.getQ() + diff[0], hex.getR() + diff[1]);
    }

    public static int axial_distance(Hex a, Hex b) {
        return (Math.abs(a.getQ() - b.getQ())
                + Math.abs(a.getQ() + a.getR() - b.getQ() - b.getR())
                + Math.abs(a.getR() - b.getR())) / 2;
    }

    public static int offset_distance(int offset, Hex a, Hex b){
        Hex ac = hexCoordToGrid(offset, a);
        Hex bc = hexCoordToGrid(offset, b);
        return axial_distance(ac, bc);
    }


    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }
}
