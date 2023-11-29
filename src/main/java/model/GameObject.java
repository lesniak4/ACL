package model;

import model.components.Component;
import utils.Vector2;

import java.util.*;

public class GameObject {

    private CanadaGame game;
    private Vector2 position;
    private String name;

    private Map<Class<? extends Component>, Component> components;

    private ArrayList<Component> toAdd;
    private ArrayList<Component> toDestroy;

    public GameObject(CanadaGame game){

        this(0d, 0d, "New GameObject", game);
    }

    public GameObject(double x, double y, CanadaGame game){

        this(x, y, "New GameObject", game);
    }

    public GameObject(double x, double y, String name, CanadaGame game){

        this.game = game;
        this.position = new Vector2(x, y);
        this.name = name;

        this.components = new LinkedHashMap<>();
        this.toAdd = new ArrayList<>();
        this.toDestroy = new ArrayList<>();
    }

    public void update(){

        addComponents();
        for(Component c : components.values()){
            c.update();
        }
        destroyComponents();
    }

    public void addComponent(Component component){
        this.components.put(component.getClass(), component);
    }

    public void addComponentNextLoop(Component component){
        toAdd.add(component);
    }

    public void removeComponent(Component component){
        toDestroy.add(component);
    }

    public void addComponents(){

        for (Component comp : toAdd){
            this.components.put(comp.getClass(), comp);
        }
        toAdd.clear();
    }

    public void destroyComponents(){
        for (Component comp : toDestroy){
            this.components.remove(comp.getClass(), comp);
        }
        toDestroy.clear();
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

    @Override
    public String toString(){
        return this.name;
    }
}
