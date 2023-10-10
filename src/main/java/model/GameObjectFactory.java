package model;

import model.components.physics.ColliderComponent;
import model.components.physics.MovementComponent;
import model.components.physics.PlayerInputComponent;
import model.components.rendering.CircleRendererComponent;
import model.components.rendering.HexRendererComponent;
import model.components.rendering.RectangleRendererComponent;
import model.world.Hex;
import model.world.HexLayout;

import java.awt.*;

public class GameObjectFactory {

    private static GameObjectFactory instance = new GameObjectFactory();

    public static GameObjectFactory getInstance() {
        return instance;
    }

    private GameObjectFactory(){

    }

    public GameObject createWallTile(Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject wallTile = new GameObject(pos.X(), pos.Y());
        wallTile.addComponent(new HexRendererComponent(wallTile, painter, Color.green, hex, layout));
        wallTile.addComponent(new ColliderComponent(wallTile, physics, layout.getSize().X()));

        return wallTile;
    }

    public GameObject createPathTile(Hex hex, HexLayout layout, CanadaPainter painter){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject pathTile = new GameObject(pos.X(), pos.Y());
        pathTile.addComponent(new HexRendererComponent(pathTile, painter, Color.white, hex, layout));

        return pathTile;
    }

    public GameObject createPlayerObject(double posX, double posY, CanadaPainter painter, CanadaController controller, CanadaPhysics physics){

        GameObject playerTile = new GameObject(posX, posY);
        playerTile.addComponent(new CircleRendererComponent(playerTile, painter, Color.BLACK,7.5));

        PlayerInputComponent playerInputComponent = new PlayerInputComponent(playerTile, controller);
        playerTile.addComponent(playerInputComponent);
        playerTile.addComponent(new MovementComponent(playerTile, 1f, physics, playerInputComponent));
        playerTile.addComponent(new ColliderComponent(playerTile, physics, 7.5));

        return playerTile;
    }
}
