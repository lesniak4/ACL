package data;

import model.items.ItemData;
import model.items.ResourceData;
import model.items.WeaponData;
import utils.SpriteLoader;

import javax.swing.*;
import java.util.HashMap;

public class ItemDataFactory {

    private static final HashMap<ItemType, ItemData> items = new HashMap<>();

    public static ResourceData getResourceData(ItemType itemType){
        ResourceData item = (ResourceData)items.get(itemType);

        if(item == null){
            if(itemType.equals(ItemType.GOLD_COINS)){
                item = new ResourceData("Coins",
                        SpriteLoader.getSprite("/sprites/gameobjects/coins.png"),
                        new ImageIcon(SpriteLoader.getSprite("/sprites/ui/coins_ui.png")),
                        12d, 5);
            }
            else if(itemType.equals(ItemType.AXE)){
                item = new ResourceData("Axe",
                        SpriteLoader.getSprite("/sprites/gameobjects/axe.png"),
                        new ImageIcon(SpriteLoader.getSprite("/sprites/ui/axe_ui.png")),
                        25d, 20);
            }
            items.put(itemType, item);
        }
        return item;
    }

    public static WeaponData getWeaponData(ItemType itemType){
        WeaponData item = (WeaponData)items.get(itemType);

        if(item == null){
            if(itemType.equals(ItemType.SWORD)){
                item = new WeaponData("Sword",
                        SpriteLoader.getSprite("/sprites/gameobjects/sword.png"),
                        new ImageIcon(SpriteLoader.getSprite("/sprites/ui/sword_ui.png")),
                        25d, 10, 30);
            }
            else if(itemType.equals(ItemType.SLINGSHOT)){
                item = new WeaponData("Slingshot",
                        SpriteLoader.getSprite("/sprites/gameobjects/slingshot.png"),
                        new ImageIcon(SpriteLoader.getSprite("/sprites/ui/slingshot_ui.png")),
                        25d, 10, 20);
            }
            items.put(itemType, item);
        }
        return item;
    }


}
