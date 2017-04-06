package com.q3spxx.Tiles;

import com.q3spxx.Game.Game;
import com.q3spxx.Graphics.TextureAtlas;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by q3spxx on 13.03.2017.
 */
public class Tiles {
    private final int SIZE = 8;
    private final int SCALE = Game.SPRITE_SCALE;
    private final int TOTAL_SIZE = SCALE * SIZE;
    public Map<TileType, Tile> tiles = new HashMap();

    public Tiles (TextureAtlas atlas) {
        init(atlas);
    }
    private void init (TextureAtlas atlas) {
        tiles.put(TileType.EMPTY, new Tile(atlas, SIZE, TileType.EMPTY, 1));
        tiles.put(TileType.BRICK, new Tile(atlas, SIZE, TileType.BRICK, 1));
        tiles.put(TileType.METAL, new Tile(atlas, SIZE, TileType.METAL, 1));
        tiles.put(TileType.WATER, new Tile(atlas, SIZE, TileType.WATER, 2));
        tiles.put(TileType.GRASS, new Tile(atlas, SIZE, TileType.GRASS, 1));
        tiles.put(TileType.ICE, new Tile(atlas, SIZE, TileType.ICE, 1));
    }
    public void runAnimation () {
        for (int i = 1; i < tiles.size(); i++) {
            tiles.get(TileType.fromNum(i)).runAnimation();
        }
    }
    public void update () {
        for (int i = 1; i < tiles.size(); i++) {
            tiles.get(TileType.fromNum(i)).update();
        }
    }
    public void render (Graphics2D g, int x, int y, int index, int state) {
        tiles.get(TileType.fromNum(index)).render(g, x * TOTAL_SIZE + Game.OFFSETX, y * TOTAL_SIZE + Game.OFFSETY, TOTAL_SIZE, state);
    }
}
