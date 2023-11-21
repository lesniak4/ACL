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
    private BufferedImage mineLeftSprite;
    private BufferedImage mineRightSprite;

    private BufferedImage goldCoinsUI;
    private BufferedImage axeUI;

    private SpriteLoader(){

        try {
            pathSprite = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/path.png")), 1, 16);
            wallSprite = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/wall.png")), 1, 16);
            playerWalking = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_walking.png")), 8, 16);
            playerIdle = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_idle.png")), 8, 1);
            monsterIdle = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_monster_idle.png")), 8, 1);
            monsterWalking = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_monster_walking.png")), 8, 16);
            goldCoinsSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/coins.png"));
            axeSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/axe.png"));
            exitSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/exit.png"));
            mineLeftSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/mine_left.png"));
            mineRightSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/mine_right.png"));

            goldCoinsUI = ImageIO.read(getClass().getResource("/sprites/ui/coins_ui.png"));
            axeUI = ImageIO.read(getClass().getResource("/sprites/ui/axe_ui.png"));

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

    public BufferedImage getMineLeftSprite() {
        return mineLeftSprite;
    }

    public BufferedImage getMineRightSprite() {
        return mineRightSprite;
    }

    public BufferedImage getGoldCoinsUI() {
        return goldCoinsUI;
    }
    public BufferedImage getAxeUI() {
        return axeUI;
    }
}
