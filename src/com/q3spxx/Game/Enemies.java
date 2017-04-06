package com.q3spxx.Game;

import com.q3spxx.Effects.EffectType;
import com.q3spxx.Effects.Respawn;
import com.q3spxx.Graphics.TextureAtlas;
import com.q3spxx.utils.Time;

import java.awt.*;
import java.util.ArrayList;

public class Enemies {
    private final int SPRITE_SIZE = 16;
    private final int SPRITE_SCALE = Game.SPRITE_SCALE;
    private final int TOTAL_SIZE = SPRITE_SIZE * SPRITE_SCALE;

    private TextureAtlas atlas;
    private long lastTime;
    private Respawn respawnAnimation;
    private Cords [] respawnCords = new Cords[3];
    private int currentRespawnIndex = 0;
    protected ArrayList<Enemy> enemies = new ArrayList();

    private int ENEMIES_COUNT = 20;
    private int CURRENT_ENEMY_ID = 0;

    public Enemies (TextureAtlas atlas) {
        this.atlas = atlas;
        this.lastTime = Time.get();

        respawnCords[0] = new Cords(0 * TOTAL_SIZE, 0 * TOTAL_SIZE);
        respawnCords[1] = new Cords(6 * TOTAL_SIZE, 0 * TOTAL_SIZE);
        respawnCords[2] = new Cords(12 * TOTAL_SIZE, 0 * TOTAL_SIZE);

        this.respawnAnimation = new Respawn(EffectType.Respawn, false, atlas) {
            @Override
            public void animationCallback () {
                enemies.add( new Enemy(respawnCords[currentRespawnIndex].x, respawnCords[currentRespawnIndex].y, atlas, EnemyType.USUAL, CURRENT_ENEMY_ID));
                currentRespawnIndex++;
                CURRENT_ENEMY_ID++;
                if (currentRespawnIndex == 3) {
                    currentRespawnIndex = 0;
                }
            }
        };
    }
    private void respawn () {
        respawnAnimation.show(respawnCords[currentRespawnIndex].x, respawnCords[currentRespawnIndex].y);
    }
    public void update () {
        if (enemies.size() < 4 && CURRENT_ENEMY_ID < ENEMIES_COUNT) {
            long now = Time.get();
            if (now - lastTime > Game.ai.INTERVAL) {
                respawn();
                lastTime = now;
            }
        }
        respawnAnimation.update();
    }
    public void render (Graphics2D g) {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(g);
        }
        respawnAnimation.render(g);
    }
}
