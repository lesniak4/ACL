package model;

import model.components.Component;

import java.util.*;

public class GameObject {

    private Vector2 position;

    private Map<Class<? extends Component>, Component> components;

    public GameObject(){

        this(0f, 0f);
    }

    public GameObject(double x, double y){

        this.position = new Vector2(x, y);

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

    public double getX() {
        return position.X();
    }

    public double getY() {
        return position.Y();
    }

    public <T extends Component> T getComponent(Class<T> componentClass){
        Component c = this.components.get(componentClass);
        if(c != null){
            return componentClass.cast(c);
        }else{
            return null;
        }
    }

    public void setPosition(double newX, double newY){
        this.position.set(newX, newY);
    }
}
