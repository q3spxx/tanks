package com.q3spxx.Game;

import com.q3spxx.Map.Cell;
import com.q3spxx.Tiles.TileType;

import java.util.ArrayList;

/**
 * Created by q3spxx on 22.03.2017.
 */
public abstract class Collision {
    protected static boolean map (float x, float y, int size) {
        int cellSize = Game.map.getCellSize();
        int scale = size / cellSize;

        Cell [][] grid = Game.map.getLevelGrid();

        if (scale < 1) {
            return mapSpriteSize((int) x, (int) y, grid, size, cellSize);
        } else {
            for (int xNum = 0; xNum < scale; xNum++) {
                for (int yNum = 0; yNum < scale; yNum++) {
                    if (mapCell((int) x + xNum * cellSize, (int) y + yNum * cellSize, grid, cellSize)) {
                        return true;
                    }
                }
            }
        }

        return false;

    }
    private static boolean mapSpriteSize (int x, int y, Cell [][] grid, int spriteSize, int cellSize) {
        if (
                x < 0 ||
                y < 0 ||
                x + spriteSize > cellSize * grid.length||
                y + spriteSize > cellSize * grid[0].length) {
            return true;
        }
        if (
                grid[x / cellSize][y / cellSize].getDestructible() ||
                grid[(x + spriteSize - 1) / cellSize][y / cellSize].getDestructible() ||
                grid[x / cellSize][(y + spriteSize - 1) / cellSize].getDestructible() ||
                grid[(x + spriteSize - 1) / cellSize][(y + spriteSize - 1) / cellSize].getDestructible()
                ) {
            return true;
        }
        return false;
    }
    private static boolean mapCell (int x, int y, Cell[][] grid, int cellSize) {
        if (
                x < 0 ||
                y < 0 ||
                x + cellSize > cellSize * grid.length||
                y + cellSize > cellSize * grid[0].length) {
            return true;
        }
        if (
                grid[x / cellSize][y / cellSize].getBlock() ||
                grid[(x + cellSize - 1) / cellSize][y / cellSize].getBlock() ||
                grid[x / cellSize][(y + cellSize - 1) / cellSize].getBlock() ||
                grid[(x + cellSize - 1) / cellSize][(y + cellSize - 1) / cellSize].getBlock()
                ) {
            return true;
        }
        return false;
    }
    public static boolean onIce (float x, float y, int size) {
        int cellSize = Game.map.getCellSize();
        Cell [][] grid = Game.map.getLevelGrid();
        int scale = size / cellSize;
        if (
                grid[(int) x / cellSize][(int) y / cellSize].getType() == TileType.ICE.num() &&
                grid[(int) (x + cellSize * scale - 1) / cellSize][(int) y / cellSize].getType() == TileType.ICE.num() &&
                grid[(int) x / cellSize][(int) (y +cellSize * scale - 1) / cellSize].getType() == TileType.ICE.num() &&
                grid[(int) (x + cellSize * scale - 1) / cellSize][(int) (y +cellSize * scale - 1) / cellSize].getType() == TileType.ICE.num()
                ) {
            return true;
        }
        return false;
    }
    public static boolean checkFlag (float x, float y) {
        if (
                x > Game.flag.getX() &&
                y > Game.flag.getY() &&
                x < Game.flag.getX() + Game.flag.getSize() &&
                y < Game.flag.getY() + Game.flag.getSize()
                ) {
            return true;
        }
        return false;
    }
    public static boolean enemy (float x, float y, int size, int id) {
        ArrayList<Enemy> enemies = Game.enemies.enemies;
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (id == enemy.id) {
                continue;
            }
            if (
                    checkPoint(x, y, enemy.x, enemy.y, size) ||
                    checkPoint(x + size, y, enemy.x, enemy.y, size) ||
                    checkPoint(x, y + size, enemy.x, enemy.y, size) ||
                    checkPoint(x + size, y + size, enemy.x, enemy.y, size)
                    ) {
                return true;
            }
        }
        return false;
    }
    private static boolean checkPoint (float x, float y, float X, float Y,  float SIZE) {
        if (x >= X && x <= X + SIZE && y >= Y && y <= Y + SIZE) {
            return true;
        } else {
            return false;
        }
    }
}
