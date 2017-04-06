package com.q3spxx.Tiles;

/**
 * Created by q3spxx on 14.03.2017.
 */
public abstract class TileStateType {
    public static int getStateFromGrid (int grid) {
        switch (grid) {
            case 1:
                return 13;
            case 2:
                return 14;
            case 3:
                return 2;
            case 4:
                return 12;
            case 5:
                return 4;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 11;
            case 9:
                return 5;
            case 10:
                return 3;
            case 11:
                return 8;
            case 12:
                return 1;
            case 13:
                return 10;
            case 14:
                return 9;
            case 15:
                return 0;
            default:
                return 0;
        }
    }
}
