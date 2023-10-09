package model;

public class Vector2 {

    private double x;
    private double y;

    public Vector2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double X() {
        return x;
    }

    public double Y() {
        return y;
    }

    public void set(double newX, double newY){
        this.x = newX;
        this.y = newY;
    }
}
