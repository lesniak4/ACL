package model.components.physics;


import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

public abstract class MovementComponent extends Component {

    protected CanadaPhysics physics;
    protected double movementSpeed;
    protected double velocityX;
    protected double velocityY;

    public MovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics) {
        super(obj);
        this.physics = physics;
        this.movementSpeed = movementSpeed;
    }
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
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
