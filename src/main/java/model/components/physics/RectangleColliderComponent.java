package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;

public class RectangleColliderComponent extends ColliderComponent{

    protected float width;
    protected float height;

    public RectangleColliderComponent(GameObject obj, CanadaPhysics physics, float width, float height) {
        super(obj, physics);
        this.width = width;
        this.height = height;
    }
}
