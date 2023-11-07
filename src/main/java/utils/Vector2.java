package utils;

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

    public static double squaredDistance(Vector2 a, Vector2 b){
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }

    public static double distance(Vector2 a, Vector2 b){
        return Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
    }

    public static double dot(Vector2 a, Vector2 b){
        return a.x * b.x + a.y * b.y;
    }

    @Override
    public boolean equals(Object o){
        return (o instanceof Vector2) && Math.abs(((Vector2) o).x - this.x) < 1d && Math.abs(((Vector2) o).y - this.y) < 1d;
    }

    public static Vector2 worldToScreenIso(Vector2 v){

        return new Vector2((v.X() - v.Y()), (v.X() + v.Y()) * 0.5d);
    }

    public static Vector2 normalize(Vector2 v){
        double length = Math.sqrt(v.x * v.x + v.y * v.y);
        if(length == 0d) length = 1d;
        return new Vector2(v.x / length, v.y / length);
    }

    public String toString(){
        return "(" + x + "," + y + ")";
    }

}
