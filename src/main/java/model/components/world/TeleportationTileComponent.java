package model.components.world;

import model.GameObject;
import model.components.Component;
import model.components.physics.ColliderComponent;
import model.components.physics.ICollidable;
import model.components.physics.PlayerMovementComponent;
import utils.GameConfig;
import utils.Vector2;

public class TeleportationTileComponent extends Component implements ICollidable {

    private TeleportationTileComponent linkedTile;
    private Vector2 teleportationPos;

    public TeleportationTileComponent(GameObject obj, TeleportationTileOrientation orientation) {
        super(obj);

        GameConfig gc = GameConfig.getInstance();
        Vector2 tilePos = this.getGameObject().getPosition();
        if(orientation == TeleportationTileOrientation.LEFT){
            this.teleportationPos = new Vector2(tilePos.X() + Math.sqrt(3d) * 0.5d * gc.getTileSize(),
                                                tilePos.Y() + 1.5d * gc.getTileSize());
        }else{
            this.teleportationPos = new Vector2(tilePos.X() + Math.sqrt(3d) * gc.getTileSize(),
                                                    tilePos.Y());
        }

    }

    @Override
    public void update() {

    }

    public void setLinkedTile(TeleportationTileComponent linkedTile){
        this.linkedTile = linkedTile;
    }

    public Vector2 getTeleportationPos() {
        return teleportationPos;
    }

    public TeleportationTileComponent getLinkedTile() {
        return linkedTile;
    }

    @Override
    public void subscribeToCollider(ColliderComponent collider) {

        collider.addCollidableComponent(this);
    }

    @Override
    public void onCollisionEnter(GameObject colliderObj) {

        if(colliderObj.getComponent(PlayerMovementComponent.class) != null) {
            colliderObj.setPosition(getLinkedTile().getTeleportationPos());
        }
    }

    @Override
    public void onCollisionExit(GameObject colliderObj) {

    }
}
