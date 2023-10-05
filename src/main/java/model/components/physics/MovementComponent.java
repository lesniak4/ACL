package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

public class MovementComponent extends Component {

    private CanadaPhysics physics;

    private float movementSpeed;
    private float velocityX;
    private float velocityY;

    public MovementComponent(GameObject obj, CanadaPhysics physics, float movementSpeed) {
        super(obj);
        this.physics = physics;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void update(double dt) {

        // On calcule le vecteur velocity selon l'input

        // On informe le moteur physique que le gameObject attaché à ce component doit être update
        physics.addToUpdate(this);
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
