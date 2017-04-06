package com.q3spxx.Map;

import com.q3spxx.Game.Vec;
import com.q3spxx.Tiles.TileStateType;
import com.q3spxx.Tiles.TileType;

import java.io.Serializable;

/**
 * Created by q3spxx on 15.03.2017.
 */
public class Cell implements Serializable {
    private static final long serialVersionUID = 101l;

    private int type;
    private int state;
    private int grid = 15;

    public Cell (int type) {
        this.type = type;
        this.state = 0;
        this.grid = 15;
    }
    public void setType (Vec vec, int upgrade) {

        if (type == TileType.BRICK.num()) {
            int test;
            switch (vec.n) {
                case 0:
                    test = grid & 3;
                    if (test == 0) {
                        grid = grid & 3;
                    } else {
                        grid = grid & 12;
                    }
                    break;
                case 1:
                    test = grid & 5;
                    if (test == 0) {
                        grid = grid & 5;
                    } else {
                        grid = grid & 10;
                    }
                    break;
                case 2:
                    test = grid & 12;
                    if (test == 0) {
                        grid = grid & 12;
                    } else {
                        grid = grid & 3;
                    }
                    break;
                case 3:
                    test = grid & 10;
                    if (test == 0) {
                        grid = grid & 10;
                    } else {
                        grid = grid & 5;
                    }
                    break;
            }
        }

        if (type == TileType.METAL.num() && upgrade == 3) {
            grid = 0;
        }

        if (grid == 0) {
            setType(0);
        }
        state = TileStateType.getStateFromGrid(grid);
    }
    public int destroyed (Vec vec) {
        if (type == TileType.BRICK.num()) {
            switch (vec.n) {
                case 1:
                    return grid & 5;
                case 2:
                    return grid & 12;
                case 3:
                    return grid & 10;
                default:
                    return grid & 3;
            }
        }
        return 0;
    }
    public void setType (int _type) {
        type = _type;
    }
    public int getType () {
        return type;
    }
    public int getState () {
        return state;
    }
    public boolean getBlock () {
        return TileType.fromNum(type).block;
    }
    public boolean getDestructible () {
        return TileType.fromNum(type).destructible;
    }
    public void update () {

    }
}
