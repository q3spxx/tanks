package com.q3spxx.Game;

import com.q3spxx.Constructor.Constructor;
import com.q3spxx.Graphics.TextureAtlas;
import com.q3spxx.IO.Input;
import com.q3spxx.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by q3spxx on 15.03.2017.
 */
public class Cursor extends Entity {

    private BufferedImage image;
    private boolean step = false;
    private final int SPEED_PER_SECOND = 5;
    private boolean moved = false;
    private boolean inChange = false;
    private long lastTime;
    private Constructor constructor;

    public Cursor (float x, float y, TextureAtlas atlas, Constructor constructor) {
        super(EntityType.Cursor, x, y);
        this.constructor = constructor;
        image = atlas.cut(0, 0, SPRITE_SIZE, SPRITE_SIZE);
        lastTime = Time.get();
    }
    public void update (Input input) throws IOException {

        long now = Time.get();
        if (
                !input.getKey(KeyEvent.VK_D) &&
                !input.getKey(KeyEvent.VK_S)
                ) {
            inChange = false;
        }
        if (
                !input.getKey(KeyEvent.VK_UP) &&
                !input.getKey(KeyEvent.VK_DOWN) &&
                !input.getKey(KeyEvent.VK_RIGHT) &&
                !input.getKey(KeyEvent.VK_LEFT)
                ) {
            step = false;
            lastTime = now;
            moved = false;
        }

        if (now - lastTime > Time.SECOND / SPEED_PER_SECOND) {
            step = false;
            lastTime = now;
        }

        if (step) {
            return;
        }


        if (input.getKey(KeyEvent.VK_S)) {
            Game.map.saveMap();
            step = true;
        }

        int newX = (int) x;
        int newY = (int) y;

        if (input.getKey(KeyEvent.VK_UP)) {
            newY -= SPRITE_SIZE * SPRITE_SCALE;
            step = true;
            moved = true;
        } else if (input.getKey(KeyEvent.VK_DOWN)) {
            newY += SPRITE_SIZE * SPRITE_SCALE;
            step = true;
            moved = true;
        } else if (input.getKey(KeyEvent.VK_RIGHT)) {
            newX += SPRITE_SIZE * SPRITE_SCALE;
            step = true;
            moved = true;
        } else if (input.getKey(KeyEvent.VK_LEFT)) {
            newX -= SPRITE_SIZE * SPRITE_SCALE;
            step = true;
            moved = true;
        }


        if (newX / (SPRITE_SIZE * SPRITE_SCALE) < 0) {
            x = 0;
        } else if (newX / (SPRITE_SIZE * SPRITE_SCALE) > 12) {
            x = 12 * SPRITE_SIZE * SPRITE_SCALE;
        } else {
            x = newX;
            if (moved) {
                constructor.moved = true;
            }
        }
        if (newY / (SPRITE_SIZE * SPRITE_SCALE) < 0) {
            y = 0;
        } else if (newY / (SPRITE_SIZE * SPRITE_SCALE) > 12) {
            y = 12 * SPRITE_SIZE * SPRITE_SCALE;
        } else {
            y = newY;
            if (moved) {
                constructor.moved = true;
            }
        }



        if (input.getKey(KeyEvent.VK_D)) {

            if (!inChange) {
                if (!constructor.moved) {
                    constructor.current++;
                }
                constructor.moved = false;

                if (constructor.current > constructor.count) {
                    constructor.current = 0;
                }
            }

            inChange = true;


            for (int i = 0; i < constructor.constructorTiles[constructor.current].location.length; i++) {
                for (int j = 0; j < constructor.constructorTiles[constructor.current].location[i].length; j++) {
                    int type = 0;
                    if (constructor.constructorTiles[constructor.current].location[i][j] == 1) {
                        type = constructor.constructorTiles[constructor.current].type;
                    }
                    Game.map.changeTypeCell(
                            (int) x / (SPRITE_SIZE * SPRITE_SCALE) * 2 + j,
                            (int) y / (SPRITE_SIZE * SPRITE_SCALE) * 2 + i,
                            type
                    );
                }
            }
        }
    }

    public void render (Graphics2D g) {
        g.drawImage(image, (int) x + Game.OFFSETX, (int) y + Game.OFFSETY, SPRITE_SIZE * SPRITE_SCALE, SPRITE_SIZE * SPRITE_SCALE, null);
    }
}
