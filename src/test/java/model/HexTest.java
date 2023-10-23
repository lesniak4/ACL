package model;


import model.world.Hex;
import model.world.World;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HexTest {

    public int EVEN = 1;

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
    public void hexOfNeighbor(){
        Hex hex = new Hex(2,2);

        Hex nHex = Hex.hexOfNeighbor(hex, 0);
        assertNotNull(nHex);
        assertEquals(hex.getQ() - 1 ,nHex.getQ());
        assertEquals(hex.getR(), nHex.getR());

        Hex nHex2 = Hex.hexOfNeighbor(hex, 1);
        assertNotNull(nHex2);
        assertEquals(hex.getQ(),nHex2.getQ());
        assertEquals(hex.getR() + 1, nHex2.getR());

        Hex nHex3 = Hex.hexOfNeighbor(hex, 2);
        assertNotNull(nHex3);
        assertEquals(hex.getQ() + 1 ,nHex3.getQ());
        assertEquals(hex.getR() - 1 , nHex3.getR());

        Hex nHex4 = Hex.hexOfNeighbor(hex, 3);
        assertNotNull(nHex4);
        assertEquals(hex.getQ() + 1 ,nHex4.getQ());
        assertEquals(hex.getR(), nHex4.getR());

        Hex nHex5 = Hex.hexOfNeighbor(hex, 4);
        assertNotNull(nHex5);
        assertEquals(hex.getQ(),nHex5.getQ());
        assertEquals(hex.getR() + 1, nHex5.getR());

        Hex nHex6 = Hex.hexOfNeighbor(hex, 5);
        assertNotNull(nHex6);
        assertEquals(hex.getQ() - 1, nHex6.getQ());
        assertEquals(hex.getR() + 1, nHex6.getR());
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
