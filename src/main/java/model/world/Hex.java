package model.world;

public class Hex {

    private final int q;
    private final int r;
    private final int s;

    public Hex(int q, int r)
    {
        this.q = q;
        this.r = r;
        this.s = -q - r;
        if (q + r + s != 0) throw new IllegalArgumentException("q + r + s doit être égal à 0");
    }


    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }
}
