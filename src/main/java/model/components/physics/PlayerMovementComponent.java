package model.components.physics;

import engine.Cmd;
import model.CanadaPhysics;
import model.GameObject;
import utils.Vector2;

import java.util.HashSet;
import java.util.Set;

public class PlayerMovementComponent extends MovementComponent{

    private PlayerInputComponent playerInputComponent;
    public PlayerMovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics , PlayerInputComponent playerInputComponent) {
        super(obj, movementSpeed, physics);
        this.playerInputComponent = playerInputComponent;
    }

    @Override
    public void update(double dt) {
        if(this.gameObject != null){
            Set<Cmd> commands = new HashSet<>(playerInputComponent.getCommands());

            if(!commands.isEmpty()) {
                for (Cmd command : commands) {
                    if (command == Cmd.UP) {
                        this.velocityX -= 1;
                        this.velocityY -= 1;
                    } else if (command == Cmd.DOWN) {
                        this.velocityX += 1;
                        this.velocityY += 1;
                    } else if (command == Cmd.LEFT) {
                        this.velocityX -= 1;
                        this.velocityY += 1;
                    } else if (command == Cmd.RIGHT) {
                        this.velocityX += 1;
                        this.velocityY -= 1;
                    }
                }
                Vector2 v = Vector2.normalize(new Vector2(velocityX, velocityY));
                this.velocityX = v.X() * movementSpeed;
                this.velocityY = v.Y() * movementSpeed;
                physics.addToUpdate(this);
            }
        }
    }
}
