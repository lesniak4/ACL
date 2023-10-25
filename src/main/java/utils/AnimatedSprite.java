package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedSprite {

    private BufferedImage[] sprites;
    private int frameCount;

    public AnimatedSprite(BufferedImage spriteSheet, int rows, int cols){

        this.frameCount = rows * cols;
        this.sprites = new BufferedImage[frameCount];

        int spriteWidth = spriteSheet.getWidth() / cols;
        int spriteHeight = spriteSheet.getHeight() / rows;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++) {
                int index = j + i * cols;
                sprites[index] = spriteSheet.getSubimage(spriteWidth * j, spriteHeight * i, spriteWidth, spriteHeight);
            }
        }
    }

    public BufferedImage getSprite(int frame){
        return this.sprites[frame % frameCount];
    }

    public int getFrameCount(){
        return this.frameCount;
    }
}
