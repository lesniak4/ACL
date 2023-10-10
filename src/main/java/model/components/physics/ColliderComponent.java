package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;
import model.components.Component;

public class ColliderComponent extends Component {

    private CanadaPhysics physics;
    private double radius;

    public ColliderComponent(GameObject obj, CanadaPhysics physics, double radius) {
        super(obj);
        this.physics = physics;
        this.radius = radius;
        this.physics.addCollider(this);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void update(double dt) {

    }

}
