package model.items;

import data.ItemType;

import javax.swing.*;

public class Resource extends Item{

    protected ResourceData data;
    protected int currentAmount;

    public Resource(ResourceData data, int initialAmount){

        super();

        this.data = data;
        this.currentAmount = initialAmount;
    }

    @Override
    public String getName() {
        return data.getName();
    }

    @Override
    public ImageIcon getIcon() {
        return data.getIcon();
    }

    @Override
    public String getUILabelContent() {
        return Integer.toString(currentAmount);
    }

    @Override
    public boolean shouldBeRemovedFromInventory() {
        return this.currentAmount <= 0;
    }

    @Override
    public int getCurrentAmount() {
        return currentAmount;
    }

    public void add(int amount){
        this.currentAmount += amount;
    }

    public boolean remove(int amount){
        if(this.currentAmount >= amount) {
            this.currentAmount -= amount;
            return true;
        }
        return false;
    }

    public boolean isEmpty(){
        return this.currentAmount <= 0;
    }

}
