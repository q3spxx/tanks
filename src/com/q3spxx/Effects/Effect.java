package com.q3spxx.Effects;

import java.awt.*;

/**
 * Created by q3spxx on 25.03.2017.
 */
public abstract class Effect {
    private final EffectType type;
    protected float x = 0;
    protected float y = 0;

    public Effect (EffectType type) {
        this.type = type;
    }

    public abstract void update ();

    public abstract void render (Graphics2D g);
}
