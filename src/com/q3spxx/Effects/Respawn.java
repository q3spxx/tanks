package com.q3spxx.Effects;

import com.q3spxx.Game.Animation;
import com.q3spxx.Game.Game;
import com.q3spxx.Graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Respawn extends Effect {
    private BufferedImage image;
    private final int SPRITE_SIZE = 16;
    private final int SPRITE_SCALE = Game.SPRITE_SCALE;

    public Animation animation;
    public Respawn (EffectType type, boolean repeat, TextureAtlas atlas) {
        super(type);
        image = atlas.cut(16 * SPRITE_SIZE, 6 * SPRITE_SIZE,  SPRITE_SIZE * 5, SPRITE_SIZE);
        this.animation = new Animation(12.0f, 3, repeat, false, 2) {
            @Override
            public void callback () {
                animationCallback();
            }
        };
    }
    public void animationCallback () {
        System.out.println("end");
    }
    public void show (float _x, float _y) {
        x = _x;
        y = _y;

        animation.run();
    }

    @Override
    public void update() {
        animation.update();
    }

    @Override
    public void render(Graphics2D g) {
        if (animation.active) {

            g.drawImage(
                    image,
                    (int) x + Game.OFFSETX,
                    (int) y + Game.OFFSETY,
                    (int) x + SPRITE_SIZE * SPRITE_SCALE + Game.OFFSETX,
                    (int) y + SPRITE_SIZE * SPRITE_SCALE + Game.OFFSETY,
                    animation.getFrame() * SPRITE_SIZE,
                    0,
                    animation.getFrame() * SPRITE_SIZE + SPRITE_SIZE,
                    SPRITE_SIZE,
                    null);
        }

    }
}
