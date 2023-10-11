package model;

import model.components.WorldExitComponent;
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

    public GameObject createWallTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject wallTile = new GameObject(pos.X(), pos.Y(), game);
        wallTile.addComponent(new HexRendererComponent(wallTile, painter, Color.green, hex, layout));
        wallTile.addComponent(new ColliderComponent(wallTile, physics, layout.getSize().X(), false));

        return wallTile;
    }

    public GameObject createPathTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject pathTile = new GameObject(pos.X(), pos.Y(), game);
        pathTile.addComponent(new HexRendererComponent(pathTile, painter, Color.white, hex, layout));

        return pathTile;
    }

    public GameObject createPlayerObject(CanadaGame game, double posX, double posY, CanadaPainter painter, CanadaController controller, CanadaPhysics physics){

        GameObject player = new GameObject(posX, posY, game);
        player.addComponent(new CircleRendererComponent(player, painter, Color.BLACK,8));

        PlayerInputComponent playerInputComponent = new PlayerInputComponent(player, controller);
        player.addComponent(playerInputComponent);
        player.addComponent(new MovementComponent(player, 1f, physics, playerInputComponent));
        player.addComponent(new ColliderComponent(player, physics, 8, false));

        return player;
    }

    public GameObject createWorldExitTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject exitTile = new GameObject(pos.X(), pos.Y(), game);
        exitTile.addComponent(new HexRendererComponent(exitTile, painter, Color.blue, hex, layout));
        exitTile.addComponent(new ColliderComponent(exitTile, physics, layout.getSize().X(), true));
        exitTile.addComponent(new WorldExitComponent(exitTile));

        return exitTile;
    }
}
