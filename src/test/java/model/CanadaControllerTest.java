package model;

import engine.Cmd;
import model.components.physics.PlayerMovementComponent;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CanadaControllerTest {

    @Ignore
    public void deplacementRight() {

    /*
        CanadaPhysics physics = new CanadaPhysics();
        CanadaPainter painter = new CanadaPainter();
        CanadaController controller = new CanadaController();
        CanadaGame game = new CanadaGame("", painter, physics, controller);

        GameObject player = GameObjectFactory.getInstance().createPlayerObject(game,0,0,painter,controller,physics);
        PlayerMovementComponent m = player.getComponent(PlayerMovementComponent.class);

        // RIGHT TEST
        controller.setKeysPressed(Cmd.RIGHT);

        m.update();
        physics.updatePhysics(1);

        assertTrue(player.getX() >= 0);

        // LEFT TEST
        controller.removeCommands(Cmd.RIGHT);
        controller.setKeysPressed(Cmd.LEFT);

        player.setPosition(0, 0);
        m.update();
        physics.updatePhysics(1);

        assertTrue(player.getX() <= 0);

        // DOWN TEST
        controller.removeCommands(Cmd.LEFT);
        controller.setKeysPressed(Cmd.DOWN);

        player.setPosition(0, 0);
        m.update();
        physics.updatePhysics(1);

        assertTrue(player.getY() >= 0);

        // UP TEST
        controller.removeCommands(Cmd.DOWN);
        controller.setKeysPressed(Cmd.UP);

        player.setPosition(0, 0);
        m.update();
        physics.updatePhysics(1);

        assertTrue(player.getY() <= 0);

        // DOWN-RIGHT TEST
        controller.removeCommands(Cmd.UP);
        controller.setKeysPressed(Cmd.RIGHT, Cmd.DOWN);

        player.setPosition(0, 0);
        m.update();
        physics.updatePhysics(1);
        m.update();
        physics.updatePhysics(1);

        assertTrue(player.getX() >= 0);
        assertTrue(player.getY() >= 0);
    */
    }

}
