package model.components.world;

import model.GameObject;
import model.components.physics.ColliderComponent;
import model.components.physics.ICollidable;
import model.components.physics.PlayerMovementComponent;
import model.items.ItemData;
import model.items.ResourceData;

public class ResourceComponent extends ItemComponent implements ICollidable {

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

    @Override
    public void subscribeToCollider(ColliderComponent collider) {

        collider.addCollidableComponent(this);
    }

    @Override
    public void onCollisionEnter(GameObject colliderObj) {

        if(colliderObj.getComponent(PlayerMovementComponent.class) != null) {
            gameObject.getGame().getPlayerInventory().add(getData(), getAmount());
            gameObject.getGame().incrScore(getData().getScoreValue());
            gameObject.destroyGameObject();
        }
    }

    @Override
    public void onCollisionExit(GameObject colliderObj) {

    }
}


