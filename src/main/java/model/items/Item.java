package model.items;

import javax.swing.*;

public abstract class Item {

    public Item(){}

    public abstract String getName();

    public abstract ImageIcon getIcon();

    public abstract int getCurrentAmount();

    public abstract String getUILabelContent();

    public abstract boolean shouldBeRemovedFromInventory();

}
