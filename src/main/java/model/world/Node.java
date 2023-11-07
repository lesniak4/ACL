package model.world;

import model.*;

public class Node {
    private Hex hex;
    private int distance;

    public Node(Hex hex, int distance){
        this.hex = hex;
        this.distance = distance;
    }

    public Node(Hex hex){
        this.hex = hex;
        this.distance = Integer.MAX_VALUE;
    }
    public Hex getHex() {
        return hex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        return 0;
    }
    @Override
    public boolean equals(Object node){
        return (node instanceof Node && ((Node)node).getHex().equals(this.hex));
    }

    @Override
    public String toString(){
        return hex.toString() + '{' + distance + '}';
    }
}