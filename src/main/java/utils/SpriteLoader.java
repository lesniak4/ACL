package utils;

import javax.imageio.ImageIO;
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
    private AnimatedSprite playerWalking;
    private AnimatedSprite playerIdle;

    private SpriteLoader(){

        try {
            pathSprite = ImageIO.read(getClass().getResource("/sprites/path.png"));
            wallSprite = ImageIO.read(getClass().getResource("/sprites/wall.png"));
            playerSprite = ImageIO.read(getClass().getResource("/sprites/player.png"));
            playerWalking = new AnimatedSprite(ImageIO.read(getClass().getResource("/sprites/spritesheet_player_walking.png")), 8, 12);
            playerIdle = new AnimatedSprite(ImageIO.read(getClass().getResource("/sprites/spritesheet_player_idle.png")), 8, 12);

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

    public AnimatedSprite getPlayerWalkingSprite() {
        return playerWalking;
    }

    public AnimatedSprite getPlayerIdleSprite() {
        return playerIdle;
    }
}
