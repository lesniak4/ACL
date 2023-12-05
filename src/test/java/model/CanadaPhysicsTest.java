package model;

import model.components.physics.ColliderComponent;
import org.junit.jupiter.api.Test;
import utils.Vector2;

import static org.junit.jupiter.api.Assertions.*;


public class CanadaPhysicsTest {

    @Test
    public void areCollidingTrue() {

        CanadaPhysics physics = new CanadaPhysics();
        CanadaGame game = new CanadaGame("", new CanadaPainter(), physics, new CanadaController());

        GameObject obj1 = new GameObject(0f, 0f, game);
        ColliderComponent coll1 = new ColliderComponent(obj1, physics, 5f, false);
        obj1.addComponent(coll1);

        GameObject obj2 = new GameObject(10f, 10f, game);
        ColliderComponent coll2 = new ColliderComponent(obj2, physics, 16f, false);
        obj2.addComponent(coll2);

        assertTrue(physics.areColliding(coll1, coll2));
    }

    @Test
    public void areCollidingFalse() {

        CanadaPhysics physics = new CanadaPhysics();
        CanadaGame game = new CanadaGame("", new CanadaPainter(), physics, new CanadaController());

        GameObject obj1 = new GameObject(0f, 0f, game);
        ColliderComponent coll1 = new ColliderComponent(obj1, physics, 5f, false);
        obj1.addComponent(coll1);

        GameObject obj2 = new GameObject(10f, 10f, game);
        ColliderComponent coll2 = new ColliderComponent(obj2, physics, 5f, false);
        obj2.addComponent(coll2);

        assertFalse(physics.areColliding(coll1, coll2));
    }

    @Test
    public void positionAfterCollision() {

        CanadaPhysics physics = new CanadaPhysics();
        CanadaGame game = new CanadaGame("", new CanadaPainter(), physics, new CanadaController());

        GameObject obj1 = new GameObject(10f, 10f, game);
        ColliderComponent coll1 = new ColliderComponent(obj1, physics, 1f, false);
        obj1.addComponent(coll1);

        GameObject obj2 = new GameObject(10f, 10f, game);
        ColliderComponent coll2 = new ColliderComponent(obj2, physics, 5f, false);
        obj2.addComponent(coll2);

        Vector2 velocity = new Vector2(10f, 10f);
        Vector2 lastPos = new Vector2(0f,0f);

        Vector2 newPos = physics.positionAfterCollision(obj1, obj2, lastPos, velocity, 1f);
        assertEquals(newPos.X(), 0f);
        assertEquals(newPos.Y(), 0f);
    }
}