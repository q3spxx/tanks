package com.q3spxx.Game;

import com.q3spxx.IO.Input;

import java.awt.*;
import java.io.IOException;

public abstract class Entity {
    public final EntityType type;
    public static final int SPRITE_SIZE = 16;
    public static final int SPRITE_SCALE = Game.SPRITE_SCALE;

    protected float x;
    protected float y;

    protected Entity(EntityType type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}
