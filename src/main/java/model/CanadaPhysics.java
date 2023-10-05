package model;

import engine.IGamePhysics;
import model.components.physics.ColliderComponent;
import model.components.physics.MovementComponent;

import java.util.ArrayList;
import java.util.Collection;

public class CanadaPhysics implements IGamePhysics {

    private Collection<ColliderComponent> colliders;
    private Collection<MovementComponent> toUpdate;

    public CanadaPhysics(){

        colliders = new ArrayList<>();
        toUpdate = new ArrayList<>();
    }

    @Override
    public void updatePhysics(double dt) {

        for (MovementComponent c : toUpdate){
            // On effectue les opérations à faire (collisions, deplacement, ...)
            GameObject gameObject = c.getGameObject();
            gameObject.setPosition(gameObject.getX() + c.getVelocityX(), gameObject.getY() + c.getVelocityY());
        }

        toUpdate.clear();
    }

    public void addCollider(ColliderComponent collider){

        this.colliders.add(collider);
    }

    public void addToUpdate(MovementComponent movementComponent){

        this.toUpdate.add(movementComponent);
    }
}
