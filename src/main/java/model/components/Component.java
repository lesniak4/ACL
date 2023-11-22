package model.components;

import model.GameObject;

public abstract class Component {

    protected GameObject gameObject;

    public Component(GameObject obj){

        this.gameObject = obj;
    }

    public abstract void update();

    public GameObject getGameObject(){
        return this.gameObject;
    }

    public void destroyComponent(){
        gameObject.removeComponent(this);
    }
}
