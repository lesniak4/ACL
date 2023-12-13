package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;
import model.components.attacks.DamageAreaComponent;
import model.components.attacks.HealthComponent;
import model.components.attacks.StunComponent;

import java.util.ArrayList;

public class ColliderComponent extends Component {

    private CanadaPhysics physics;
    private double radius;
    private boolean isTrigger;
    private GameObject lastCollidedObj;

    private ArrayList<ICollidable> collidableComponents;

    public ColliderComponent(GameObject obj, CanadaPhysics physics, double radius, boolean isTrigger) {
        super(obj);
        this.physics = physics;
        this.radius = radius;
        this.isTrigger = isTrigger;

        collidableComponents = new ArrayList<>();

        this.physics.addCollider(this);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void update() {

    }

    public void addCollidableComponent(ICollidable coll){
        this.collidableComponents.add(coll);
    }

    public void removeCollidableComponent(ICollidable coll){
        this.collidableComponents.remove(coll);
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void onCollisionEnter(GameObject colliderObj){

        if(this.lastCollidedObj != colliderObj){
            clearCollision();
        }
        this.lastCollidedObj = colliderObj;

        for(ICollidable c : collidableComponents){
            c.onCollisionEnter(colliderObj);
        }
    }

    public void onCollisionExit(GameObject colliderObj){

        for(ICollidable c : collidableComponents){
            c.onCollisionExit(colliderObj);
        }
    }

    public void clearCollision(){

        if(lastCollidedObj != null){
            onCollisionExit(lastCollidedObj);
            lastCollidedObj.getComponent(ColliderComponent.class).onCollisionExit(gameObject);
            lastCollidedObj = null;
        }
    }

    @Override
    public void destroyComponent(){

        physics.removeCollider(this);
    }
}
