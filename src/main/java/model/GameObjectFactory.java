package model;

import model.components.physics.PlayerComponent;
import model.components.rendering.RectangleComponent;

import java.awt.*;

public class GameObjectFactory {

    private static GameObjectFactory instance = new GameObjectFactory();

    public static GameObjectFactory getInstance() {
        return instance;
    }

    private GameObjectFactory(){

    }

    public GameObject createWallTile(float posX, float posY, float width, float height, Color color, CanadaPainter painter){

        GameObject wallTile = new GameObject(posX, posY);
        wallTile.addComponent(new RectangleComponent(wallTile, painter, color, width, height));

        return wallTile;
    }

    public GameObject createPlayerTile(float posX, float posY, float width, float height, Color color, CanadaPainter painter){

        GameObject playerTile = new GameObject(posX, posY);
        playerTile.addComponent(new RectangleComponent(playerTile, painter, color, width, height));

        return playerTile;
    }
}
