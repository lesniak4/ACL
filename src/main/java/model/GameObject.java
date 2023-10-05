package model;

import model.components.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameObject {

    private float posX;
    private float posY;

    private List<Component> components;

    public GameObject(){

        this(0f, 0f);
    }

    public GameObject(float x, float y){

        this.posX = x;
        this.posY = y;

        this.components = new ArrayList<>();
    }

    public void update(double dt){

        for(Component c : components){
            c.update(dt);
        }
    }

    public void addComponent(Component component){
        this.components.add(component);
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }
}
