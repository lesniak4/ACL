package model.components.characters.player;

import data.ItemDataFactory;
import data.ItemType;
import model.GameObject;
import model.components.characters.StatsComponent;
import model.components.physics.MonsterMovementComponent;
import model.components.physics.PlayerMovementComponent;
import model.components.world.*;
import model.components.Component;
import model.items.Inventory;
import utils.GameConfig;

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
            if(!inventory.contains(weapon.getData())) {
                inventory.add(weapon.getData());
                colliderObj.destroyGameObject();
                getGameObject().getGame().incrScore(weapon.getData().getScoreValue());
            }
        }

        SwimmingLessonComponent swimmingLessonComponent = colliderObj.getComponent(SwimmingLessonComponent.class);
        if(swimmingLessonComponent != null){
            PlayerMovementComponent m = gameObject.getComponent(PlayerMovementComponent.class);
            if(m != null && !m.canSwim()){
                if(!swimmingLessonComponent.isCurrentlyLearning()) {
                    System.out.println("Started Learning");
                    swimmingLessonComponent.startLearning(m);
                }
            }
        }

        // Check collision avec une case de téléportation
        TeleportationTileComponent tpTile = colliderObj.getComponent(TeleportationTileComponent.class);
        if (tpTile != null) {
            this.getGameObject().setPosition(tpTile.getLinkedTile().getTeleportationPos());
        }
    }

    public void endInteractionWith(GameObject colliderObj){

        SwimmingLessonComponent swimmingLessonComponent = colliderObj.getComponent(SwimmingLessonComponent.class);
        if(swimmingLessonComponent != null) {
            PlayerMovementComponent m = gameObject.getComponent(PlayerMovementComponent.class);
            if (m != null && !m.canSwim()) {
                if (swimmingLessonComponent.isCurrentlyLearning()) {
                    System.out.println("Stop Learning");
                    swimmingLessonComponent.stopLearning();
                }
            }
        }
    }

    @Override
    public void update() {

    }
}
