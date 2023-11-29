package model;

import engine.IGameController;
import model.components.ai.AIComponent;
import model.components.ai.PathNodeComponent;
import model.components.ai.PathfindingComponent;
import model.components.animation.CharacterAnimationComponent;
import model.components.attacks.*;
import model.components.physics.*;
import model.components.characters.player.PlayerInputComponent;
import model.components.characters.player.PlayerInteractionComponent;
import model.components.characters.player.PlayerPauseComponent;
import model.components.characters.StatsComponent;
import model.components.characters.player.skills.PlayerSkillsShopComponent;
import model.components.rendering.*;
import model.components.world.*;
import model.fsm.states.game.PlayingState;
import model.world.Hex;
import model.world.HexLayout;
import model.world.WorldGraph;
import utils.GameConfig;
import utils.SpriteLoader;
import utils.Vector2;
import views.HealthBarView;

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
        GameObject wallTile = new GameObject(pos.X(), pos.Y(), "Wall_"+hex.getQ()+"_"+hex.getR(), game);
        wallTile.addComponent(new BitmaskedSpriteRendererComponent(wallTile, painter, Color.WHITE, 1, true, SpriteLoader.getInstance().getWallSprite()));
        //wallTile.addComponent(new HexRendererComponent(wallTile, painter, Color.GREEN, hex, layout, true));
        wallTile.addComponent(new ColliderComponent(wallTile, physics, layout.getSize().X(), false));

        return wallTile;
    }

    public GameObject createBorderTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject wallTile = new GameObject(pos.X(), pos.Y(), "Border_"+hex.getQ()+"_"+hex.getR(), game);
        wallTile.addComponent(new BitmaskedSpriteRendererComponent(wallTile, painter, Color.WHITE, 1, true, SpriteLoader.getInstance().getWallSprite()));
        //wallTile.addComponent(new HexRendererComponent(wallTile, painter, Color.GREEN, hex, layout, true));

        return wallTile;
    }

    public GameObject createPathTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject pathTile = new GameObject(pos.X(), pos.Y(), "Path_"+hex.getQ()+"_"+hex.getR(), game);
        pathTile.addComponent(new BitmaskedSpriteRendererComponent(pathTile, painter, Color.WHITE, 0, false, SpriteLoader.getInstance().getPathSprite()));
        pathTile.addComponent(new PathNodeComponent(pathTile));
        //pathTile.addComponent(new HexRendererComponent(pathTile, painter, Color.WHITE, hex, layout, true));

        return pathTile;
    }

    public GameObject createCoinsObject(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject coins = new GameObject(pos.X(), pos.Y(), "Coins_"+hex.getQ()+"_"+hex.getR(), game);
        coins.addComponent(new SpriteRendererComponent(coins, painter, Color.ORANGE, 1, false, SpriteLoader.getInstance().getGoldCoinsSprite()));
        coins.addComponent(new ColliderComponent(coins,  physics, 10,true));
        coins.addComponent(new CoinComponent(coins, GameConfig.getInstance().getCoinValue()));

        return coins;
    }

    public GameObject createKeyObject(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject key = new GameObject(pos.X(), pos.Y(), "Key_"+hex.getQ()+"_"+hex.getR(), game);
        key.addComponent(new SpriteRendererComponent(key, painter, Color.ORANGE, 1, false, SpriteLoader.getInstance().getAxeSprite()));
        key.addComponent(new ColliderComponent(key, physics,25, true));
        key.addComponent(new KeyComponent(key));


        return key;
    }

    public GameObject createPlayerObject(CanadaGame game, double posX, double posY, CanadaPainter painter, IGameController controller, CanadaPhysics physics, PlayingState playingState){

        GameConfig gc = GameConfig.getInstance();

        GameObject player = new GameObject(posX, posY, "Player", game);
        player.addComponent(new CameraComponent(player));
        AnimatedSpriteRendererComponent renderer = new AnimatedSpriteRendererComponent(player, painter, Color.WHITE, 1, false, SpriteLoader.getInstance().getPlayerIdleSprite(), 0.6d);
        player.addComponent(renderer);
        //player.addComponent(new SpriteRendererComponent(player, painter, Color.WHITE, 1, SpriteLoader.getInstance().getPlayerSprite()));
        //player.addComponent(new CircleRendererComponent(player, painter, Color.RED,1,1, true));

        PlayerInputComponent playerInputComponent = new PlayerInputComponent(player, controller);
        StatsComponent stats = new StatsComponent(player, gc.getPlayerBaseMS(), gc.getPlayerBaseMeleeDMG(), gc.getPlayerBaseRangedDMG(), gc.getPlayerMeleeAttackDistance(), gc.getPlayerRangedAttackSpeed());
        PlayerMovementComponent movement = new PlayerMovementComponent(player, gc.getPlayerBaseMS(), physics, playerInputComponent, stats);
        MeleeAttackComponent meleeAttackComponent = new MeleeAttackComponent(player, stats, movement, physics, 15d, 60,10);
        RangedAttackComponent rangedAttackComponent = new RangedAttackComponent(player, stats, movement, physics, 10d, 180,90);
        playerInputComponent.setMeleeAttackComponent(meleeAttackComponent);
        playerInputComponent.setRangedAttackComponent(rangedAttackComponent);

        StunComponent stun = new StunComponent(player);
        playerInputComponent.setStunComponent(stun);

        player.addComponent(playerInputComponent);
        PlayerPauseComponent playerPauseComponent  = new PlayerPauseComponent(player, playerInputComponent);
        player.addComponent(playerPauseComponent);

        player.addComponent(stats);
        //player.addComponent(new PlayerSpeedModifierComponent(player, stats, 10000, 2d));
        //player.addComponent(new PlayerInvisibleModifierComponent(player, stats, 10000));

        HealthComponent health = new HealthComponent(player, gc.getPlayerBaseHealth());
        player.addComponent(health);
        HealthBarView healthBar = new HealthBarView(game, player, health);
        playingState.addView(healthBar);

        player.addComponent(movement);
        player.addComponent(new CharacterAnimationComponent(player, movement, meleeAttackComponent, renderer, SpriteLoader.getInstance().getPlayerIdleSprite(), SpriteLoader.getInstance().getPlayerWalkingSprite(), SpriteLoader.getInstance().getPlayerFightingSprite()));
        player.addComponent(new ColliderComponent(player, physics, 12.45d, false));
        player.addComponent(new PlayerInteractionComponent(player, stats));

        player.addComponent(new PlayerSkillsShopComponent(player, playerInputComponent, stats));
        player.addComponent(meleeAttackComponent);
        player.addComponent(rangedAttackComponent);
        player.addComponent(stun);

        return player;
    }

    public GameObject createMonsterObject(CanadaGame game, double posX, double posY, CanadaPainter painter, WorldGraph worldGraph, CanadaPhysics physics, GameObject target, GameObject player, PlayingState playingState){

        GameConfig gc = GameConfig.getInstance();

        GameObject monster = new GameObject(posX, posY, "Monster", game);
        PathfindingComponent pathfindingComponent = new PathfindingComponent(monster, worldGraph);
        pathfindingComponent.setTarget(target.getPosition());

        //monster.addComponent(new CircleRendererComponent(monster, painter, Color.RED,1, 8, true));
        AnimatedSpriteRendererComponent renderer = new AnimatedSpriteRendererComponent(monster, painter, Color.WHITE, 1, false, SpriteLoader.getInstance().getMonsterIdleSprite(), 0.6d);
        monster.addComponent(renderer);

        StatsComponent stats = new StatsComponent(monster, gc.getMonsterBaseMS(), gc.getMonsterBaseMeleeDMG(), gc.getMonsterBaseRangedDMG(), gc.getMonsterMeleeAttackDistance(), gc.getMonsterRangedAttackSpeed());


        HealthComponent health = new HealthComponent(monster, gc.getMonsterBaseHealth());
        monster.addComponent(health);
        HealthBarView healthBar = new HealthBarView(game, monster, health);
        playingState.addView(healthBar);

        MonsterMovementComponent movement = new MonsterMovementComponent(monster, gc.getMonsterBaseMS(), physics, pathfindingComponent);
        MeleeAttackComponent meleeAttack = new MeleeAttackComponent(monster, stats, movement, physics, 15d, 30,10);
        StunComponent stun = new StunComponent(monster);

        monster.addComponent(new AIComponent(monster,pathfindingComponent, player, stun, meleeAttack));

        monster.addComponent(movement);
        monster.addComponent(meleeAttack);
        monster.addComponent(stun);

        monster.addComponent(new CharacterAnimationComponent(monster, movement, meleeAttack, renderer, SpriteLoader.getInstance().getMonsterIdleSprite(), SpriteLoader.getInstance().getMonsterWalkingSprite(), SpriteLoader.getInstance().getMonsterFightingSprite()));
        monster.addComponent(new ColliderComponent(monster, physics, 8d, true));



        return monster;
    }

    public GameObject createWorldSpawnTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject exitTile = new GameObject(pos.X(), pos.Y(), "WorldSpawn", game);
        exitTile.addComponent(new BitmaskedSpriteRendererComponent(exitTile, painter, Color.WHITE, 0, false, SpriteLoader.getInstance().getPathSprite()));
        exitTile.addComponent(new ColliderComponent(exitTile, physics, layout.getSize().X(), false));
        exitTile.addComponent(new WorldSpawnComponent(exitTile));

        return exitTile;
    }

    public GameObject createWorldExitTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject exitTile = new GameObject(pos.X(), pos.Y(), "WorldExit", game);
        exitTile.addComponent(new BitmaskedSpriteRendererComponent(exitTile, painter, Color.WHITE, 0, false, SpriteLoader.getInstance().getPathSprite()));
        exitTile.addComponent(new SpriteRendererComponent(exitTile, painter, Color.ORANGE, 1, false, SpriteLoader.getInstance().getExitSprite()));
        exitTile.addComponent(new ColliderComponent(exitTile, physics, layout.getSize().X(), false));
        exitTile.addComponent(new WorldExitComponent(exitTile));

        return exitTile;
    }

    public GameObject createTeleportationTile(CanadaGame game, Hex hex, HexLayout layout, CanadaPainter painter, CanadaPhysics physics, TeleportationTileOrientation orientation){

        Vector2 pos = layout.hexToWorldPos(hex);
        GameObject tpTile = new GameObject(pos.X(), pos.Y(), "Mine_"+hex.getQ()+"_"+hex.getR(), game);
        tpTile.addComponent(new SpriteRendererComponent(tpTile, painter, Color.WHITE, 1, false, orientation == TeleportationTileOrientation.LEFT ? SpriteLoader.getInstance().getMineLeftSprite() : SpriteLoader.getInstance().getMineRightSprite()));

        tpTile.addComponent(new ColliderComponent(tpTile, physics, layout.getSize().X(), true));
        tpTile.addComponent(new TeleportationTileComponent(tpTile, orientation));

        return tpTile;
    }

    public GameObject createDamageArea(CanadaGame game, Vector2 position, AttackComponent owner, CanadaPhysics physics, double radius, int damage, int stunDuration, int lifetime, boolean destroyOnHit){

        GameObject damageArea = new GameObject(position.X(), position.Y(), "DamageArea_"+owner.getGameObject().toString(), game);
        damageArea.addComponent(new DamageAreaMovementComponent(damageArea, 0, new Vector2(0,0), physics));
        damageArea.addComponent(new ColliderComponent(damageArea, physics, radius, true));
        damageArea.addComponent(new DamageAreaComponent(damageArea, damage, stunDuration, lifetime, owner, destroyOnHit));

        return damageArea;
    }

    public GameObject createDamageArea(CanadaGame game, Vector2 position, AttackComponent owner, CanadaPhysics physics, double radius, int damage, int stunDuration, int lifetime, double movementSpeed, Vector2 direction, boolean destroyOnHit){

        GameObject damageArea = new GameObject(position.X(), position.Y(), "DamageArea_"+owner.getGameObject().toString(), game);
        damageArea.addComponent(new SpriteRendererComponent(damageArea, game.getPainter(), Color.ORANGE, 1, false, SpriteLoader.getInstance().getStoneSprite()));
        damageArea.addComponent(new DamageAreaMovementComponent(damageArea, movementSpeed, direction, physics));
        damageArea.addComponent(new ColliderComponent(damageArea, physics, radius, true));
        damageArea.addComponent(new DamageAreaComponent(damageArea, damage, stunDuration, lifetime, owner, destroyOnHit));

        return damageArea;
    }
}
