package model.components.physics;

import engine.Cmd;
import engine.IGameController;
import model.CanadaPhysics;
import model.GameObject;

import java.util.LinkedList;

public class PlayerComponent extends MovementComponent {

    protected IGameController controller;

    public PlayerComponent(GameObject obj, CanadaPhysics physics, float movementSpeed, IGameController controller){
        super(obj, physics, movementSpeed);
        this.controller = controller;
        this.velocityX = 0f;
        this.velocityY = 0f;
    }

    @Override
    public void update(double dt) {
        if(this.gameObject != null){
            LinkedList<Cmd> commands = controller.getCommands();

            while (!commands.isEmpty()) {
                Cmd command = commands.removeFirst();

                System.out.println(command);

                switch (command) {
                    case STOP_DOWN:
                    case UP:
                        if (this.velocityY - movementSpeed >= -movementSpeed)
                            this.velocityY -= movementSpeed;
                        break;
                    case DOWN:
                    case STOP_UP:
                        if (this.velocityY + movementSpeed <= movementSpeed)
                            this.velocityY += movementSpeed;
                        break;
                    case LEFT:
                    case STOP_RIGHT:
                        if (this.velocityX - movementSpeed >= -movementSpeed)
                            this.velocityX -= movementSpeed;
                        break;
                    case RIGHT:
                    case STOP_LEFT:
                        if (this.velocityX + movementSpeed <= movementSpeed)
                            this.velocityX += movementSpeed;
                        break;
                    default:
                        break;
                }
            }
            physics.addToUpdate(this);
        }
    }
}