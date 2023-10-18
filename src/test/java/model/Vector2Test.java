package model;

import org.junit.jupiter.api.Test;
import utils.Vector2;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2Test {

    @Test
    public void squaredDistance() {

        Vector2 a = new Vector2(0,0);
        Vector2 b = new Vector2(0,0);
        assertEquals(0f, Vector2.squaredDistance(a,b));

        a = new Vector2(1,0);
        b = new Vector2(0,0);
        assertEquals(1f, Vector2.squaredDistance(a,b));

        a = new Vector2(-3, 0);
        b = new Vector2(-3,-6);
        assertEquals(36f, Vector2.squaredDistance(a,b));
    }

    @Test
    public void distance() {

        Vector2 a = new Vector2(0,0);
        Vector2 b = new Vector2(0,0);
        assertEquals(0f, Vector2.distance(a,b));

        a = new Vector2(1,0);
        b = new Vector2(0,0);
        assertEquals(1f, Vector2.distance(a,b));

        a = new Vector2(-3, 0);
        b = new Vector2(-3,-6);
        assertEquals(6f, Vector2.distance(a,b));
    }

    @Test
    public void dot() {

        Vector2 a = new Vector2(0,0);
        Vector2 b = new Vector2(0,0);
        assertEquals(0f, Vector2.dot(a,b));

        a = new Vector2(1,0);
        b = new Vector2(1,0);
        assertEquals(1f, Vector2.dot(a,b));

        a = new Vector2(-3, 1);
        b = new Vector2(-3,-6);
        assertEquals(3f, Vector2.dot(a,b));
    }
}