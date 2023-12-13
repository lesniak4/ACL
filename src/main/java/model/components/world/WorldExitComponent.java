package model.components.world;

import data.ItemDataFactory;
import data.ItemType;
import model.GameObject;
import model.components.Component;
import model.components.physics.ColliderComponent;
import model.components.physics.ICollidable;
import model.components.physics.PlayerMovementComponent;

public class WorldExitComponent extends Component implements ICollidable {

    public WorldExitComponent(GameObject obj) {
        super(obj);
    }

    @Override
    public void update() {

    }

    @Override
    public void subscribeToCollider(ColliderComponent collider) {

        collider.addCollidableComponent(this);
    }

    @Override
    public void onCollisionEnter(GameObject colliderObj) {

        if(colliderObj.getComponent(PlayerMovementComponent.class) != null) {
            if(gameObject.getGame().getPlayerInventory().contains(ItemDataFactory.getResourceData(ItemType.AXE))){
                gameObject.getGame().getPlayerInventory().remove(ItemDataFactory.getResourceData(ItemType.AXE), 1);
                gameObject.getGame().incrScore(50);
                gameObject.getGame().setPlayerWin(true);
            }
        }
    }

    @Override
    public void onCollisionExit(GameObject colliderObj) {

    }
}
