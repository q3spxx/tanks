package com.q3spxx.Game;

import com.q3spxx.Graphics.TextureAtlas;
import com.q3spxx.IO.Input;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by q3spxx on 16.03.2017.
 */
public class Flag extends Entity {

    public int state = 0;
    private BufferedImage image;

    private enum State {
        ALIVE(0 * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE),
        DEAD(1 * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE);

        private int x, y, w, h;

        State (int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        protected BufferedImage texture (BufferedImage image) {
            return image.getSubimage(x, y, w, h);
        }

    }
    public Flag (TextureAtlas atlas) {
        super(EntityType.Flag, 6 * SPRITE_SIZE * SPRITE_SCALE, 12 * SPRITE_SIZE * SPRITE_SCALE);
        this.image = atlas.cut(19 * SPRITE_SIZE, 2 * SPRITE_SIZE, 2 * SPRITE_SIZE, SPRITE_SIZE);
    }

    public float getX () {
        return x;
    }
    public float getY () {
        return y;
    }
    public float getSize () {
        return SPRITE_SIZE * SPRITE_SCALE;
    }

    public void kill () {
        state = 1;
    }



    protected State getStateTexture (int index) {
        if (index == 0) {
            return State.ALIVE;
        } else {
            return State.DEAD;
        }
    }

    public void render(Graphics2D g) {
        g.drawImage(getStateTexture(state).texture(image), (int) x + Game.OFFSETX, (int) y + Game.OFFSETY, SPRITE_SIZE * SPRITE_SCALE, SPRITE_SIZE * SPRITE_SCALE, null);
    }
}
