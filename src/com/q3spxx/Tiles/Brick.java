package com.q3spxx.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by q3spxx on 27.03.2017.
 */
public abstract class Brick {
    protected static BufferedImage createBrickTiles (BufferedImage tile, int spriteSize) {
        BufferedImage result = new BufferedImage(spriteSize * 15, spriteSize, BufferedImage.TYPE_INT_ARGB);
        Graphics resultGraphics = result.getGraphics();
        BufferedImage temp = new BufferedImage(spriteSize, spriteSize, BufferedImage.TYPE_INT_ARGB);
        Graphics tempGraphics = temp.getGraphics();
        tempGraphics.setColor(Color.black);
        //(0) default tile
        resultGraphics.drawImage(tile, 0, 0, null);
        //(1) 1 1
        //    0 0
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, spriteSize / 2, spriteSize, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize, 0, null);
        //(2) 0 0
        //    1 1
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, 0, spriteSize, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 2, 0, null);
        //(3) 1 0
        //    1 0
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(spriteSize / 2, 0, spriteSize / 2, spriteSize);
        resultGraphics.drawImage(temp, spriteSize * 3, 0, null);
        //(4) 0 1
        //    0 1
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, 0, spriteSize / 2, spriteSize);
        resultGraphics.drawImage(temp, spriteSize * 4, 0, null);
        //(5) 1 0
        //    0 1
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(spriteSize / 2, 0, spriteSize / 2, spriteSize / 2);
        tempGraphics.fillRect(0, spriteSize / 2, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 5, 0, null);
        //(6) 0 1
        //    1 0
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, 0, spriteSize / 2, spriteSize / 2);
        tempGraphics.fillRect(spriteSize / 2, spriteSize / 2, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 6, 0, null);
        //(7) 0 1
        //    1 1
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, 0, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 7, 0, null);
        //(8) 1 0
        //    1 1
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(spriteSize / 2, 0, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 8, 0, null);
        //(9) 1 1
        //    1 0
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(spriteSize / 2, spriteSize / 2, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 9, 0, null);
        //(10) 1 1
        //     0 1
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, spriteSize / 2, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 10, 0, null);
        //(11) 1 0
        //     0 0
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, spriteSize / 2, spriteSize, spriteSize / 2);
        tempGraphics.fillRect(spriteSize / 2, 0, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 11, 0, null);
        //(12) 0 1
        //     0 0
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, spriteSize / 2, spriteSize, spriteSize / 2);
        tempGraphics.fillRect(0, 0, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 12, 0, null);
        //(13) 0 0
        //     0 1
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, 0, spriteSize, spriteSize / 2);
        tempGraphics.fillRect(0, spriteSize / 2, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 13, 0, null);
        //(14) 0 0
        //     1 0
        tempGraphics.drawImage(tile, 0, 0, null);
        tempGraphics.fillRect(0, 0, spriteSize, spriteSize / 2);
        tempGraphics.fillRect(spriteSize / 2, spriteSize / 2, spriteSize / 2, spriteSize / 2);
        resultGraphics.drawImage(temp, spriteSize * 14, 0, null);


        return result;
    }
}
