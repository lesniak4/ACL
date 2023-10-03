package model;

import model.components.IComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GameObject {

    private float posX;
    private float posY;

    private List<IComponent> components;

    public GameObject(){

        this(0f, 0f);
    }

    public GameObject(float x, float y, IComponent... components){

        this.posX = x;
        this.posY = y;

        this.components = new ArrayList<>();
        if(components.length > 0){
            this.components.addAll(Arrays.asList(components));
        }
    }

    public void update(double dt){

        for(IComponent c : components){
            c.update(this, dt);
        }
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }
}
