package com.q3spxx.Game;

public class AI {
    public long INTERVAL;
    public AI () {
        this.INTERVAL = (4000 - 25 * (Game.LEVEL - 1)) * 1000000l;
    }
    public void update (Enemies enemies) {
        for (int i = 0; i < enemies.enemies.size(); i++) {

            enemies.enemies.get(i).update();
            if (!enemies.enemies.get(i).moving()) {
                enemies.enemies.get(i).changeDirection();
            }
            if ((int) (Math.random() * 16) % 16 == 0) {
                enemies.enemies.get(i).shot();
            }
        }
    }
}
