package model.items;

import java.util.Collection;
import java.util.HashMap;

public class Inventory {

    private HashMap<ItemData, Item> items;

    public Inventory() {

        this.items = new HashMap<>();
    }


    public void add(ResourceData item, int amount){

        if(items.containsKey(item)){
            ((Resource)items.get(item)).add(amount);
        }else{
            items.put(item, new Resource(item, amount));
        }
    }

    public void add(WeaponData item){

        if(!items.containsKey(item)){
            items.put(item, new Weapon(item));
        }
    }

    public boolean remove(ResourceData item, int amount){

        if(!items.containsKey(item)){
            return false;
        }else{
            boolean success = ((Resource)items.get(item)).remove(amount);
            if(items.get(item).shouldBeRemovedFromInventory()){
                items.remove(item);
            }
            return success;
        }
    }

    public boolean use(WeaponData item){

        ((Weapon)items.get(item)).use();
        if(items.get(item).shouldBeRemovedFromInventory()){
            items.remove(item);
            return true;
        }
        return false;

    }

    public boolean contains(ItemData item){
        return items.containsKey(item);
    }

    public boolean contains(ItemData item, int amount){
        return items.containsKey(item) && items.get(item).getCurrentAmount() >= amount;
    }

    public Collection<Item> getItems(){
        return items.values();
    }

    public void clear(){
        items.clear();
    }
}
