package model;

import engine.IGameController;
import model.components.AIComponent;
import model.components.PathfindingComponent;
import model.components.WorldExitComponent;
import model.components.CoinComponent;
import model.components.physics.*;
import model.components.rendering.CircleRendererComponent;
import model.components.rendering.HexRendererComponent;
import model.world.Hex;
import model.world.HexLayout;
import model.world.World;
import model.world.WorldGraph;

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
        wallTile.addComponent(new HexRendererComponent(wallTile, painter, Color.GREEN, hex, layout, true));
        wallTile.addComponent(new ColliderComponent(wallTile, physics, layout.getSize().X(), false));

        return wallTile;
    }

    public GameObject createPathTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject pathTile = new GameObject(pos.X(), pos.Y(), game);
        pathTile.addComponent(new HexRendererComponent(pathTile, painter, Color.WHITE, hex, layout, true));

        return pathTile;
    }

    public GameObject createCoinTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject coinTile = new GameObject(pos.X(), pos.Y(), game);
        coinTile.addComponent(new CircleRendererComponent(coinTile, painter, Color.ORANGE, 5, true));
        coinTile.addComponent(new ColliderComponent(coinTile,  physics, 5,true));
        coinTile.addComponent(new CoinComponent(coinTile));

        return coinTile;
    }

    public GameObject createPlayerObject(CanadaGame game, double posX, double posY, CanadaPainter painter, IGameController controller, CanadaPhysics physics){

        GameObject player = new GameObject(posX, posY, game);
        player.addComponent(new CircleRendererComponent(player, painter, Color.BLACK,8, true));

        PlayerInputComponent playerInputComponent = new PlayerInputComponent(player, controller);
        player.addComponent(playerInputComponent);
        player.addComponent(new PlayerMovementComponent(player, 1f, physics, playerInputComponent));
        player.addComponent(new ColliderComponent(player, physics, 8, false));
        player.addComponent(new PlayerInteractionComponent(player));

        return player;
    }

    public GameObject createMonsterObject(CanadaGame game, double posX, double posY, CanadaPainter painter, World world, CanadaPhysics physics, GameObject target, GameObject player){

        GameObject monster = new GameObject(posX, posY, game);
        PathfindingComponent pathfindingComponent = new PathfindingComponent(monster, world);
        pathfindingComponent.setTarget(target.getPosition());

        monster.addComponent(new CircleRendererComponent(monster, painter, Color.RED,8, true));
        monster.addComponent(new AIComponent(monster,pathfindingComponent, player));
        monster.addComponent(new MonsterMovementComponent(monster, 0.65f, physics, pathfindingComponent));
        monster.addComponent(new ColliderComponent(monster, physics, 8, false));

        return monster;
    }

    public GameObject createWorldExitTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject exitTile = new GameObject(pos.X(), pos.Y(), game);
        exitTile.addComponent(new HexRendererComponent(exitTile, painter, Color.BLUE, hex, layout, true));
        exitTile.addComponent(new ColliderComponent(exitTile, physics, layout.getSize().X(), true));
        exitTile.addComponent(new WorldExitComponent(exitTile));

        return exitTile;
    }
}
