package model.components.attacks;

import model.CanadaPhysics;
import model.GameObject;
import model.components.characters.StatsComponent;
import model.components.physics.MovementComponent;

public class RangeAttackComponent extends AttackComponent{

    public RangeAttackComponent(GameObject obj, StatsComponent stats, MovementComponent movement, CanadaPhysics physics, double radius, int stunDurationInMS, int lifetimeInMS) {
        super(obj, stats, movement, physics, radius, stunDurationInMS, lifetimeInMS);
    }

    @Override
    public void update() {

    }
}
