package model.components.physics;

import engine.Cmd;
import engine.IGameController;
import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;
import utils.Vector2;

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
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    @Override
    public void update(double dt) {
        if(this.gameObject != null){
            Set<Cmd> commands = controller.getCommands();

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
                /*
                double length = Math.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY);
                this.velocityX = (this.velocityX / length) * movementSpeed;
                this.velocityY = (this.velocityY / length) * movementSpeed;
                 */
                Vector2 v = Vector2.normalize(new Vector2(velocityX, velocityY));
                this.velocityX = v.X();
                this.velocityY = v.Y();
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
        this.velocityX = 0d;
        this.velocityY = 0d;
    }
}
