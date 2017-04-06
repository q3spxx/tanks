package com.q3spxx.Map;

import com.q3spxx.Game.Game;
import com.q3spxx.Game.Vec;

import java.awt.*;
import java.io.IOException;

public class GameMap {
    private Level level;
    private final int CELL_SIZE = 8;
    private final int CELL_SCALE = Game.SPRITE_SCALE;
    private int offsetX = Game.OFFSETX;
    private int offsetY = Game.OFFSETY;
    public GameMap () {
//        this.level = new Level();
//        this.level = Game.levels.getLevel(0);
    }
    public void loadLevel (int levelIndex) {
        level = Game.levels.getLevel(levelIndex);
    }
    public Cell [][] getLevelGrid () {
        return level.grid;
    }
    public int getCellSize () {
        return CELL_SIZE * CELL_SCALE;
    }
    public String getLevelTitle () {
        return level.title;
    }
    public void changeTypeCell (int x1, int y1, int x2, int y2, Vec vec, int upgrade) {
        if (
                x1 < level.grid.length &&
                y1 < level.grid[0].length &&
                x2 < level.grid.length &&
                y2 < level.grid[0].length
                ) {
            if (
                    level.grid[x1][y1].destroyed(vec) == 0 && level.grid[x2][y2].destroyed(vec) == 0 ||
                    level.grid[x1][y1].destroyed(vec) != 0 && level.grid[x2][y2].destroyed(vec) != 0
                    ) {
                level.grid[x1][y1].setType(vec, upgrade);
                level.grid[x2][y2].setType(vec, upgrade);

            } else {
                if (level.grid[x1][y1].destroyed(vec) == 0) {
                    level.grid[x2][y2].setType(vec, upgrade);
                } else if (level.grid[x2][y2].destroyed(vec) == 0) {
                    level.grid[x1][y1].setType(vec, upgrade);
                }
            }
        }
    }
    public void changeTypeCell (int x, int y, int type) {
        level.grid[x][y].setType(type);
    }
    public void saveMap () throws IOException {
        Game.levels.saveLevel(level);
    }
    public void update () {
        Game.tiles.update();
    }
    public void renderUnder (Graphics2D g) {

        g.setColor(Color.black);
        g.fillRect(offsetX, offsetY, level.grid.length * CELL_SIZE * CELL_SCALE, level.grid[0].length * CELL_SIZE * CELL_SCALE);

        for (int x = 0; x < level.grid.length; x++) {
            for (int y = 0; y < level.grid[x].length; y++) {
                if (level.grid[x][y].getType() >= 0) {
                    Game.tiles.render(g, x, y, level.grid[x][y].getType(), level.grid[x][y].getState());
                }
            }
        }
    }
    public void renderOver (Graphics2D g) {
        for (int x = 0; x < level.grid.length; x++) {
            for (int y = 0; y < level.grid[x].length; y++) {
                if (level.grid[x][y].getType() < 0) {
                    Game.tiles.render(g, x, y, level.grid[x][y].getType(), level.grid[x][y].getState());
                }
            }
        }
    }
}
