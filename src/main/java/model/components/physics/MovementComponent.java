package model.components.physics;

import engine.Cmd;
import engine.IGameController;
import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class MovementComponent extends Component {

    protected CanadaPhysics physics;

    protected IInputController controller;
    protected double movementSpeed;
    protected double velocityX;
    protected double velocityY;

    public MovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics, IInputController controller) {
        super(obj);
        this.physics = physics;
        this.controller = controller;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void update(double dt) {
        if(this.gameObject != null){
            Set<Cmd> commands = controller.getCommands();

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

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void resetVelocity(){
        this.velocityX = 0f;
        this.velocityY = 0f;
    }
}
