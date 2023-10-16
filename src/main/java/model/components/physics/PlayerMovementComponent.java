package model.components.physics;

import engine.Cmd;
import model.CanadaPhysics;
import model.GameObject;

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
            Set<Cmd> commands = playerInputComponent.getCommands();

            if(!commands.isEmpty()) {
                for (Cmd command : commands) {
                    if (command == Cmd.UP) {
                        this.velocityY -= movementSpeed;
                    } else if (command == Cmd.DOWN) {
                        this.velocityY += movementSpeed;
                    } else if (command == Cmd.LEFT) {
                        this.velocityX -= movementSpeed;
                    } else if (command == Cmd.RIGHT) {
                        this.velocityX += movementSpeed;
                    }
                }
                physics.addToUpdate(this);
            }
        }
    }
}
