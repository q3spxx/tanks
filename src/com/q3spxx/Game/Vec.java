package com.q3spxx.Game;

/**
 * Created by q3spxx on 24.03.2017.
 */
public enum Vec {
    UP(0, 0, -1),
    LEFT(1, -1, 0),
    DOWN(2, 0, 1),
    RIGHT(3, 1, 0);
    public int x;
    public int y;
    public int n;
    Vec (int n, int x, int y) {
        this.x = x;
        this.y = y;
        this.n = n;
    }
}
