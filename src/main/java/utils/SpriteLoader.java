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
    private SpriteSheet playerFighting;
    private SpriteSheet monsterIdle;
    private SpriteSheet monsterWalking;
    private SpriteSheet monsterFighting;
    private BufferedImage goldCoinsSprite;
    private BufferedImage axeSprite;
    private BufferedImage swordSprite;
    private BufferedImage exitSprite;
    private BufferedImage mineLeftSprite;
    private BufferedImage mineRightSprite;

    private BufferedImage mainMenuBackgroundUI;
    private BufferedImage goldCoinsUI;
    private BufferedImage goldCoinsSmallUI;
    private BufferedImage axeUI;
    private BufferedImage speedUI;
    private BufferedImage invisibilityUI;
    private BufferedImage damageUI;

    private SpriteLoader(){

        try {
            pathSprite = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/path.png")), 1, 16);
            wallSprite = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/wall.png")), 1, 16);
            playerWalking = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_walking.png")), 8, 16);
            playerIdle = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_idle.png")), 8, 1);
            playerFighting = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_fighting.png")), 8, 6);

            monsterIdle = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_monster_idle.png")), 8, 1);
            monsterWalking = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_monster_walking.png")), 8, 16);
            monsterFighting = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_monster_fighting.png")), 8, 6);

            goldCoinsSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/coins.png"));
            axeSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/axe.png"));
            swordSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/sword.png"));
            exitSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/exit.png"));
            mineLeftSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/mine_left.png"));
            mineRightSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/mine_right.png"));

            mainMenuBackgroundUI = ImageIO.read(getClass().getResource("/sprites/ui/mainmenu_background.png"));
            goldCoinsUI = ImageIO.read(getClass().getResource("/sprites/ui/coins_ui.png"));
            goldCoinsSmallUI = ImageIO.read(getClass().getResource("/sprites/ui/coins_small_ui.png"));
            axeUI = ImageIO.read(getClass().getResource("/sprites/ui/axe_ui.png"));
            speedUI = ImageIO.read(getClass().getResource("/sprites/ui/speed_ui.png"));
            invisibilityUI = ImageIO.read(getClass().getResource("/sprites/ui/invisible_ui.png"));
            damageUI = ImageIO.read(getClass().getResource("/sprites/ui/invisible_ui.png"));

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
    public SpriteSheet getPlayerFightingSprite() {
        return playerFighting;
    }

    public SpriteSheet getMonsterIdleSprite() {
        return monsterIdle;
    }

    public SpriteSheet getMonsterWalkingSprite() {
        return monsterWalking;
    }
    public SpriteSheet getMonsterFightingSprite() {
        return monsterFighting;
    }

    public BufferedImage getGoldCoinsSprite() {
        return goldCoinsSprite;
    }

    public BufferedImage getAxeSprite() {
        return axeSprite;
    }

    public BufferedImage getSwordSprite() {
        return swordSprite;
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

    public BufferedImage getMainMenuBackgroundUI() {
        return mainMenuBackgroundUI;
    }

    public BufferedImage getGoldCoinsUI() {
        return goldCoinsUI;
    }

    public BufferedImage getGoldCoinsSmallUI() {
        return goldCoinsSmallUI;
    }
    public BufferedImage getAxeUI() {
        return axeUI;
    }

    public BufferedImage getSpeedUI() {
        return speedUI;
    }

    public BufferedImage getInvisibilityUI() {
        return invisibilityUI;
    }

    public BufferedImage getDamageUI() {
        return damageUI;
    }
}
