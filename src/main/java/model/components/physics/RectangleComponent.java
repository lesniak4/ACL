package model.components.physics;

import model.CanadaPhysics;
import model.GameObject;

public class RectangleComponent extends ColliderComponent{

    protected float width;
    protected float height;

    public RectangleComponent(GameObject obj, CanadaPhysics physics, float width, float height) {
        super(obj, physics);
        this.width = width;
        this.height = height;
    }
}
