package model.components.world;

import model.GameObject;
import model.components.physics.ColliderComponent;
import model.components.physics.ICollidable;
import model.components.physics.PlayerMovementComponent;
import model.items.WeaponData;

public class WeaponComponent extends ItemComponent implements ICollidable {

    private WeaponData data;

    public WeaponComponent(GameObject obj, WeaponData data) {
        super(obj);

        this.data = data;
    }

    public WeaponData getData() {
        return data;
    }

    @Override
    public void subscribeToCollider(ColliderComponent collider) {

        collider.addCollidableComponent(this);
    }

    @Override
    public void onCollisionEnter(GameObject colliderObj) {

        if(colliderObj.getComponent(PlayerMovementComponent.class) != null) {
            if(!gameObject.getGame().getPlayerInventory().contains(getData())) {
                gameObject.getGame().getPlayerInventory().add(getData());
                gameObject.getGame().incrScore(getData().getScoreValue());
                gameObject.destroyGameObject();
            }
        }
    }

    @Override
    public void onCollisionExit(GameObject colliderObj) {

    }
}
