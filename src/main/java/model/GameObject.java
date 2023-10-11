package model;

import model.components.Component;

import java.util.*;

public class GameObject {

    private CanadaGame game;
    private Vector2 position;

    private Map<Class<? extends Component>, Component> components;

    public GameObject(CanadaGame game){

        this(0d, 0d, game);
    }

    public GameObject(double x, double y, CanadaGame game){

        this.game = game;
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

    public CanadaGame getGame(){
        return this.game;
    }

    public double getX() {
        return position.X();
    }

    public double getY() {
        return position.Y();
    }

    public Vector2 getPosition() { return this.position; }

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

    public void setPosition(Vector2 newPos){
        this.position = newPos;
    }

    public void destroyGameObject(){

        for(Component c : components.values()){
            c.destroyComponent();
        }
        game.removeGameObject(this);
    }
}
