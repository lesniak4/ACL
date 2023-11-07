package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {

    private static SpriteLoader instance = new SpriteLoader();

    public static SpriteLoader getInstance() {
        return instance;
    }

    private SpriteSheet pathSprite;
    private SpriteSheet wallSprite;
    private SpriteSheet playerWalking;
    private SpriteSheet playerIdle;
    private SpriteSheet monsterIdle;
    private SpriteSheet monsterWalking;
    private BufferedImage goldCoinsSprite;
    private BufferedImage axeSprite;
    private BufferedImage exitSprite;

    private SpriteLoader(){

        try {
            pathSprite = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/path.png")), 1, 16);
            wallSprite = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/wall.png")), 1, 16);
            playerWalking = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/spritesheet_player_walking.png")), 8, 16);
            playerIdle = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/spritesheet_player_idle.png")), 8, 1);
            monsterIdle = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/spritesheet_monster_idle.png")), 8, 1);
            monsterWalking = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/spritesheet_monster_walking.png")), 8, 16);
            goldCoinsSprite = ImageIO.read(getClass().getResource("/sprites/coins.png"));
            axeSprite = ImageIO.read(getClass().getResource("/sprites/axe.png"));
            exitSprite = ImageIO.read(getClass().getResource("/sprites/exit.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SpriteSheet getPathSprite() {
        return pathSprite;
    }

    public SpriteSheet getWallSprite() {
        return wallSprite;
    }

    public SpriteSheet getPlayerWalkingSprite() {
        return playerWalking;
    }

    public SpriteSheet getPlayerIdleSprite() {
        return playerIdle;
    }

    public SpriteSheet getMonsterIdleSprite() {
        return monsterIdle;
    }

    public SpriteSheet getMonsterWalkingSprite() {
        return monsterWalking;
    }

    public BufferedImage getGoldCoinsSprite() {
        return goldCoinsSprite;
    }

    public BufferedImage getAxeSprite() {
        return axeSprite;
    }

    public BufferedImage getExitSprite() {
        return exitSprite;
    }
}
