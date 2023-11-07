package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.CoinComponent;
import model.components.Component;
import model.components.WorldExitComponent;
import model.components.rendering.CircleRendererComponent;

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
    public void update(double dt) {

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
