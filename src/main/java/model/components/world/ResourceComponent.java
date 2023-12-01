package model.components.world;

import model.GameObject;
import model.items.ItemData;
import model.items.ResourceData;

public class ResourceComponent extends ItemComponent{

    private ResourceData data;
    private int amount;

    public ResourceComponent(GameObject obj, ResourceData data, int amount) {
        super(obj);

        this.data = data;
        this.amount = amount;
    }

    public ResourceData getData() {
        return data;
    }

    public int getAmount() {
        return amount;
    }
}


