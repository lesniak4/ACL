package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;
import model.components.attacks.DamageAreaComponent;
import model.components.attacks.HealthComponent;
import model.components.attacks.StunComponent;
import model.components.characters.player.PlayerInteractionComponent;

public class ColliderComponent extends Component {

    private CanadaPhysics physics;
    private double radius;
    private boolean isTrigger;
    private GameObject lastCollidedObj;

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

        DamageAreaComponent damageArea = obj.getComponent(DamageAreaComponent.class);
        if(damageArea != null && colliderObj != damageArea.getOwner().getGameObject()){
            damageArea.hitGameObject(colliderObj);
        }

    }

    public void onCollisionExit(GameObject colliderObj){

        GameObject obj = getGameObject();
        PlayerInteractionComponent player = obj.getComponent(PlayerInteractionComponent.class);

        if(player != null){
            player.endInteractionWith(colliderObj);
        }else{
            player = colliderObj.getComponent(PlayerInteractionComponent.class);
            if(player != null){
                player.endInteractionWith(obj);
            }
        }
    }

    public void clearCollision(){

        if(lastCollidedObj != null){
            onCollisionExit(lastCollidedObj);
            lastCollidedObj = null;
        }
    }

    @Override
    public void destroyComponent(){

        physics.removeCollider(this);
    }
}
