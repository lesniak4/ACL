package model.components.world;

import model.GameObject;
import model.items.WeaponData;

public class WeaponComponent extends ItemComponent{

    private WeaponData data;

    public WeaponComponent(GameObject obj, WeaponData data) {
        super(obj);

        this.data = data;
    }

    public WeaponData getData() {
        return data;
    }
}
