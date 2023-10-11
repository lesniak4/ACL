package model;

import engine.Cmd;
import model.components.physics.ColliderComponent;
import model.components.physics.MovementComponent;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanadaControllerTest {

    @Test
    public void deplacement() {

        CanadaPhysics physics = new CanadaPhysics();
        CanadaPainter painter = new CanadaPainter();
        CanadaController controller = new CanadaController();
        CanadaGame game = new CanadaGame("", painter, physics, controller, 999);


        GameObject player = GameObjectFactory.getInstance().createPlayerObject(game,20,20,painter,controller,physics);

        // RIGHT TEST
        MovementComponent m = player.getComponent(MovementComponent.class);
        controller.setKeysPressed(Cmd.UP, Cmd.DOWN, Cmd.RIGHT);

        m.update(1);
        physics.updatePhysics(1);

        assertEquals(player.getX(), 20.02d);
        assertEquals(player.getY(), 20d);

        // LEFT TEST
        controller.removeCommands(Cmd.DOWN, Cmd.RIGHT, Cmd.UP);
        controller.setKeysPressed(Cmd.LEFT);

        m.update(1);
        physics.updatePhysics(1);

        assertEquals(player.getX(), 20d);
        assertEquals(player.getY(), 20d);

        // DOWN TEST
        controller.removeCommands(Cmd.LEFT);
        controller.setKeysPressed(Cmd.DOWN);

        m.update(1);
        physics.updatePhysics(1);

        assertEquals(player.getX(), 20d);
        assertEquals(player.getY(), 20.02d);

        // UP TEST
        controller.removeCommands(Cmd.DOWN);
        controller.setKeysPressed(Cmd.UP);

        m.update(1);
        physics.updatePhysics(1);

        assertEquals(player.getX(), 20d);
        assertEquals(player.getY(), 20d);

        // DOWN-RIGHT TEST
        controller.removeCommands(Cmd.UP);
        controller.setKeysPressed(Cmd.RIGHT, Cmd.DOWN);

        m.update(20);
        physics.updatePhysics(20);

        assertEquals(player.getX(), 40d);
        assertEquals(player.getY(), 40d);
    }

}
