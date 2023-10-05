package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

public abstract class MovementComponent extends Component {

    protected CanadaPhysics physics;
    protected float movementSpeed;
    protected float velocityX;
    protected float velocityY;

    public MovementComponent(GameObject obj, CanadaPhysics physics, float movementSpeed) {
        super(obj);
        this.physics = physics;
        this.movementSpeed = movementSpeed;
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
