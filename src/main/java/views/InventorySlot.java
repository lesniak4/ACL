package views;

import model.items.Item;

import javax.swing.*;
import java.awt.*;

public class InventorySlot extends JPanel {

    private JLabel icon;
    private JLabel label;

    public InventorySlot(){

        icon = new JLabel();
        label = new JLabel();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setBackground(new Color(0,0,0,0));

        icon = new JLabel();
        label = new JLabel();

        icon.setAlignmentX(CENTER_ALIGNMENT);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setForeground(Color.WHITE);

        this.add(icon);
        this.add(label);
    }

    public void updateSlot(Item item){
        this.icon.setIcon(item.getIcon());
        this.label.setText(item.getUILabelContent());
    }

}
