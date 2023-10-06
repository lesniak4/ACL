package model.components.physics;

import engine.Cmd;
import engine.IGameController;
import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

import java.util.HashMap;
import java.util.LinkedList;

public class MovementComponent extends Component {

    protected CanadaPhysics physics;

    protected IInputController controller;
    protected float movementSpeed;
    protected float velocityX;
    protected float velocityY;

    public MovementComponent(GameObject obj, CanadaPhysics physics, float movementSpeed, IInputController controller) {
        super(obj);
        this.physics = physics;
        this.controller = controller;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void update(double dt) {
        if(this.gameObject != null){
            LinkedList<Cmd> commands = controller.getCommands();

            while (!commands.isEmpty()) {
                Cmd command = commands.removeFirst();

                switch (command) {
                    case UP:
                        this.velocityY -= movementSpeed * (float)dt;
                        break;
                    case DOWN:
                        this.velocityY += movementSpeed * (float)dt;
                        break;
                    case LEFT:
                        this.velocityX -= movementSpeed * (float)dt;
                        break;
                    case RIGHT:
                        this.velocityX += movementSpeed * (float)dt;
                        break;
                    default:
                        break;
                }
            }
            physics.addToUpdate(this);
        }
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void resetVelocity(){
        this.velocityX = 0f;
        this.velocityY = 0f;
    }
}
