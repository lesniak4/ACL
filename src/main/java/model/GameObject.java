package model;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private float posX;
    private float posY;

    private List<IComponent> components;

    public GameObject(){

        this(0f, 0f);
    }

    public GameObject(float x, float y){

        this.posX = x;
        this.posY = y;

        this.components = new ArrayList<>();
    }

    public void update(double dt){

        for(IComponent c : components){
            c.update(this, dt);
        }
    }
}
