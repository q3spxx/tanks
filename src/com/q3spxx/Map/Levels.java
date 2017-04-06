package com.q3spxx.Map;

import com.q3spxx.utils.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by q3spxx on 15.03.2017.
 */
public class Levels {
    private final String PATH = "lvl/";
    private List<Level> levels;
    public Levels () throws IOException, ClassNotFoundException {
        this.levels = new ArrayList<Level>();
        this.levels.add(ResourceLoader.deserialise(this.PATH + "default.lvl"));
        this.levels.add(ResourceLoader.deserialise(this.PATH + "1.lvl"));
    }
    public Level getLevel (int index) {
        return levels.get(index);
    }
    public void saveLevel (Level level) throws IOException {
        ResourceLoader.serialise(PATH + "1.lvl", level);
    }
}
