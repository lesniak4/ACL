package model;

import engine.IGameController;
import model.components.AIComponent;
import model.components.PathfindingComponent;
import model.components.WorldExitComponent;
import model.components.CoinComponent;
import model.components.physics.ColliderComponent;
import model.components.physics.MovementComponent;
import model.components.physics.PlayerInputComponent;
import model.components.rendering.*;
import model.components.physics.*;
import model.components.rendering.CircleRendererComponent;
import model.components.rendering.HexRendererComponent;
import model.world.Hex;
import model.world.HexLayout;
import utils.AnimatedSprite;
import utils.SpriteLoader;
import utils.Vector2;
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
        wallTile.addComponent(new SpriteRendererComponent(wallTile, painter, Color.WHITE, 1, SpriteLoader.getInstance().getWallSprite()));
        //wallTile.addComponent(new HexRendererComponent(wallTile, painter, Color.GREEN, hex, layout, true));
        wallTile.addComponent(new ColliderComponent(wallTile, physics, layout.getSize().X(), false));

        return wallTile;
    }

    public GameObject createPathTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject pathTile = new GameObject(pos.X(), pos.Y(), game);
        pathTile.addComponent(new SpriteRendererComponent(pathTile, painter, Color.WHITE, 0, SpriteLoader.getInstance().getPathSprite()));
        //pathTile.addComponent(new HexRendererComponent(pathTile, painter, Color.WHITE, hex, layout, true));

        return pathTile;
    }

    public GameObject createCoinTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject coinTile = new GameObject(pos.X(), pos.Y(), game);
        coinTile.addComponent(new CircleRendererComponent(coinTile, painter, Color.ORANGE, 1, 5, true));
        coinTile.addComponent(new ColliderComponent(coinTile,  physics, 5,true));
        coinTile.addComponent(new CoinComponent(coinTile));

        return coinTile;
    }

    public GameObject createPlayerObject(CanadaGame game, double posX, double posY, CanadaPainter painter, IGameController controller, CanadaPhysics physics){

        GameObject player = new GameObject(posX, posY, game);
        player.addComponent(new CameraComponent(player));
        player.addComponent(new AnimatedSpriteRendererComponent(player, painter, Color.WHITE, 1, new AnimatedSprite(SpriteLoader.getInstance().getTestAnimSprite(), 2, 6)));
        //player.addComponent(new SpriteRendererComponent(player, painter, Color.WHITE, 1, SpriteLoader.getInstance().getPlayerSprite()));
        //player.addComponent(new CircleRendererComponent(player, painter, Color.RED,1,1, true));

        PlayerInputComponent playerInputComponent = new PlayerInputComponent(player, controller);
        player.addComponent(playerInputComponent);
        player.addComponent(new PlayerMovementComponent(player, 1d, physics, playerInputComponent));
        player.addComponent(new ColliderComponent(player, physics, 12.45d, false));
        player.addComponent(new PlayerInteractionComponent(player));

        return player;
    }

    public GameObject createMonsterObject(CanadaGame game, double posX, double posY, CanadaPainter painter, World world, CanadaPhysics physics, GameObject target, GameObject player){

        GameObject monster = new GameObject(posX, posY, game);
        PathfindingComponent pathfindingComponent = new PathfindingComponent(monster, world);
        pathfindingComponent.setTarget(target.getPosition());

        monster.addComponent(new CircleRendererComponent(monster, painter, Color.RED,1, 8, true));
        monster.addComponent(new AIComponent(monster,pathfindingComponent, player));
        monster.addComponent(new MonsterMovementComponent(monster, 0.65f, physics, pathfindingComponent));
        monster.addComponent(new ColliderComponent(monster, physics, 8, false));

        return monster;
    }

    public GameObject createWorldExitTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject exitTile = new GameObject(pos.X(), pos.Y(), game);
        exitTile.addComponent(new HexRendererComponent(exitTile, painter, Color.BLUE, 0, hex, layout, true));
        exitTile.addComponent(new ColliderComponent(exitTile, physics, layout.getSize().X(), true));
        exitTile.addComponent(new WorldExitComponent(exitTile));

        return exitTile;
    }
}
