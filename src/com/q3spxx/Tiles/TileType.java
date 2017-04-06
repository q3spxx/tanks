package com.q3spxx.Tiles;

public enum TileType {
    EMPTY(0, 37, 8, 1, false, false),
    BRICK(1, 32, 8, 1, true, true),
    METAL(2, 32, 9, 1, true, true),
    WATER(3, 33, 10, 2, true, false),
    GRASS(-1, 33, 9, 1, false, false),
    ICE(4, 34 , 9, 1, false, false);

    private int n;
    private int x;
    private int y;
    private int width;
    public boolean block;
    public boolean destructible;
    TileType (int n, int x, int y, int width, boolean block, boolean destructible) {
        this.n = n;
        this.x = x;
        this.y = y;
        this.width = width;
        this.block = block;
        this.destructible = destructible;
    }

    public int getX () {
        return x;
    }
    public int getY () {
        return y;
    }
    public int getWidth () {
        return width;
    }

    public int num () {
        return n;
    }

    public static TileType fromNum (int n) {
        switch (n) {
            case 1:
                return BRICK;
            case 2:
                return METAL;
            case 3:
                return WATER;
            case -1:
                return GRASS;
            case 4:
                return ICE;
            default:
                return EMPTY;
        }
    }
}
