package model.components.attacks;

import model.GameObject;
import model.components.Component;
import model.components.physics.ColliderComponent;
import model.components.physics.ICollidable;

public class DamageAreaComponent extends Component implements ICollidable {

    private int damage;
    private int stunDuration;

    private AttackComponent owner;
    private int frameBeforeDestroy;
    private boolean destroyOnHit;

    public DamageAreaComponent(GameObject obj, int damage, int stunFrameCount, int lifetimeFrameCount, AttackComponent owner, boolean destroyOnHit) {
        super(obj);

        this.damage = damage;
        this.stunDuration = stunFrameCount;
        this.owner = owner;
        this.frameBeforeDestroy = lifetimeFrameCount;
        this.destroyOnHit = destroyOnHit;
    }

    @Override
    public void update() {

        frameBeforeDestroy--;
        if(frameBeforeDestroy <= 0){
            destroy();
        }
    }

    public void hitGameObject(GameObject obj){

        StunComponent stun = obj.getComponent(StunComponent.class);
        if(stun != null){
            stun.stun(stunDuration);
        }
        HealthComponent health = obj.getComponent(HealthComponent.class);
        if(health != null){
            health.takeDamage(damage);
        }
        ColliderComponent collider = obj.getComponent(ColliderComponent.class);
        if(health != null || stun != null || (destroyOnHit && collider != null && !collider.isTrigger())){
            destroy();
        }
    }

    public AttackComponent getOwner(){
        return this.owner;
    }

    public int getDamage() {
        return damage;
    }

    public void destroy(){
        this.owner.clearDamageArea();
        this.getGameObject().destroyGameObject();
    }

    public int getStunDuration() {
        return stunDuration;
    }

    public int getFrameBeforeDestroy(){
        return frameBeforeDestroy;
    }

    @Override
    public void subscribeToCollider(ColliderComponent collider) {

        collider.addCollidableComponent(this);
    }

    @Override
    public void onCollisionEnter(GameObject colliderObj) {

        if(colliderObj != getOwner().getGameObject()){
            hitGameObject(colliderObj);
        }
    }

    @Override
    public void onCollisionExit(GameObject colliderObj) {

    }
}
