package model.items;

import data.ItemDataFactory;
import data.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    public void addResourceEmptyInvNotExisting(){

        Inventory inv = new Inventory();
        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);

        inv.add(gold, 10);

        assertTrue(inv.contains(gold));
        assertEquals(1, inv.getItems().size());
        assertEquals(10, inv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void addResourceExisting(){

        Inventory inv = new Inventory();
        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);

        inv.add(gold, 10);
        inv.add(gold, 7);

        assertTrue(inv.contains(ItemDataFactory.getResourceData(ItemType.GOLD_COINS)));
        assertEquals(1, inv.getItems().size());
        assertEquals(17, inv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void addResourceNotEmptyInvNotExisting(){

        Inventory inv = new Inventory();
        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);
        ResourceData axe = ItemDataFactory.getResourceData(ItemType.AXE);

        inv.add(gold, 10);
        inv.add(axe, 1);

        assertTrue(inv.contains(gold));
        assertTrue(inv.contains(axe));
        assertEquals(2, inv.getItems().size());
        assertEquals(1, inv.getItems().get(axe).getCurrentAmount());
        assertEquals(10, inv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void addWeaponEmptyInvNotExisting(){

        Inventory inv = new Inventory();
        WeaponData sword = ItemDataFactory.getWeaponData(ItemType.SWORD);

        inv.add(sword);

        assertTrue(inv.contains(sword));
        assertEquals(1, inv.getItems().size());
        assertEquals(1, inv.getItems().get(sword).getCurrentAmount());
    }

    @Test
    public void addWeaponExisting(){

        Inventory inv = new Inventory();
        WeaponData sword = ItemDataFactory.getWeaponData(ItemType.SWORD);

        inv.add(sword);
        inv.add(sword);

        assertTrue(inv.contains(sword));
        assertEquals(1, inv.getItems().size());
        assertEquals(1, inv.getItems().get(sword).getCurrentAmount());
    }

    @Test
    public void addWeaponNotEmptyInvNotExisting(){

        Inventory inv = new Inventory();
        WeaponData sword = ItemDataFactory.getWeaponData(ItemType.SWORD);
        WeaponData slingshot = ItemDataFactory.getWeaponData(ItemType.SLINGSHOT);

        inv.add(sword);
        inv.add(slingshot);

        assertTrue(inv.contains(sword));
        assertTrue(inv.contains(slingshot));
        assertEquals(2, inv.getItems().size());
        assertEquals(1, inv.getItems().get(sword).getCurrentAmount());
        assertEquals(1, inv.getItems().get(slingshot).getCurrentAmount());
    }

    @Test
    public void removeResourceTrue(){

        Inventory inv = new Inventory();
        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);

        inv.add(gold, 10);
        boolean res = inv.remove(gold, 4);

        assertTrue(res);
        assertTrue(inv.contains(gold));
        assertEquals(1, inv.getItems().size());
        assertEquals(6, inv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void removeResourceTrueDelete(){

        Inventory inv = new Inventory();
        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);

        inv.add(gold, 10);
        boolean res = inv.remove(gold, 10);

        assertTrue(res);
        assertFalse(inv.contains(gold));
        assertEquals(0, inv.getItems().size());
    }

    @Test
    public void removeResourceFalseDontExist(){

        Inventory inv = new Inventory();
        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);
        ResourceData axe = ItemDataFactory.getResourceData(ItemType.AXE);

        inv.add(gold, 10);
        boolean res = inv.remove(axe, 4);

        assertFalse(res);
        assertTrue(inv.contains(gold));
        assertEquals(1, inv.getItems().size());
        assertEquals(10, inv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void removeResourceFalseNotEnough(){

        Inventory inv = new Inventory();
        ResourceData gold = ItemDataFactory.getResourceData(ItemType.GOLD_COINS);

        inv.add(gold, 10);
        boolean res = inv.remove(gold, 12);

        assertFalse(res);
        assertTrue(inv.contains(gold));
        assertEquals(1, inv.getItems().size());
        assertEquals(10, inv.getItems().get(gold).getCurrentAmount());
    }

    @Test
    public void useWeaponTrue(){

        Inventory inv = new Inventory();
        WeaponData sword = ItemDataFactory.getWeaponData(ItemType.SWORD);

        inv.add(sword);
        boolean res = inv.use(sword);

        assertTrue(res);
        assertTrue(inv.contains(sword));
        assertEquals(1, inv.getItems().size());
        assertEquals(sword.getMaxUse() - 1, ((Weapon)inv.getItems().get(sword)).getRemainingUse());
    }

    @Test
    public void useWeaponTrueDelete(){

        Inventory inv = new Inventory();
        WeaponData sword = ItemDataFactory.getWeaponData(ItemType.SWORD);

        inv.add(sword);
        for(int i = 0; i < sword.getMaxUse()-1; i++){
            inv.use(sword);
        }
        boolean res = inv.use(sword);

        assertTrue(res);
        assertFalse(inv.contains(sword));
        assertEquals(0, inv.getItems().size());
    }

    @Test
    public void useWeaponTrueTwoItems(){

        Inventory inv = new Inventory();
        WeaponData sword = ItemDataFactory.getWeaponData(ItemType.SWORD);
        WeaponData slingshot = ItemDataFactory.getWeaponData(ItemType.SLINGSHOT);

        inv.add(sword);
        inv.add(slingshot);
        boolean res = inv.use(sword);

        assertTrue(res);
        assertTrue(inv.contains(sword));
        assertTrue(inv.contains(slingshot));
        assertEquals(2, inv.getItems().size());
        assertEquals(sword.getMaxUse() - 1, ((Weapon)inv.getItems().get(sword)).getRemainingUse());
        assertEquals(slingshot.getMaxUse(), ((Weapon)inv.getItems().get(slingshot)).getRemainingUse());
    }

    @Test
    public void useWeaponFalseEmptyInv(){

        Inventory inv = new Inventory();
        WeaponData slingshot = ItemDataFactory.getWeaponData(ItemType.SLINGSHOT);

        boolean res = inv.use(slingshot);

        assertFalse(res);
        assertFalse(inv.contains(slingshot));
        assertEquals(0, inv.getItems().size());
    }

    @Test
    public void useWeaponFalseNotEmptyInv(){

        Inventory inv = new Inventory();
        WeaponData sword = ItemDataFactory.getWeaponData(ItemType.SWORD);
        WeaponData slingshot = ItemDataFactory.getWeaponData(ItemType.SLINGSHOT);

        inv.add(sword);
        boolean res = inv.use(slingshot);

        assertFalse(res);
        assertTrue(inv.contains(sword));
        assertFalse(inv.contains(slingshot));
        assertEquals(1, inv.getItems().size());
        assertEquals(sword.getMaxUse(), ((Weapon)inv.getItems().get(sword)).getRemainingUse());
    }

}
