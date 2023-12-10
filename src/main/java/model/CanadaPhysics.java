package model;

import engine.IGamePhysics;
import model.components.attacks.DamageAreaComponent;
import model.components.physics.ColliderComponent;
import model.components.physics.MovementComponent;
import model.components.world.SwimmingLessonComponent;
import model.components.world.WaterComponent;
import utils.Vector2;

import java.util.*;

public class CanadaPhysics implements IGamePhysics {

    private Collection<ColliderComponent> colliders;
    private Collection<MovementComponent> toUpdate;

    public CanadaPhysics(){

        colliders = new ArrayList<>();
        toUpdate = new ArrayList<>();
    }

    @Override
    public void updatePhysics(double dt) {

        for (MovementComponent m : toUpdate){
            // On effectue les opérations à faire (collisions, deplacement, ...)
            GameObject gameObject = m.getGameObject();

            Vector2 lastPos = new Vector2(gameObject.getX(), gameObject.getY());

            Vector2 velocity = new Vector2(m.getVelocityX(), m.getVelocityY());
            Vector2 newPos = new Vector2(lastPos.X() + velocity.X() * dt, lastPos.Y() + velocity.Y() * dt);
            
            gameObject.setPosition(newPos);

            ColliderComponent collider = gameObject.getComponent(ColliderComponent.class);
            if(collider != null){
                ColliderComponent firstCol = null;
                for(int i = 0; i < 2; i++) {
                    for (ColliderComponent c : colliders) {
                        if (c != collider && c != firstCol) {
                            if (areColliding(collider, c)) {
                                if (!c.isTrigger() || !m.canSwim() && c.getGameObject().getComponent(WaterComponent.class) != null) {
                                    if(firstCol == null) {
                                        newPos = positionAfterCollision(gameObject, c.getGameObject(), lastPos, velocity, dt);
                                        gameObject.setPosition(newPos);
                                    }else  {
                                        gameObject.setPosition(lastPos);
                                    }
                                }
                                if(firstCol == null) {
                                    firstCol = c;
                                }
                                collider.onCollisionEnter(c.getGameObject());
                                break;
                            }
                        }
                    }
                }
            }
            m.resetVelocity();
        }
        toUpdate.clear();
    }

    public void addCollider(ColliderComponent collider){

        this.colliders.add(collider);
    }

    public void addToUpdate(MovementComponent movementComponent){

        this.toUpdate.add(movementComponent);
    }

    public void removeCollider(ColliderComponent collider){

        this.colliders.remove(collider);
    }

    public boolean areColliding(ColliderComponent c1, ColliderComponent c2){

        GameObject go1 = c1.getGameObject();
        GameObject go2 = c2.getGameObject();

        double r1 = c1.getRadius();
        double r2 = c2.getRadius();

        return Vector2.squaredDistance(go1.getPosition(), go2.getPosition()) <= (r1+r2)*(r1+r2);//(x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) <= (r1+r2)*(r1+r2);
    }

    /**
     * permet de vider les listes d'objets à prendre en compte
     * pour les collisions et mises à jour lors d'un changement de niveau
     */
    public void reset() {
        this.colliders.clear();
        this.toUpdate.clear();
    }

    public Vector2 positionAfterCollision(GameObject toMove, GameObject collider, Vector2 lastPos, Vector2 velocity, double dt){

        // On repositionne l'objet par rapport à la tangente au point de collision
        double length = Vector2.distance(collider.getPosition(), toMove.getPosition());
        if(length == 0D) length = 1D;

        Vector2 normal = new Vector2((collider.getX() - toMove.getX()) / length, (collider.getY() - toMove.getY()) / length);
        Vector2 tanDir = new Vector2(-normal.Y(), normal.X());
        double dot = Vector2.dot(tanDir, velocity);

        // On soustrait le vecteur normal sinon il arrive qu'on passe à travers
        return new Vector2(lastPos.X() + (tanDir.X() * dot - normal.X() * 0.02) * dt,
                            lastPos.Y() + (tanDir.Y() * dot - normal.Y() * 0.02) * dt);
    }

}
