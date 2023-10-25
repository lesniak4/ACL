package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import utils.Vector2;
import model.components.PathfindingComponent;
import model.world.Hex;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class MonsterMovementComponent extends MovementComponent{

    private PathfindingComponent pathfindingComponent;

    public MonsterMovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics, PathfindingComponent pathfindingComponent) {
        super(obj, movementSpeed, physics);
        this.pathfindingComponent = pathfindingComponent;
    }

    @Override
    public void update(double dt) {
        if(pathfindingComponent.isMoving()){
            LinkedList<Hex> moves =  pathfindingComponent.pathFinding();

            Vector2 posNextMove;
            if(!moves.isEmpty()) {
                Hex nextMove = moves.pop();
                posNextMove = Hex.hexToWorldPos(nextMove, pathfindingComponent.getWorld().getTileSize());
            }else {
                posNextMove = pathfindingComponent.getTarget();
            }

            Vector2 pos = this.getGameObject().getPosition();
            double dst = Vector2.distance(pos, posNextMove);

            this.velocityX = -((pos.X() - posNextMove.X()) / dst) * movementSpeed;
            this.velocityY = -((pos.Y() - posNextMove.Y()) / dst) * movementSpeed;

            physics.addToUpdate(this);
        }
    }
}
