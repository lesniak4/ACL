package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.PathfindingComponent;

public class MonsterMovementComponent extends MovementComponent{

    private PathfindingComponent pathfindingComponent;

    public MonsterMovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics, PathfindingComponent pathfindingComponent) {
        super(obj, movementSpeed, physics);
        this.pathfindingComponent = pathfindingComponent;
    }

    @Override
    public void update(double dt) {

    }
}
