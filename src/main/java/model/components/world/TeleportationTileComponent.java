package model.components.world;

import model.GameObject;
import model.components.Component;
import utils.GameConfig;
import utils.Vector2;

public class TeleportationTileComponent extends Component {

    private TeleportationTileComponent linkedTile;
    private Vector2 teleportationPos;

    public TeleportationTileComponent(GameObject obj, TeleportationTileOrientation orientation) {
        super(obj);

        GameConfig gc = GameConfig.getInstance();
        Vector2 tilePos = this.getGameObject().getPosition();
        if(orientation == TeleportationTileOrientation.LEFT){
            this.teleportationPos = new Vector2(tilePos.X() + Math.sqrt(3d) * 0.5d * gc.getTileSize(),
                                                tilePos.Y() + 1.5d * gc.getTileSize());
            System.out.println("LEFT : Pos " + tilePos + " Tp : " + teleportationPos);
        }else{
            this.teleportationPos = new Vector2(tilePos.X() + Math.sqrt(3d) * gc.getTileSize(),
                                                    tilePos.Y());
            System.out.println("RIGHT : Pos " + tilePos + " Tp : " + teleportationPos);
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
}
