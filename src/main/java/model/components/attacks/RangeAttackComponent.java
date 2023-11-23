package model.components.attacks;

import model.CanadaPhysics;
import model.GameObject;

public class RangeAttackComponent extends AttackComponent{

    public RangeAttackComponent(GameObject obj, CanadaPhysics physics, double radius, int damage, int lifetimeInMS) {
        super(obj, physics, radius, damage, lifetimeInMS);
    }

    @Override
    public void update() {

    }
}
