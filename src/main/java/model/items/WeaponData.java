package model.items;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class WeaponData extends ItemData{

    protected int maxUse;

    public WeaponData(String name, BufferedImage sprite, ImageIcon uiIcon, double colliderRadius, int scoreValue, int maxUse) {
        super(name, sprite, uiIcon, colliderRadius, scoreValue);

        this.maxUse = maxUse;
    }

    public int getMaxUse(){
        return maxUse;
    }
}
