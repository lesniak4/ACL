package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import utils.Vector2;
import model.components.ai.PathfindingComponent;
import model.world.Hex;

public class MonsterMovementComponent extends MovementComponent{

    private PathfindingComponent pathfindingComponent;

    public MonsterMovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics, PathfindingComponent pathfindingComponent) {
        super(obj, movementSpeed, physics);
        this.pathfindingComponent = pathfindingComponent;
    }

    @Override
    public void update(double dt) {
        if(pathfindingComponent.isMoving()){

            Vector2 posNextMove;
            Hex nextMove = pathfindingComponent.pathFinding();
            if(nextMove != null){
                posNextMove = Hex.hexToWorldPos(nextMove, pathfindingComponent.getWorld().getTileSize());
            }else{
                posNextMove = pathfindingComponent.getTarget();
            }

            Vector2 pos = this.getGameObject().getPosition();
            Vector2 dir = Vector2.normalize(new Vector2(posNextMove.X() - pos.X(), (posNextMove.Y() - pos.Y())));
            this.velocityX = dir.X() * movementSpeed;
            this.velocityY = dir.Y() * movementSpeed;

            physics.addToUpdate(this);
        }
    }
}
