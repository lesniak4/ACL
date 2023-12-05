package model.items;

import data.ItemDataFactory;
import data.ItemType;
import org.junit.jupiter.api.Test;
import utils.GameConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeaponTest {

    @Test
    public void use(){

        WeaponData data = ItemDataFactory.getWeaponData(ItemType.SWORD);
        Weapon w = new Weapon(data);
        w.use();

        assertEquals(data.getMaxUse() - 1, w.getRemainingUse());
    }
}
