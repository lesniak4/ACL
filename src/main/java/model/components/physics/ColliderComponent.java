package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

public class ColliderComponent extends Component {

    private CanadaPhysics physics;
    private float radius;

    public ColliderComponent(GameObject obj, CanadaPhysics physics, float radius) {
        super(obj);
        this.physics = physics;
        this.radius = radius;
        this.physics.addCollider(this);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void update(double dt) {

    }

}
