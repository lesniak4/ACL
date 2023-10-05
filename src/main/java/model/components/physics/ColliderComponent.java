package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

public abstract class ColliderComponent extends Component {

    private CanadaPhysics physics;

    public ColliderComponent(GameObject obj, CanadaPhysics physics) {
        super(obj);
        this.physics = physics;

        this.physics.addCollider(this);
    }

    @Override
    public void update(double dt) {

    }

}
