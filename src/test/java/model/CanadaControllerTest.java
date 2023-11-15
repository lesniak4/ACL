package model;

import controllers.InGameController;
import engine.Cmd;
import model.components.physics.MovementComponent;
import model.components.physics.PlayerMovementComponent;
import org.junit.jupiter.api.Test;
import views.InGameView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CanadaControllerTest {

    @Test
    public void deplacement() {


        CanadaPhysics physics = new CanadaPhysics();
        CanadaPainter painter = new CanadaPainter();
        CanadaController controller = new CanadaController();
        CanadaGame game = new CanadaGame("", painter, physics, controller);

        GameObject player = GameObjectFactory.getInstance().createPlayerObject(game,20,20,painter,controller,physics, new InGameController(game, new InGameView(game)));
        PlayerMovementComponent m = player.getComponent(PlayerMovementComponent.class);

        // RIGHT TEST
        controller.setKeysPressed(Cmd.UP, Cmd.DOWN, Cmd.RIGHT);

        m.update();
        physics.updatePhysics(1);

        assertEquals((int)player.getX(), 21);
        assertEquals((int)player.getY(), 18);

        // LEFT TEST
        controller.removeCommands(Cmd.DOWN, Cmd.RIGHT, Cmd.UP);
        controller.setKeysPressed(Cmd.LEFT);

        m.update();
        physics.updatePhysics(1);

        assertEquals((int)player.getX(), 19);
        assertEquals((int)player.getY(), 19);

        // DOWN TEST
        controller.removeCommands(Cmd.LEFT);
        controller.setKeysPressed(Cmd.DOWN);

        m.update();
        physics.updatePhysics(1);

        assertEquals((int)player.getX(), 19);
        assertEquals((int)player.getY(), 19);

        // UP TEST
        controller.removeCommands(Cmd.DOWN);
        controller.setKeysPressed(Cmd.UP);

        m.update();
        physics.updatePhysics(1);

        assertEquals((int)player.getX(), 19);
        assertEquals((int)player.getY(), 19);

        // DOWN-RIGHT TEST
        controller.removeCommands(Cmd.UP);
        controller.setKeysPressed(Cmd.RIGHT, Cmd.DOWN);

        m.update();
        physics.updatePhysics(1);

        assertEquals((int)player.getX(), 20);
        assertEquals((int)player.getY(), 19);


    }

}
