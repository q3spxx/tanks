package com.q3spxx.Game;

import com.q3spxx.Graphics.Sprite;
import com.q3spxx.Graphics.SpriteSheet;
import com.q3spxx.Graphics.TextureAtlas;
import com.q3spxx.IO.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Tanks{

    private Bullet bullet;
    private Vec vec = Vec.UP;
    private boolean changeDirection = false;
    private boolean keyUp = true;
    private int iceTimer = 16;
    private static int SPRITEX = 0, SPRITEY = 0;

    private boolean shotControl = false;

    private Heading heading;
    private static Map<Heading, Sprite> spriteMap;

    private enum Heading  {
        UP(SPRITEX * SPRITE_SIZE + 0 * SPRITE_SIZE, SPRITEY * SPRITE_SIZE + upgrade * SPRITE_SIZE, 2 * SPRITE_SIZE, 1 * SPRITE_SIZE),
        LEFT(SPRITEX * SPRITE_SIZE + 2 * SPRITE_SIZE, SPRITEY * SPRITE_SIZE + upgrade * SPRITE_SIZE, 2 * SPRITE_SIZE, 1 * SPRITE_SIZE),
        DOWN(SPRITEX * SPRITE_SIZE + 4 * SPRITE_SIZE, SPRITEY * SPRITE_SIZE + upgrade * SPRITE_SIZE, 2 * SPRITE_SIZE, 1 * SPRITE_SIZE),
        RIGHT(SPRITEX * SPRITE_SIZE + 6 * SPRITE_SIZE, SPRITEY * SPRITE_SIZE + upgrade * SPRITE_SIZE, 2 * SPRITE_SIZE, 1 * SPRITE_SIZE);
        private int x, y, w, h;
        Heading (int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
        protected BufferedImage texture (TextureAtlas atlas) {
            return atlas.cut(x, y, w, h);
        }
    }
    public Player (float x, float y, float speed, TextureAtlas atlas, int upgrade) {
        super(EntityType.Player, x, y, speed);
        this.upgrade = upgrade;
        heading = Heading.UP;
        spriteMap = new HashMap<Heading, Sprite>();

        redefineSprites(atlas);
        this.bullet = new Bullet(this, atlas);

    }

    protected static void redefineSprites (TextureAtlas atlas) {
        for (Heading h : Heading.values()) {
            SpriteSheet sheet = new SpriteSheet(h.texture(atlas), SPRITE_FOR_HEADING, SPRITE_SIZE);
            Sprite sprite = new Sprite(sheet, SPRITE_SCALE);
            spriteMap.put(h, sprite);
        }
    }

    public void update(Input input) {
        float newX = x;
        float newY = y;
        float speedValue = speed;
        boolean move = false;

        if (input.getKey(KeyEvent.VK_D)) {

            if (!bullet.flying && !shotControl && !bullet.bang.animation.active) {
                bullet.shot(x + (SPRITE_SIZE * SPRITE_SCALE) / 2, y + (SPRITE_SIZE * SPRITE_SCALE) / 2, vec, 8);
                shotControl = true;
            }

        }

        if (!input.getKey(KeyEvent.VK_D)) {
            shotControl = false;
        }

        bullet.update();

        if (
                !input.getKey(KeyEvent.VK_UP) &&
                !input.getKey(KeyEvent.VK_LEFT) &&
                !input.getKey(KeyEvent.VK_DOWN) &&
                !input.getKey(KeyEvent.VK_RIGHT)
                ) {
            keyUp = true;
            animation.stop();
        }

        while (speedValue != 0) {

            if (input.getKey(KeyEvent.VK_UP)) {
                newY -= speedValue;
                if (vec != Vec.UP) {
                    changeDirection = true;
                }
                vec = Vec.UP;
                heading = Heading.UP;
                keyUp = false;
                iceTimer = 16;
                animation.run();
            } else if (input.getKey(KeyEvent.VK_RIGHT)) {
                newX += speedValue;
                if (vec != Vec.RIGHT) {
                    changeDirection = true;
                }
                vec = Vec.RIGHT;
                heading = Heading.RIGHT;
                keyUp = false;
                iceTimer = 16;
                animation.run();
            } else if (input.getKey(KeyEvent.VK_DOWN)) {
                newY += speedValue;
                if (vec != Vec.DOWN) {
                    changeDirection = true;
                }
                vec = Vec.DOWN;
                heading = Heading.DOWN;
                keyUp = false;
                iceTimer = 16;
                animation.run();
            } else if (input.getKey(KeyEvent.VK_LEFT)) {
                newX -= speedValue;
                if (vec != Vec.LEFT) {
                    changeDirection = true;
                }
                vec = Vec.LEFT;
                heading = Heading.LEFT;
                keyUp = false;
                iceTimer = 16;
                animation.run();
            }

            if (changeDirection) {
                int rest;
                int cellSize = Game.map.getCellSize();
                if (vec == Vec.LEFT || vec == Vec.RIGHT) {
                    rest = (int) newY % cellSize;
                    if (rest < cellSize / 2) {
                        y = ((int) newY / cellSize) * cellSize;
                    } else {
                        y = ((int) newY / cellSize + 1) * cellSize;
                    }
                } else {
                    rest = (int) newX % cellSize;
                    if (rest < cellSize / 2) {
                        x = ((int) newX / cellSize) * cellSize;
                    } else {
                        x = ((int) newX / cellSize + 1) * cellSize;
                    }
                }
                changeDirection = false;
                break;
            }

            if (Collision.onIce(newX, newY, SPRITE_SIZE * SPRITE_SCALE) && keyUp) {
                if (iceTimer != 0) {
                    newX += vec.x * speedValue;
                    newY += vec.y * speedValue;
                    iceTimer--;
                }
            }


            if (!Collision.map(
                    newX,
                    newY,
                    SPRITE_SIZE * SPRITE_SCALE)) {
                if (!Collision.enemy(newX, newY, SPRITE_SIZE * SPRITE_SCALE, 100)) {
                    x = newX;
                    y = newY;
                    break;
                }
            }

            if (move) {
                break;
            }

            newX = x;
            newY = y;
            speedValue--;
        }

        animation.update();

    }
    public void render(Graphics2D g) {
        spriteMap.get(heading).render(g, x + Game.OFFSETX, y + Game.OFFSETY, animation.getFrame());
        bullet.render(g);
    }
}
