package utils;

import model.GameObjectFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SpriteLoader {

    private static SpriteLoader instance = new SpriteLoader();

    public static SpriteLoader getInstance() {
        return instance;
    }

    private Image pathSprite;
    private Image wallSprite;

    private SpriteLoader(){

        try {
            pathSprite = ImageIO.read(getClass().getResource("/sprites/path.png"));
            wallSprite = ImageIO.read(getClass().getResource("/sprites/wall.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Image getPathSprite() {
        return pathSprite;
    }

    public Image getWallSprite() {
        return wallSprite;
    }
}
