package model.items;

import javax.swing.*;

public interface Item {

    String getName();
    ImageIcon getIcon();
    int getCurrentAmount();
    String getUILabelContent();
    boolean shouldBeRemovedFromInventory();
}
