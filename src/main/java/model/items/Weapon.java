package model.items;

import javax.swing.*;

public class Weapon extends Item{

    protected WeaponData data;
    protected int remainingUse;

    public Weapon(WeaponData data) {
        super();

        this.data = data;
        this.remainingUse = data.getMaxUse();
    }

    public void use(){
        remainingUse--;
    }

    @Override
    public boolean shouldBeRemovedFromInventory() {
        return this.remainingUse <= 0;
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
    public int getCurrentAmount() {
        return 1;
    }

    @Override
    public String getUILabelContent(){
        return remainingUse + "/" + data.maxUse;
    }
}
