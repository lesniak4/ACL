package model.component.physics;

import engine.Cmd;
import engine.UIPanel;
import model.*;
import model.components.characters.StatsComponent;
import model.components.physics.PlayerMovementComponent;
import model.fsm.states.game.PlayingState;
import model.items.Inventory;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import utils.GameConfig;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerMovementComponentTest {

    private CanadaPhysics physics;
    private CanadaPainter painter;
    private CanadaController controller;
    private CanadaGame game;

    private GameObject player;

    private double invSqrt2;

    private void init(){

        physics = new CanadaPhysics();
        painter = new CanadaPainter();
        controller = new CanadaController();
        game = new CanadaGame("", painter, physics, controller);

        invSqrt2 = 1d / Math.sqrt(2d);

        player = GameObjectFactory.getInstance().createPlayerObject(game, 0, 0, painter, controller, physics, new PlayingState(game, new UIPanel(0, 0)), new Inventory());
    }

    @Test
    public void deplacementRight() {

        init();
        PlayerMovementComponent m = player.getComponent(PlayerMovementComponent.class);
        StatsComponent statsComponent = player.getComponent(StatsComponent.class);

        statsComponent.setActualSpeed(5d);

        controller.setKeysPressed(Cmd.RIGHT);
        m.update();

        assertEquals(invSqrt2 * 5d, m.getVelocityX());
        assertEquals(-invSqrt2 * 5d, m.getVelocityY());
    }

    @Test
    public void deplacementLeft() {

        init();
        PlayerMovementComponent m = player.getComponent(PlayerMovementComponent.class);
        StatsComponent statsComponent = player.getComponent(StatsComponent.class);

        statsComponent.setActualSpeed(5d);

        controller.setKeysPressed(Cmd.LEFT);
        m.update();

        assertEquals(-invSqrt2 * 5d, m.getVelocityX());
        assertEquals(invSqrt2 * 5d, m.getVelocityY());
    }

    @Test
    public void deplacementUp() {

        init();
        PlayerMovementComponent m = player.getComponent(PlayerMovementComponent.class);
        StatsComponent statsComponent = player.getComponent(StatsComponent.class);

        statsComponent.setActualSpeed(5d);

        controller.setKeysPressed(Cmd.UP);
        m.update();

        assertEquals(-invSqrt2 * 5d, m.getVelocityX());
        assertEquals(-invSqrt2 * 5d, m.getVelocityY());
    }

    @Test
    public void deplacementDown() {

        init();
        PlayerMovementComponent m = player.getComponent(PlayerMovementComponent.class);
        StatsComponent statsComponent = player.getComponent(StatsComponent.class);

        statsComponent.setActualSpeed(5d);

        controller.setKeysPressed(Cmd.DOWN);
        m.update();

        assertEquals(invSqrt2 * 5d, m.getVelocityX());
        assertEquals(invSqrt2 * 5d, m.getVelocityY());
    }

}
