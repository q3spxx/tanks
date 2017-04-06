package com.q3spxx.Constructor;

/**
 * Created by q3spxx on 20.03.2017.
 */
public enum TileLocation {
    DEFAULT_EMPTY(0, 0),
    RIGHT_BRICK(1, 1),
    DOWN_BRICK(2, 1),
    LEFT_BRICK(3, 1),
    UP_BRICK(4, 1),
    DEFAULT_BRICK(0, 1),
    RIGHT_METAL(1, 2),
    DOWN_METAL(2, 2),
    LEFT_METAL(3, 2),
    UP_METAL(4, 2),
    DEFAULT_METAL(0, 2),
    DEFAULT_WATER(0, 3),
    DEFAULT_GRASS(0, -1),
    DEFAULT_ICE(0, 4);

    public int [][] location = new int [2][2];
    public int type;

    TileLocation (int location, int type) {
        this.type = type;
        switch (location) {
            case 1:
                this.location [0][0] = 0;
                this.location [0][1] = 0;
                this.location [1][0] = 1;
                this.location [1][1] = 1;
                break;
            case 2:
                this.location [0][0] = 0;
                this.location [0][1] = 1;
                this.location [1][0] = 0;
                this.location [1][1] = 1;
                break;
            case 3:
                this.location [0][0] = 1;
                this.location [0][1] = 1;
                this.location [1][0] = 0;
                this.location [1][1] = 0;
                break;
            case 4:
                this.location [0][0] = 1;
                this.location [0][1] = 0;
                this.location [1][0] = 1;
                this.location [1][1] = 0;
                break;
            default:
                this.location [0][0] = 1;
                this.location [0][1] = 1;
                this.location [1][0] = 1;
                this.location [1][1] = 1;
        }
    }
}
