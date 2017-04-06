package com.q3spxx.Game;

public abstract class Tanks extends Entity {
    public static final int SPRITE_FOR_HEADING = 2;

    protected float speed;
    public static int upgrade;

    protected Animation animation;

    public Tanks (EntityType type, float x, float y, float speed) {
        super(type, x, y);
        this.speed = speed;
        this.animation = new Animation(20.0f, SPRITE_FOR_HEADING, true, true, 0);
    }

}
