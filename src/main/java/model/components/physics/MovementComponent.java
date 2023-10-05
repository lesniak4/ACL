package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

public class MovementComponent extends Component {

    private CanadaPhysics physics;

    public MovementComponent(GameObject obj, CanadaPhysics physics) {
        super(obj);
        this.physics = physics;
    }

    @Override
    public void update(double dt) {

        // On informe le moteur physique que le gameObject attaché à ce component doit être update
        physics.addToUpdate(this);
    }
}
