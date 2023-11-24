package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.rendering.HealthBarComponent;
import utils.GameConfig;
import utils.Vector2;
import model.components.ai.PathfindingComponent;
import model.world.Hex;
import views.HealthBarView;

public class MonsterMovementComponent extends MovementComponent{

    private PathfindingComponent pathfindingComponent;

    private HealthBarView healthBar;

    public MonsterMovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics, PathfindingComponent pathfindingComponent, HealthBarView healthBar) {
        super(obj, movementSpeed, physics);
        this.pathfindingComponent = pathfindingComponent;
        this.healthBar = healthBar;
    }

    public HealthBarView getView() {
        return healthBar;
    }

    @Override
    public void update() {
        healthBar.update();

        if(pathfindingComponent.isMoving()){

            Vector2 posNextMove;
            Hex nextMove = pathfindingComponent.pathFinding();
            if(nextMove != null){
                posNextMove = Hex.hexToWorldPos(nextMove, GameConfig.getInstance().getTileSize());
            }else{
                posNextMove = pathfindingComponent.getTarget();
            }

            Vector2 pos = this.getGameObject().getPosition();
            Vector2 dir = Vector2.normalize(new Vector2(posNextMove.X() - pos.X(), (posNextMove.Y() - pos.Y())));
            this.setCurrentFacingDirection(dir);
            this.velocityX = dir.X() * movementSpeed;
            this.velocityY = dir.Y() * movementSpeed;

            physics.addToUpdate(this);
        }
    }
}
