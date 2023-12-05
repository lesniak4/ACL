package model;


import model.world.Hex;
import model.world.World;
import org.junit.jupiter.api.Test;
import utils.Vector2;

import static org.junit.jupiter.api.Assertions.*;

public class HexTest {

    private int EVEN = 1;

    @Test
    public void hexCoordToGrid(){
        Hex hex =  Hex.gridToHexCoord(EVEN,20,20);
        Vector2 pos = Hex.hexCoordToGrid(EVEN, hex);

        assertEquals(20, pos.X());
        assertEquals(20, pos.Y());
    }

    @Test
    public void gridToHexCoord(){
        Vector2 pos = Hex.hexCoordToGrid(EVEN, new Hex(1,1));
        Hex hex =  Hex.gridToHexCoord(EVEN, (int)pos.X(),(int)pos.Y());

        assertEquals(1, hex.getQ());
        assertEquals(1, hex.getR());
    }

    @Test
    public void hexOfNeighborSW(){
        Hex hex = new Hex(2,2);

        Hex nHex = Hex.hexOfNeighbor(hex, 0);
        assertNotNull(nHex);
        assertEquals(1 ,nHex.getQ());
        assertEquals(3, nHex.getR());
    }

    @Test
    public void hexOfNeighborSE(){
        Hex hex = new Hex(2,2);

        Hex nHex2 = Hex.hexOfNeighbor(hex, 1);
        assertNotNull(nHex2);
        assertEquals(2,nHex2.getQ());
        assertEquals(3, nHex2.getR());
    }

    @Test
    public void hexOfNeighborE(){
        Hex hex = new Hex(2,2);

        Hex nHex3 = Hex.hexOfNeighbor(hex, 2);
        assertNotNull(nHex3);
        assertEquals(3 ,nHex3.getQ());
        assertEquals(2, nHex3.getR());
    }

    @Test
    public void hexOfNeighborNE(){
        Hex hex = new Hex(2,2);

        Hex nHex4 = Hex.hexOfNeighbor(hex, 3);
        assertNotNull(nHex4);
        assertEquals(3 ,nHex4.getQ());
        assertEquals(1, nHex4.getR());
    }

    @Test
    public void hexOfNeighborNW(){
        Hex hex = new Hex(2,2);

        Hex nHex5 = Hex.hexOfNeighbor(hex, 4);
        assertNotNull(nHex5);
        assertEquals(2,nHex5.getQ());
        assertEquals(1, nHex5.getR());
    }

    @Test
    public void hexOfNeighborW(){
        Hex hex = new Hex(2,2);

        Hex nHex6 = Hex.hexOfNeighbor(hex, 5);
        assertNotNull(nHex6);
        assertEquals(1, nHex6.getQ());
        assertEquals(2, nHex6.getR());
    }

    @Test
    public void isInHex(){
        int WIDTH = 700;
        double tileSize = WIDTH / (float) (Math.sqrt(3f) * 10);

        Hex hex =  Hex.gridToHexCoord(EVEN,2,2);
        boolean b = Hex.isInHex(hex, new Vector2(200,200), tileSize);

        assertFalse(b);
    }

}
