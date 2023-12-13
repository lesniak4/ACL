package model.components.physics;

import model.GameObject;

public interface ICollidable {

    void subscribeToCollider(ColliderComponent collider);
    void onCollisionEnter(GameObject colliderObj);
    void onCollisionExit(GameObject colliderObj);
}
