package com.q3spxx.Interface;

import com.q3spxx.Game.Game;
import com.q3spxx.Graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by q3spxx on 29.03.2017.
 */
public class Interface {
    private int SCALE;
    private int SPRITE_SIZE;
    private int x;
    private int y;
    private BufferedImage background;
    public Interface (TextureAtlas atlas, int x, int y, int size, int scale) {
        this.x = x;
        this.y = y;
        this.SPRITE_SIZE = size;
        this.SCALE = scale;
        background = new BufferedImage(4 * size, 30 * size, BufferedImage.TYPE_INT_ARGB);
        BufferedImage temp = atlas.cut(46 * size, 0, 4 * size, 30 * size);
        Graphics bg = background.getGraphics();
        bg.setColor(Color.black);
        bg.fillRect(0, 0, 4 * size, 30 * size);
        bg.drawImage(temp, 0, 0,null);
    }
    public void render (Graphics2D g) {
        g.drawImage(background, x + Game.OFFSETX, y - Game.OFFSETY, SPRITE_SIZE * 4 * SCALE, SPRITE_SIZE * 30 * SCALE, null);
    }
}
