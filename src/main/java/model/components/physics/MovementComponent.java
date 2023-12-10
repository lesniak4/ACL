package model.components.physics;


import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;
import utils.Vector2;

public abstract class MovementComponent extends Component {

    protected CanadaPhysics physics;
    protected double movementSpeed;
    protected double velocityX;
    protected double velocityY;
    protected Vector2 currentFacingDirection;

    protected boolean canSwim;

    public MovementComponent(GameObject obj, double movementSpeed, CanadaPhysics physics, boolean canSwim) {
        super(obj);
        this.physics = physics;
        this.movementSpeed = movementSpeed;
        this.currentFacingDirection = new Vector2(0,0);
        this.canSwim = canSwim;
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

    public boolean isMoving(){
        return this.velocityX != 0 || this.velocityY != 0;
    }

    public boolean canSwim() {
        return canSwim;
    }

    public Vector2 getCurrentFacingDirection(){
        return this.currentFacingDirection;
    }

    public void setCurrentFacingDirection(Vector2 currentFacingDirection){
        this.currentFacingDirection = currentFacingDirection;
    }

    public double getDirectionAngle(){
        return Math.atan2(this.currentFacingDirection.X(), this.currentFacingDirection.Y());
    }
}
