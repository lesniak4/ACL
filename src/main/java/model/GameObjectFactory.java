package model;

import model.components.physics.ColliderComponent;
import model.components.physics.MovementComponent;
import model.components.physics.PlayerInputComponent;
import model.components.rendering.RectangleRendererComponent;

import java.awt.*;

public class GameObjectFactory {

    private static GameObjectFactory instance = new GameObjectFactory();

    public static GameObjectFactory getInstance() {
        return instance;
    }

    private GameObjectFactory(){

    }

    public GameObject createWallTile(float posX, float posY, float width, float height, CanadaPainter painter, CanadaPhysics physics){

        GameObject wallTile = new GameObject(posX, posY);
        wallTile.addComponent(new RectangleRendererComponent(wallTile, painter, Color.green, width, height));
        wallTile.addComponent(new ColliderComponent(wallTile, physics, width/2));

        return wallTile;
    }

    public GameObject createPathTile(float posX, float posY, float width, float height, CanadaPainter painter){

        GameObject pathTile = new GameObject(posX, posY);
        pathTile.addComponent(new RectangleRendererComponent(pathTile, painter, Color.white, width, height));

        return pathTile;
    }

    public GameObject createPlayerObject(float posX, float posY, CanadaPainter painter, CanadaController controller, CanadaPhysics physics){

        GameObject playerTile = new GameObject(posX, posY);
        playerTile.addComponent(new RectangleRendererComponent(playerTile, painter, Color.BLACK,20,20));

        PlayerInputComponent playerInputComponent = new PlayerInputComponent(playerTile, controller);
        playerTile.addComponent(playerInputComponent);
        playerTile.addComponent(new MovementComponent(playerTile, 1f, physics, playerInputComponent));
        playerTile.addComponent(new ColliderComponent(playerTile, physics, 10));

        return playerTile;
    }
}
