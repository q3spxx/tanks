package com.q3spxx.Effects;

import com.q3spxx.Game.Animation;
import com.q3spxx.Game.Game;
import com.q3spxx.Graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bang extends Effect {

    private BufferedImage image;
    private final int SPRITE_SIZE = 16;
    private final int SPRITE_SCALE = Game.SPRITE_SCALE;

    public Animation animation;


    public Bang(EffectType type, boolean repeat, TextureAtlas atlas) {
        super(type);

        image = atlas.cut(16 * SPRITE_SIZE, 8 * SPRITE_SIZE,  SPRITE_SIZE * 3, SPRITE_SIZE);
        this.animation = new Animation(30.0f, 3, repeat, false, 0);
    }

    public void show (float _x, float _y) {
        x = _x - (SPRITE_SIZE * SPRITE_SCALE) / 2;
        y = _y - (SPRITE_SIZE * SPRITE_SCALE) / 2;

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
