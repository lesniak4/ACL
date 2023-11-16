package model;

import engine.IGameController;
import model.components.ai.AIComponent;
import model.components.ai.PathNodeComponent;
import model.components.ai.PathfindingComponent;
import model.components.animation.CharacterAnimationComponent;
import model.components.physics.*;
import model.components.rendering.AnimatedSpriteRendererComponent;
import model.components.rendering.BitmaskedSpriteRendererComponent;
import model.components.rendering.CameraComponent;
import model.components.rendering.SpriteRendererComponent;
import model.components.world.CoinComponent;
import model.components.world.KeyComponent;
import model.components.world.WorldExitComponent;
import model.components.world.WorldSpawnComponent;
import model.world.Hex;
import model.world.HexLayout;
import model.world.WorldGraph;
import utils.GameConfig;
import utils.SpriteLoader;
import utils.Vector2;

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
        wallTile.addComponent(new BitmaskedSpriteRendererComponent(wallTile, painter, Color.WHITE, 1, true, SpriteLoader.getInstance().getWallSprite()));
        //wallTile.addComponent(new HexRendererComponent(wallTile, painter, Color.GREEN, hex, layout, true));
        wallTile.addComponent(new ColliderComponent(wallTile, physics, layout.getSize().X(), false));

        return wallTile;
    }

    public GameObject createBorderTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject wallTile = new GameObject(pos.X(), pos.Y(), game);
        wallTile.addComponent(new BitmaskedSpriteRendererComponent(wallTile, painter, Color.WHITE, 1, true, SpriteLoader.getInstance().getWallSprite()));
        //wallTile.addComponent(new HexRendererComponent(wallTile, painter, Color.GREEN, hex, layout, true));

        return wallTile;
    }

    public GameObject createPathTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject pathTile = new GameObject(pos.X(), pos.Y(), game);
        pathTile.addComponent(new BitmaskedSpriteRendererComponent(pathTile, painter, Color.WHITE, 0, false, SpriteLoader.getInstance().getPathSprite()));
        pathTile.addComponent(new PathNodeComponent(pathTile));
        //pathTile.addComponent(new HexRendererComponent(pathTile, painter, Color.WHITE, hex, layout, true));

        return pathTile;
    }

    public GameObject createCoinsObject(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject coins = new GameObject(pos.X(), pos.Y(), game);
        coins.addComponent(new SpriteRendererComponent(coins, painter, Color.ORANGE, 1, false, SpriteLoader.getInstance().getGoldCoinsSprite()));
        coins.addComponent(new ColliderComponent(coins,  physics, 10,true));
        coins.addComponent(new CoinComponent(coins, GameConfig.getInstance().getCoinValue()));

        return coins;
    }

    public GameObject createKeyObject(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject key = new GameObject(pos.X(), pos.Y(), game);
        key.addComponent(new SpriteRendererComponent(key, painter, Color.ORANGE, 1, false, SpriteLoader.getInstance().getAxeSprite()));
        key.addComponent(new ColliderComponent(key, physics,25, true));
        key.addComponent(new KeyComponent(key));


        return key;
    }

    public GameObject createPlayerObject(CanadaGame game, double posX, double posY, CanadaPainter painter, IGameController controller, CanadaPhysics physics){

        GameConfig gc = GameConfig.getInstance();

        GameObject player = new GameObject(posX, posY, game);
        player.addComponent(new CameraComponent(player));
        AnimatedSpriteRendererComponent renderer = new AnimatedSpriteRendererComponent(player, painter, Color.WHITE, 1, false, SpriteLoader.getInstance().getPlayerIdleSprite(), 0.6d);
        player.addComponent(renderer);
        //player.addComponent(new SpriteRendererComponent(player, painter, Color.WHITE, 1, SpriteLoader.getInstance().getPlayerSprite()));
        //player.addComponent(new CircleRendererComponent(player, painter, Color.RED,1,1, true));

        PlayerInputComponent playerInputComponent = new PlayerInputComponent(player, controller);
        player.addComponent(playerInputComponent);
        PlayerPauseComponent playerPauseComponent  = new PlayerPauseComponent(player, playerInputComponent);
        player.addComponent(playerPauseComponent);
        PlayerMovementComponent movement = new PlayerMovementComponent(player, gc.getPlayerBaseMS(), physics, playerInputComponent);
        player.addComponent(movement);
        player.addComponent(new CharacterAnimationComponent(player, movement, renderer, SpriteLoader.getInstance().getPlayerIdleSprite(), SpriteLoader.getInstance().getPlayerWalkingSprite()));
        player.addComponent(new ColliderComponent(player, physics, 12.45d, false));
        player.addComponent(new PlayerInteractionComponent(player));

        return player;
    }

    public GameObject createMonsterObject(CanadaGame game, double posX, double posY, CanadaPainter painter, WorldGraph worldGraph, CanadaPhysics physics, GameObject target, GameObject player){

        GameConfig gc = GameConfig.getInstance();

        GameObject monster = new GameObject(posX, posY, game);
        PathfindingComponent pathfindingComponent = new PathfindingComponent(monster, worldGraph);
        pathfindingComponent.setTarget(target.getPosition());

        //monster.addComponent(new CircleRendererComponent(monster, painter, Color.RED,1, 8, true));
        AnimatedSpriteRendererComponent renderer = new AnimatedSpriteRendererComponent(monster, painter, Color.WHITE, 1, false, SpriteLoader.getInstance().getMonsterIdleSprite(), 0.5d);
        monster.addComponent(renderer);

        monster.addComponent(new AIComponent(monster,pathfindingComponent, player));
        MonsterMovementComponent movement = new MonsterMovementComponent(monster, gc.getMonsterBaseMS(), physics, pathfindingComponent);
        monster.addComponent(movement);
        monster.addComponent(new CharacterAnimationComponent(monster, movement, renderer, SpriteLoader.getInstance().getMonsterIdleSprite(), SpriteLoader.getInstance().getMonsterWalkingSprite()));
        monster.addComponent(new ColliderComponent(monster, physics, 8, true));

        return monster;
    }

    public GameObject createWorldSpawnTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject exitTile = new GameObject(pos.X(), pos.Y(), game);
        exitTile.addComponent(new BitmaskedSpriteRendererComponent(exitTile, painter, Color.WHITE, 0, false, SpriteLoader.getInstance().getPathSprite()));
        exitTile.addComponent(new ColliderComponent(exitTile, physics, layout.getSize().X(), false));
        exitTile.addComponent(new WorldSpawnComponent(exitTile));

        return exitTile;
    }

    public GameObject createWorldExitTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject exitTile = new GameObject(pos.X(), pos.Y(), game);
        exitTile.addComponent(new BitmaskedSpriteRendererComponent(exitTile, painter, Color.WHITE, 0, false, SpriteLoader.getInstance().getPathSprite()));
        exitTile.addComponent(new SpriteRendererComponent(exitTile, painter, Color.ORANGE, 1, false, SpriteLoader.getInstance().getExitSprite()));
        exitTile.addComponent(new ColliderComponent(exitTile, physics, layout.getSize().X(), false));
        exitTile.addComponent(new WorldExitComponent(exitTile));

        return exitTile;
    }
}
