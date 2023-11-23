package model.components.attacks;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

public abstract class AttackComponent extends Component {

    protected CanadaPhysics physics;

    protected double radius;
    protected int damage;
    protected int lifetimeInMS;

    protected DamageAreaComponent instantiatedDamageArea;

    public AttackComponent(GameObject obj, CanadaPhysics physics, double radius, int damage, int lifetimeInMS) {
        super(obj);

        this.physics = physics;

        this.radius = radius;
        this.damage = damage;
        this.lifetimeInMS = lifetimeInMS;
        this.instantiatedDamageArea = null;
    }

    public void clearDamageArea(){
        instantiatedDamageArea = null;
    }
}
