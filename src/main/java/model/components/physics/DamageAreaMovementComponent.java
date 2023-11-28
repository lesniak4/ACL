package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;

public class DamageAreaMovementComponent extends MovementComponent{

    public DamageAreaMovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics) {
        super(obj, movementSpeed, physics);
    }

    @Override
    public void update() {

        physics.addToUpdate(this);
    }
}
