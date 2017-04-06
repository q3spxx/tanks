package com.q3spxx.Game;

import com.q3spxx.Graphics.Sprite;
import com.q3spxx.Graphics.SpriteSheet;
import com.q3spxx.Graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Enemy extends Tanks {
    private EnemyType type;
    private Heading heading;
    private Vec vec;
    private Bullet bullet;
    private static int SPRITEX, SPRITEY;
    private static Map<Heading, Sprite> spriteMap;

    public int id;

    private enum Heading {
        UP(SPRITEX * SPRITE_SIZE + 0 * SPRITE_SIZE, SPRITEY * SPRITE_SIZE, 2 * SPRITE_SIZE, 1 * SPRITE_SIZE),
        LEFT(SPRITEX * SPRITE_SIZE + 2 * SPRITE_SIZE, SPRITEY * SPRITE_SIZE, 2 * SPRITE_SIZE, 1 * SPRITE_SIZE),
        DOWN(SPRITEX * SPRITE_SIZE + 4 * SPRITE_SIZE, SPRITEY * SPRITE_SIZE, 2 * SPRITE_SIZE, 1 * SPRITE_SIZE),
        RIGHT(SPRITEX * SPRITE_SIZE + 6 * SPRITE_SIZE, SPRITEY * SPRITE_SIZE, 2 * SPRITE_SIZE, 1 * SPRITE_SIZE);
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
    public Enemy (float x, float y, TextureAtlas atlas, EnemyType type, int id) {
        super(EntityType.Enemy, x, y, (float) type.speed);
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
        this.SPRITEX = type.spriteX;
        this.SPRITEY = type.spriteY;
        heading = Heading.DOWN;
        vec = Vec.DOWN;
        spriteMap = new HashMap<Heading, Sprite>();
        for (Heading h : Heading.values()) {
            SpriteSheet sheet = new SpriteSheet(h.texture(atlas), SPRITE_FOR_HEADING, SPRITE_SIZE);
            Sprite sprite = new Sprite(sheet, SPRITE_SCALE);
            spriteMap.put(h, sprite);
        }
        bullet = new Bullet(this, atlas);
        animation.run();
    }
    public void update () {
        animation.update();
        bullet.update();
    }
    public boolean moving () {
        float newX = x;
        float newY = y;

        newX = newX + vec.x * speed;
        newY = newY + vec.y * speed;

        if (!Collision.map(newX, newY, SPRITE_SIZE * SPRITE_SCALE)) {
            if (!Collision.enemy(newX, newY, SPRITE_SIZE * SPRITE_SCALE, id)) {
                x = newX;
                y = newY;
                return true;
            }
            return false;
        } else {
            return false;
        }

    }
    public void shot () {
        if (!bullet.flying && !bullet.bang.animation.active) {
            bullet.shot(x + (SPRITE_SIZE * SPRITE_SCALE) / 2, y + (SPRITE_SIZE * SPRITE_SCALE) / 2, vec, type.bulletSpeed);
        }
    }
    public void changeDirection () {
        if (vec == Vec.UP || vec == Vec.DOWN) {
            if (Math.random() < 0.5) {
                vec = Vec.LEFT;
                heading = Heading.LEFT;
            } else {
                vec = Vec.RIGHT;
                heading = Heading.RIGHT;
            }
        } else {
            if (Math.random() < 0.5) {
                vec = Vec.UP;
                heading = Heading.UP;
            } else {
                vec = Vec.DOWN;
                heading = Heading.DOWN;

            }
        }
    }
    public void render (Graphics2D g) {
        spriteMap.get(heading).render(g, x + Game.OFFSETX, y + Game.OFFSETY, animation.getFrame());
        bullet.render(g);
    }
}
