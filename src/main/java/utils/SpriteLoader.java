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
    private BufferedImage lakeSprite;
    private BufferedImage swimLessonSprite;
    private SpriteSheet playerWalking;
    private SpriteSheet playerIdle;
    private SpriteSheet playerFighting;
    private SpriteSheet playerSlingshot;
    private SpriteSheet playerLearningSwim;
    private SpriteSheet playerSwimming;

    private SpriteSheet monsterIdle;
    private SpriteSheet monsterWalking;
    private SpriteSheet monsterFighting;
    private SpriteSheet monsterSwimming;

    private BufferedImage stoneSprite;
    private BufferedImage exitSprite;
    private BufferedImage mineLeftSprite;
    private BufferedImage mineRightSprite;


    private SpriteLoader(){

        try {
            pathSprite = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/path.png")), 1, 16);
            wallSprite = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/wall.png")), 1, 16);
            lakeSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/lake.png"));
            swimLessonSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/swim_lesson.png"));

            playerWalking = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_walking.png")), 8, 16);
            playerIdle = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_idle.png")), 8, 1);
            playerFighting = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_fighting.png")), 8, 6);
            playerSlingshot = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_slingshot.png")), 8, 1);
            playerLearningSwim = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_learning_swim.png")), 8, 1);
            playerSwimming = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_player_swimming.png")), 8, 1);

            monsterIdle = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_monster_idle.png")), 8, 1);
            monsterWalking = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_monster_walking.png")), 8, 16);
            monsterFighting = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_monster_fighting.png")), 8, 6);
            monsterSwimming = new SpriteSheet(ImageIO.read(getClass().getResource("/sprites/gameobjects/spritesheet_monster_swimming.png")), 8, 1);

            stoneSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/stone.png"));
            exitSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/exit.png"));
            mineLeftSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/mine_left.png"));
            mineRightSprite = ImageIO.read(getClass().getResource("/sprites/gameobjects/mine_right.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage getSprite(String path){
        try {
            return ImageIO.read(SpriteLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SpriteSheet getPathSprite() {
        return pathSprite;
    }

    public SpriteSheet getWallSprite() {
        return wallSprite;
    }

    public BufferedImage getWaterSprite() {
        return lakeSprite;
    }

    public BufferedImage getSwimmingLessonSprite() {
        return swimLessonSprite;
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
    public SpriteSheet getPlayerSlingshotSprite() {
        return playerSlingshot;
    }
    public SpriteSheet getPlayerLearningSwimSprite() {
        return playerLearningSwim;
    }
    public SpriteSheet getPlayerSwimmingSprite() {
        return playerSwimming;
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
    public SpriteSheet getMonsterSwimmingSprite() {
        return monsterSwimming;
    }

    public BufferedImage getStoneSprite() {
        return stoneSprite;
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
}
