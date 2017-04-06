package com.q3spxx.Constructor;

/**
 * Created by q3spxx on 20.03.2017.
 */
public class Constructor {
    public final TileLocation [] constructorTiles = new TileLocation [14];
    public int current = 0;
    public boolean moved = false;
    public int count;
    public Constructor () {
        constructorTiles[0] = TileLocation.DEFAULT_EMPTY;
        constructorTiles[1] = TileLocation.RIGHT_BRICK;
        constructorTiles[2] = TileLocation.DOWN_BRICK;
        constructorTiles[3] = TileLocation.LEFT_BRICK;
        constructorTiles[4] = TileLocation.UP_BRICK;
        constructorTiles[5] = TileLocation.DEFAULT_BRICK;
        constructorTiles[6] = TileLocation.RIGHT_METAL;
        constructorTiles[7] = TileLocation.DOWN_METAL;
        constructorTiles[8] = TileLocation.LEFT_METAL;
        constructorTiles[9] = TileLocation.UP_METAL;
        constructorTiles[10] = TileLocation.DEFAULT_METAL;
        constructorTiles[11] = TileLocation.DEFAULT_WATER;
        constructorTiles[12] = TileLocation.DEFAULT_GRASS;
        constructorTiles[13] = TileLocation.DEFAULT_ICE;

        this.count = this.constructorTiles.length - 1;
    }
}
