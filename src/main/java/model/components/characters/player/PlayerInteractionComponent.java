package model.components.characters.player;

import data.ItemDataFactory;
import data.ItemType;
import model.GameObject;
import model.components.characters.StatsComponent;
import model.components.physics.MonsterMovementComponent;
import model.components.world.*;
import model.components.Component;
import model.items.Inventory;

public class PlayerInteractionComponent extends Component {

    private StatsComponent stats;
    private Inventory inventory;

    public PlayerInteractionComponent(GameObject obj, StatsComponent stats, Inventory inventory) {

        super(obj);
        this.stats = stats;
        this.inventory = inventory;
    }

    public void interactWith(GameObject colliderObj){

        WorldExitComponent exit = colliderObj.getComponent(WorldExitComponent.class);
        if(exit != null && inventory.contains(ItemDataFactory.getResourceData(ItemType.AXE))){
            inventory.remove(ItemDataFactory.getResourceData(ItemType.AXE), 1);
            getGameObject().getGame().incrScore(50);
            getGameObject().getGame().setPlayerWin(true);

        }

        ResourceComponent resource = colliderObj.getComponent(ResourceComponent.class);
        if(resource != null){
            inventory.add(resource.getData(), resource.getAmount());
            colliderObj.destroyGameObject();
            getGameObject().getGame().incrScore(resource.getData().getScoreValue());
        }

        WeaponComponent weapon = colliderObj.getComponent(WeaponComponent.class);
        if(weapon != null){
            inventory.add(weapon.getData());
            colliderObj.destroyGameObject();
            getGameObject().getGame().incrScore(weapon.getData().getScoreValue());
        }

        // Check collision avec une case de téléportation
        TeleportationTileComponent tpTile = colliderObj.getComponent(TeleportationTileComponent.class);
        if (tpTile != null) {
            this.getGameObject().setPosition(tpTile.getLinkedTile().getTeleportationPos());
        }
    }

    @Override
    public void update() {

    }
}
