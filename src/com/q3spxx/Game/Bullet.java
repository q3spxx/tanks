package com.q3spxx.Game;

import com.q3spxx.Effects.Bang;
import com.q3spxx.Effects.EffectType;
import com.q3spxx.Graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by q3spxx on 24.03.2017.
 */
public class Bullet extends Entity {

    public Tanks owner;
    public Vec vec;
    public boolean flying = false;
    private final int SPRITE_SIZE = 8;
    private final int TOTAL_SIZE = SPRITE_SIZE * SPRITE_SCALE;
    private BufferedImage image;
    private int speed = 0;
    private int direction = 0;

    protected Bang bang;

    public Bullet (Tanks owner, TextureAtlas atlas) {
        super(EntityType.Bullet, 0, 0);
        this.owner = owner;
        this.image = atlas.cut(40 * SPRITE_SIZE + 1, 12 * SPRITE_SIZE + 4, 32, 8);
        this.bang = new Bang(EffectType.Bang, false, atlas);
    }

    public void shot (float xPlayer, float yPlayer, Vec vector, int speedValue) {

        vec = vector;
        speed = speedValue;
        direction = vec.n;

        switch (direction) {
            case 0:
                x = xPlayer - (TOTAL_SIZE) / 2;
                y = yPlayer - (TOTAL_SIZE + SPRITE_SCALE);
                break;
            case 1:
                x = xPlayer - (TOTAL_SIZE);
                y = yPlayer - (TOTAL_SIZE) / 2;
                break;
            case 2:
                x = xPlayer - (TOTAL_SIZE) / 2;
                y = yPlayer + SPRITE_SCALE;
                break;
            case 3:
                x = xPlayer + (TOTAL_SIZE) / 2 - SPRITE_SCALE * 2;
                y = yPlayer - (TOTAL_SIZE) / 2;
                break;
        }

        flying = true;
    }

    public void update () {
        if (flying) {

            int speedValue = speed;
            boolean hit = false;
            int xTarget1 = 0;
            int yTarget1 = 0;
            int xTarget2 = 0;
            int yTarget2 = 0;
            float xf1 = 0;
            float yf1 = 0;
            float xf2 = 0;
            float yf2 = 0;
            switch (vec.n) {
                case 0:
                    xf1 = x;
                    yf1 = y;
                    xf2 = x + TOTAL_SIZE;
                    yf2 = y;
                    break;
                case 1:
                    xf1 = x;
                    yf1 = y + TOTAL_SIZE;
                    xf2 = x;
                    yf2 = y;
                    break;
                case 2:
                    xf1 = x;
                    yf1 = y + TOTAL_SIZE;
                    xf2 = x + TOTAL_SIZE;
                    yf2 = y + TOTAL_SIZE;
                    break;
                case 3:
                    xf1 = x + TOTAL_SIZE;
                    yf1 = y;
                    xf2 = x + TOTAL_SIZE;
                    yf2 = y + TOTAL_SIZE;
                    break;
            }
            if (Collision.checkFlag(xf1, yf1) && Game.flag.state == 0 || Collision.checkFlag(xf2, yf2) && Game.flag.state == 0) {
                speedValue = 0;
                Game.flag.kill();
                hit = true;
            }
            while (speedValue != 0) {
                float newX = x + vec.x * speedValue;
                float newY = y + vec.y * speedValue;

                if (Collision.map((newX + TOTAL_SIZE / 4), newY + (TOTAL_SIZE / 4), TOTAL_SIZE / 2)) {
                    speedValue--;
                    if (!hit) {
                        hit = true;
                        switch (vec.n) {
                            case 0:
                                xTarget1 = (int) newX / TOTAL_SIZE;
                                yTarget1 = (int) newY / TOTAL_SIZE;
                                xTarget2 = (int) (newX + TOTAL_SIZE) / TOTAL_SIZE;
                                yTarget2 = (int) newY / TOTAL_SIZE;
                                break;
                            case 1:
                                xTarget1 = (int) newX / TOTAL_SIZE;
                                yTarget1 = (int) (newY + TOTAL_SIZE) / TOTAL_SIZE;
                                xTarget2 = (int) newX / TOTAL_SIZE;
                                yTarget2 = (int) newY / TOTAL_SIZE;
                                break;
                            case 2:
                                xTarget1 = (int) (newX + TOTAL_SIZE) / TOTAL_SIZE;
                                yTarget1 = (int) (newY + TOTAL_SIZE) / TOTAL_SIZE;
                                xTarget2 = (int) newX / TOTAL_SIZE;
                                yTarget2 = (int) (newY + TOTAL_SIZE) / TOTAL_SIZE;
                                break;
                            case 3:
                                xTarget1 = (int) (newX + TOTAL_SIZE) / TOTAL_SIZE;
                                yTarget1 = (int) newY / TOTAL_SIZE;
                                xTarget2 = (int) (newX + TOTAL_SIZE) / TOTAL_SIZE;
                                yTarget2 = (int) (newY + TOTAL_SIZE) / TOTAL_SIZE;
                                break;
                        }
                    }
                } else {
                    x = newX;
                    y = newY;
                    return;
                }

            }

            if (hit) {
                flying = false;
                Game.map.changeTypeCell(xTarget1, yTarget1, xTarget2, yTarget2, vec, owner.upgrade);
                bang.show(x + (TOTAL_SIZE) / 2, y + (TOTAL_SIZE) / 2);
            }
        }
        bang.update();
    }

    public void render (Graphics2D g) {
        if (flying) {
            g.drawImage(
                    image,
                    (int) x + Game.OFFSETX,
                    (int) y + Game.OFFSETY,
                    (int) x + TOTAL_SIZE + Game.OFFSETX,
                    (int) y + TOTAL_SIZE + Game.OFFSETY,
                    direction * SPRITE_SIZE,
                    0,
                    direction * SPRITE_SIZE + SPRITE_SIZE,
                    SPRITE_SIZE,
                    null);
        }
        bang.render(g);
    }
}
