package model.components.attacks;

import model.GameObject;
import model.components.Component;

public class DamageAreaComponent extends Component {

    private int damage;
    private int stunDuration;

    private AttackComponent owner;
    private int frameBeforeDestroy;

    public DamageAreaComponent(GameObject obj, int damage, int stunFrameCount, int lifetimeFrameCount, AttackComponent owner) {
        super(obj);

        this.damage = damage;
        this.stunDuration = stunFrameCount;
        this.owner = owner;
        this.frameBeforeDestroy = lifetimeFrameCount;
    }

    @Override
    public void update() {

        frameBeforeDestroy--;
        if(frameBeforeDestroy <= 0){
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
