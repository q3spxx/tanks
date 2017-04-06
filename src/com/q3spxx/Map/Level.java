package com.q3spxx.Map;

import java.io.Serializable;

/**
 * Created by q3spxx on 15.03.2017.
 */
public class Level implements Serializable {
    private final static long serialVersionUID = 11l;
    public String title = "default";
    public Cell [][] grid = new Cell [26][26];
    public Level () {
        for (int x = 0; x < this.grid.length; x++) {
            for (int y = 0; y < this.grid[x].length; y++) {
                this.grid[y][x] = new Cell(0);
            }
        }
    }
}
