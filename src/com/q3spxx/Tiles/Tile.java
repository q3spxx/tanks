package com.q3spxx.Tiles;

import com.q3spxx.Game.Animation;
import com.q3spxx.Graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by q3spxx on 13.03.2017.
 */
public class Tile {
    private BufferedImage image;
    private TileType type;
    private int size;

    private Animation animation;

    protected Tile (TextureAtlas atlas, int size, TileType type, int totalFrame) {
        this.image = atlas.cut(
                type.getX() * size,
                type.getY() * size,
                type.getWidth() * size,
                size);
        if (type == TileType.BRICK) {
            this.image = Brick.createBrickTiles(this.image, size);
        }
        this.size = size;
        this.type = type;
        this.animation = new Animation(2.0f, totalFrame, true, false, 0);
    }
    public void runAnimation () {
        animation.run();
    }

    protected void update () {
        animation.update();
    }

    protected void render (Graphics2D g, int x, int y, int totalSize, int state) {
        g.drawImage(
                image,
                x,
                y,
                totalSize + x,
                totalSize + y,
                animation.getFrame() * size + state * size,
                0,
                animation.getFrame() * size + state * size + size,
                size,
                null);
    }
}
