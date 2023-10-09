package model;

import model.components.Component;

import java.util.*;

public class GameObject {

    private float posX;
    private float posY;

    private Map<Class<? extends Component>, Component> components;

    public GameObject(){

        this(0f, 0f);
    }

    public GameObject(float x, float y){

        this.posX = x;
        this.posY = y;

        this.components = new LinkedHashMap<>();
    }

    public void update(double dt){

        for(Component c : components.values()){
            c.update(dt);
        }
    }

    public void addComponent(Component component){
        this.components.put(component.getClass(), component);
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

    public <T extends Component> T getComponent(Class<T> componentClass){
        Component c = this.components.get(componentClass);
        if(c != null){
            return componentClass.cast(c);
        }else{
            return null;
        }
    }

    public void setPosition(float newX, float newY){
        this.posX = newX;
        this.posY = newY;
    }
}
