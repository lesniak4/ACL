package model.items;

import data.ItemDataFactory;
import data.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceTest {

    @Test
    public void add(){

        Resource r = new Resource(ItemDataFactory.getResourceData(ItemType.GOLD_COINS), 10);
        r.add(5);

        assertEquals(15, r.getCurrentAmount());
    }

    @Test
    public void removeTrue(){

        Resource r = new Resource(ItemDataFactory.getResourceData(ItemType.GOLD_COINS), 10);
        boolean res = r.remove(3);

        assertTrue(res);
        assertEquals(7, r.getCurrentAmount());
    }

    @Test
    public void removeTrueZero(){

        Resource r = new Resource(ItemDataFactory.getResourceData(ItemType.GOLD_COINS), 10);
        boolean res = r.remove(10);

        assertTrue(res);
        assertEquals(0, r.getCurrentAmount());
    }

    @Test
    public void removeFalse(){

        Resource r = new Resource(ItemDataFactory.getResourceData(ItemType.GOLD_COINS), 10);
        boolean res = r.remove(15);

        assertFalse(res);
        assertEquals(10, r.getCurrentAmount());
    }
}
