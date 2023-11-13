package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

public class ColliderComponent extends Component {

    private CanadaPhysics physics;
    private double radius;
    private boolean isTrigger;

    public ColliderComponent(GameObject obj, CanadaPhysics physics, double radius, boolean isTrigger) {
        super(obj);
        this.physics = physics;
        this.radius = radius;
        this.isTrigger = isTrigger;

        this.physics.addCollider(this);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void update() {

    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void onCollisionEnter(GameObject colliderObj){

        GameObject obj = getGameObject();
        PlayerInteractionComponent player = obj.getComponent(PlayerInteractionComponent.class);

        if(player != null){
            player.interactWith(colliderObj);
        }else{
            player = colliderObj.getComponent(PlayerInteractionComponent.class);
            if(player != null){
                player.interactWith(obj);
            }
        }


    }

    @Override
    public void destroyComponent(){

        physics.removeCollider(this);
    }
}
