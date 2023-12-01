package model.items;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ResourceData extends ItemData{

    public ResourceData(String name, BufferedImage sprite, ImageIcon uiIcon, double colliderRadius, int scoreValue) {
        super(name, sprite, uiIcon, colliderRadius, scoreValue);
    }
}
