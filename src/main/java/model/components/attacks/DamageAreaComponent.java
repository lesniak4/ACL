package model.components.attacks;

import model.GameObject;
import model.components.Component;

public class DamageAreaComponent extends Component {

    private int damage;
    private int stunDuration;
    private int lifetime;

    private AttackComponent owner;
    private long spawnTime;

    public DamageAreaComponent(GameObject obj, int damage, int stunDurationInMS, int lifetimeInMS, AttackComponent owner) {
        super(obj);

        this.damage = damage;
        this.stunDuration = stunDurationInMS;
        this.lifetime = lifetimeInMS;
        this.owner = owner;
        this.spawnTime = System.currentTimeMillis();
    }

    @Override
    public void update() {

        if(System.currentTimeMillis() - spawnTime > lifetime){
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
}
