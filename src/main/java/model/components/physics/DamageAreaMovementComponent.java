package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import utils.Vector2;

public class DamageAreaMovementComponent extends MovementComponent{

    private Vector2 direction;

    public DamageAreaMovementComponent(GameObject obj, double movementSpeed, Vector2 dir, CanadaPhysics physics) {
        super(obj, movementSpeed, physics, true);

        this.direction = dir;
    }

    @Override
    public void update() {

        this.setCurrentFacingDirection(direction);
        this.velocityX = direction.X() * movementSpeed;
        this.velocityY = direction.Y() * movementSpeed;

        physics.addToUpdate(this);
    }
}
