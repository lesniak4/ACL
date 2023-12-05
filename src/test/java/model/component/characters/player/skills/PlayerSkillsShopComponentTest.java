package model.component.characters.player.skills;

import data.ItemDataFactory;
import data.ItemType;
import engine.Cmd;
import engine.UIPanel;
import model.*;
import model.components.characters.StatsComponent;
import model.components.characters.player.skills.PlayerDamageModifierComponent;
import model.components.characters.player.skills.PlayerInvisibleModifierComponent;
import model.components.characters.player.skills.PlayerSkillsShopComponent;
import model.components.characters.player.skills.PlayerSpeedModifierComponent;
import model.components.physics.PlayerMovementComponent;
import model.fsm.states.game.PlayingState;
import model.items.Inventory;
import model.items.ResourceData;
import org.junit.jupiter.api.Test;
import utils.GameConfig;


import static org.junit.jupiter.api.Assertions.*;

public class PlayerSkillsShopComponentTest {

    private CanadaPhysics physics;
    private CanadaPainter painter;
    private CanadaController controller;
    private CanadaGame game;

    private GameObject player;
    private Inventory playerInv;


    private void init(){

        physics = new CanadaPhysics();
        painter = new CanadaPainter();
        controller = new CanadaController();
        game = new CanadaGame("", painter, physics, controller);

        playerInv = new Inventory();
        player = GameObjectFactory.getInstance().createPlayerObject(game, 0, 0, painter, controller, physics, new PlayingState(game, new UIPanel(0, 0)), playerInv);
    }

    @Test
    public void isSkillAvailableTrue() {

        init();
        PlayerSkillsShopComponent c = player.getComponent(PlayerSkillsShopComponent.class);

        boolean res = c.isSkillAvailable(Cmd.SKILL_1);

        assertTrue(res);
    }

    @Test
    public void isSkillAvailableFalse() {

        init();
        PlayerSkillsShopComponent c = player.getComponent(PlayerSkillsShopComponent.class);

        playerInv.add(ItemDataFactory.getResourceData(ItemType.GOLD_COINS), 20);
        controller.setKeysPressed(Cmd.SKILL_1);
        c.update();

        boolean res = c.isSkillAvailable(Cmd.SKILL_1);
        assertFalse(res);
    }

    @Test
    public void updateBuySkill1EnoughMoney() {

        init();
        PlayerSkillsShopComponent c = player.getComponent(PlayerSkillsShopComponent.class);

        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);
        playerInv.add(gold, 20);
        controller.setKeysPressed(Cmd.SKILL_1);
        c.update();
        c.getGameObject().update();

        assertFalse(c.isSkillAvailable(Cmd.SKILL_1));
        assertNotNull(player.getComponent(PlayerSpeedModifierComponent.class));
        assertEquals(20 - GameConfig.getInstance().getSkill1Cost(), playerInv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void updateBuySkill1NotEnoughMoney() {

        init();
        PlayerSkillsShopComponent c = player.getComponent(PlayerSkillsShopComponent.class);

        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);
        playerInv.add(gold, 10);
        controller.setKeysPressed(Cmd.SKILL_1);
        c.update();
        c.getGameObject().update();

        assertTrue(c.isSkillAvailable(Cmd.SKILL_1));
        assertNull(player.getComponent(PlayerSpeedModifierComponent.class));
        assertEquals(10, playerInv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void updateBuySkill2EnoughMoney() {

        init();
        PlayerSkillsShopComponent c = player.getComponent(PlayerSkillsShopComponent.class);

        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);
        playerInv.add(gold, 30);
        controller.setKeysPressed(Cmd.SKILL_2);
        c.update();
        c.getGameObject().update();

        assertFalse(c.isSkillAvailable(Cmd.SKILL_2));
        assertNotNull(player.getComponent(PlayerInvisibleModifierComponent.class));
        assertEquals(30 - GameConfig.getInstance().getSkill2Cost(), playerInv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void updateBuySkill2NotEnoughMoney() {

        init();
        PlayerSkillsShopComponent c = player.getComponent(PlayerSkillsShopComponent.class);

        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);
        playerInv.add(gold, 10);
        controller.setKeysPressed(Cmd.SKILL_2);
        c.update();
        c.getGameObject().update();

        assertTrue(c.isSkillAvailable(Cmd.SKILL_2));
        assertNull(player.getComponent(PlayerInvisibleModifierComponent.class));
        assertEquals(10, playerInv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void updateBuySkill3EnoughMoney() {

        init();
        PlayerSkillsShopComponent c = player.getComponent(PlayerSkillsShopComponent.class);

        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);
        playerInv.add(gold, 30);
        controller.setKeysPressed(Cmd.SKILL_3);
        c.update();
        c.getGameObject().update();

        assertFalse(c.isSkillAvailable(Cmd.SKILL_3));
        assertNotNull(player.getComponent(PlayerDamageModifierComponent.class));
        assertEquals(30 - GameConfig.getInstance().getSkill3Cost(), playerInv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void updateBuySkill3NotEnoughMoney() {

        init();
        PlayerSkillsShopComponent c = player.getComponent(PlayerSkillsShopComponent.class);

        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);
        playerInv.add(gold, 10);
        controller.setKeysPressed(Cmd.SKILL_3);
        c.update();
        c.getGameObject().update();

        assertTrue(c.isSkillAvailable(Cmd.SKILL_3));
        assertNull(player.getComponent(PlayerDamageModifierComponent.class));
        assertEquals(10, playerInv.getItems().get(gold).getCurrentAmount());
    }
}
