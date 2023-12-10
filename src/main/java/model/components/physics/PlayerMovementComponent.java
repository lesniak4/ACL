package model.components.physics;

import engine.Cmd;
import model.CanadaPhysics;
import model.GameObject;
import model.components.characters.player.PlayerInputComponent;
import model.components.characters.StatsComponent;
import utils.Vector2;

import java.util.HashSet;
import java.util.Set;

public class PlayerMovementComponent extends MovementComponent{

    private PlayerInputComponent playerInputComponent;
    private StatsComponent stats;

    public PlayerMovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics , PlayerInputComponent playerInputComponent, StatsComponent stats, boolean canSwim) {
        super(obj, movementSpeed, physics, canSwim);
        this.playerInputComponent = playerInputComponent;
        this.stats = stats;

    }

    public void learnedSwimming(){
        this.canSwim = true;
    }

    @Override
    public void update() {

        if(this.gameObject != null){

            Set<Cmd> commands = new HashSet<>(playerInputComponent.getCommands());

            if(!commands.isEmpty()) {
                boolean pressedMovementKey = false;
                for (Cmd command : commands) {
                    if (command == Cmd.UP) {
                        this.velocityX -= 1;
                        this.velocityY -= 1;
                        pressedMovementKey = true;
                    } else if (command == Cmd.DOWN) {
                        this.velocityX += 1;
                        this.velocityY += 1;
                        pressedMovementKey = true;
                    } else if (command == Cmd.LEFT) {
                        this.velocityX -= 1;
                        this.velocityY += 1;
                        pressedMovementKey = true;
                    } else if (command == Cmd.RIGHT) {
                        this.velocityX += 1;
                        this.velocityY -= 1;
                        pressedMovementKey = true;
                    }
                }
                if(pressedMovementKey) {
                    Vector2 v = Vector2.normalize(new Vector2(velocityX, velocityY));
                    this.setCurrentFacingDirection(v);
                    this.velocityX = v.X() * stats.getActualSpeed();
                    this.velocityY = v.Y() * stats.getActualSpeed();
                    physics.addToUpdate(this);
                }
            }
        }
    }
}
