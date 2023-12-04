package views;

import model.CanadaGame;
import model.items.Inventory;
import model.items.Item;
import utils.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class InventoryView extends UIView {

    private Inventory inventory;

    private HashMap<String, InventorySlot> slots;

    public InventoryView(CanadaGame game, Inventory inventory){

        super(game);
        this.inventory = inventory;
        this.slots = new HashMap<>(4);
    }

    @Override
    public void buildView(){

        GameConfig gc = GameConfig.getInstance();

        this.setSize(gc.getWinWidth()/5, (int)(gc.getWinHeight()* 0.1));
        this.setBackground(new Color(40,40,40,190));

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    }

    @Override
    public void update() {

        List<Item> items = new ArrayList<>(inventory.getItemsValues());
        if(items.isEmpty() || items.size() != slots.size()){
            this.removeAll();
            slots.clear();
            populateInventoryView(items);
        }else{
            for(Item item : items){
                if(slots.containsKey(item.getName())){
                    slots.get(item.getName()).updateSlot(item);
                }
            }
        }

    }

    public void populateInventoryView(List<Item> items){

        for(int i = 0; i < items.size(); i++){
            Item item = items.get(i);
            InventorySlot slot = new InventorySlot();
            slot.updateSlot(item);
            this.slots.put(item.getName(), slot);

            this.add(Box.createRigidArea(new Dimension(15,0)));
            this.add(slot);
        }
    }
}
