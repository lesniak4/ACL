package model.utils;

import org.junit.jupiter.api.Test;
import utils.Vector2;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2Test {

    @Test
    public void squaredDistanceZero() {

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
    public void squaredDistanceOne() {

        Vector2 a = new Vector2(1,0);
        Vector2 b = new Vector2(0,0);

        assertEquals(1f, Vector2.squaredDistance(a,b));
    }

    @Test
    public void squaredDistance() {

        Vector2 a = new Vector2(-3, 0);
        Vector2 b = new Vector2(-3,-6);
        assertEquals(36f, Vector2.squaredDistance(a,b));
    }



    @Test
    public void distanceZero() {

        Vector2 a = new Vector2(0,0);
        Vector2 b = new Vector2(0,0);
        assertEquals(0f, Vector2.distance(a,b));
    }

    @Test
    public void distanceOne() {

        Vector2 a = new Vector2(1,0);
        Vector2 b = new Vector2(0,0);

        assertEquals(1f, Vector2.distance(a,b));
    }

    @Test
    public void distance() {

        Vector2 a = new Vector2(-3, 0);
        Vector2 b = new Vector2(-3,-6);
        assertEquals(6f, Vector2.distance(a,b));
    }

    @Test
    public void dotZero() {

        Vector2 a = new Vector2(0,0);
        Vector2 b = new Vector2(0,0);
        assertEquals(0f, Vector2.dot(a,b));
    }

    @Test
    public void dotOne() {

        Vector2 a = new Vector2(1,0);
        Vector2 b = new Vector2(1,0);
        assertEquals(1f, Vector2.dot(a,b));
    }

    @Test
    public void dot() {

        Vector2 a = new Vector2(-3, 1);
        Vector2 b = new Vector2(-3,-6);
        assertEquals(3f, Vector2.dot(a,b));
    }

    @Test
    public void normalizeLengthOne(){

        Vector2 v = new Vector2(1, 0);
        assertEquals(1d, Vector2.normalize(v).X());
        assertEquals(0d, Vector2.normalize(v).Y());
    }

    @Test
    public void normalizeLengthZero(){

        Vector2 v = new Vector2(0, 0);
        assertEquals(0d, Vector2.normalize(v).X());
        assertEquals(0d, Vector2.normalize(v).Y());
    }

    @Test
    public void normalizeOne(){

        Vector2 v = new Vector2(1, 1);
        assertEquals(1d/Math.sqrt(2d), Vector2.normalize(v).X());
        assertEquals(1d/Math.sqrt(2d), Vector2.normalize(v).Y());
    }

    @Test
    public void normalizePositive(){

        Vector2 v = new Vector2(4, 3);
        assertEquals(0.8d, Vector2.normalize(v).X());
        assertEquals(0.6d, Vector2.normalize(v).Y());
    }

    @Test
    public void normalizeNegative(){

        Vector2 v = new Vector2(-3, -4);
        assertEquals(-0.6d, Vector2.normalize(v).X());
        assertEquals(-0.8d, Vector2.normalize(v).Y());
    }
}