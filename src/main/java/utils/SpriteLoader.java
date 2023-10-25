package utils;

import model.GameObjectFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {

    private static SpriteLoader instance = new SpriteLoader();

    public static SpriteLoader getInstance() {
        return instance;
    }

    private BufferedImage pathSprite;
    private BufferedImage wallSprite;
    private BufferedImage playerSprite;
    private BufferedImage testAnim;

    private SpriteLoader(){

        try {
            pathSprite = ImageIO.read(getClass().getResource("/sprites/path.png"));
            wallSprite = ImageIO.read(getClass().getResource("/sprites/wall.png"));
            playerSprite = ImageIO.read(getClass().getResource("/sprites/player.png"));
            testAnim = ImageIO.read(getClass().getResource("/sprites/spritesheet_player.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getPathSprite() {
        return pathSprite;
    }

    public BufferedImage getWallSprite() {
        return wallSprite;
    }

    public BufferedImage getPlayerSprite() {
        return playerSprite;
    }

    public BufferedImage getTestAnimSprite() {
        return testAnim;
    }
}
