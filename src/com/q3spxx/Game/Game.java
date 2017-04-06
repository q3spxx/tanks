package com.q3spxx.Game;

import com.q3spxx.Constructor.Constructor;
import com.q3spxx.Display.Display;
import com.q3spxx.IO.Input;
import com.q3spxx.Graphics.TextureAtlas;
import com.q3spxx.Interface.Interface;
import com.q3spxx.Map.GameMap;
import com.q3spxx.Map.Levels;
import com.q3spxx.Tiles.Tiles;
import com.q3spxx.utils.Time;

import java.awt.*;
import java.io.IOException;

public class Game implements Runnable {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 896;
    public static final String TITLE = "Tanks";
    public static final int CLEAR_COLOR = 0xff636363;
    public static final int NUM_BUFFERS = 3;
    public static final int SPRITE_SCALE = 4;
    public static final int OFFSETX = 16 * SPRITE_SCALE;
    public static final int OFFSETY = 8 * SPRITE_SCALE;

    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1;

    private int level = 1;
    private GameType GAME_TYPE = GameType.PLAYER1;

    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;
    private Input input;
    private TextureAtlas atlas;
    private Player player;
    private Cursor cursor;
    private Interface anInterface;
    public static Enemies enemies;
    public static AI ai;
    public static Flag flag;
    public static GameMap map;
    public static Levels levels;
    public static Tiles tiles;
    public static Constructor constructor;

    public static int LEVEL = 1;

    public static final String ATLAS_FILE_NAME = "texture_atlas.png";


    public Game () throws IOException, ClassNotFoundException {
        running = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
        player = new Player(256, 768, 4, atlas, 2);

        enemies = new Enemies(atlas);
        ai = new AI();

        flag = new Flag(atlas);
        constructor = new Constructor();
        cursor = new Cursor(0, 0, atlas, constructor);
        tiles = new Tiles(atlas);
        tiles.runAnimation();
        levels = new Levels();
        map = new GameMap();
        switch (GAME_TYPE) {
            case CONSTRUCTOR:
                map.loadLevel(0);
                break;
            case PLAYER1:
                map.loadLevel(level);
                break;
        }
        anInterface = new Interface(atlas, map.getLevelGrid().length * map.getCellSize(), 0, 8, SPRITE_SCALE);

    }

    public synchronized void start () {
        if (running) {
            return;
        }
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop () {
        if (!running) {
            return;
        }
        running = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();
    }

    private void update () throws IOException {
        switch (GAME_TYPE) {
            case CONSTRUCTOR:
                Game.map.update();
                cursor.update(input);
                break;
            case PLAYER1:
                Game.map.update();
                player.update(input);
                enemies.update();
                ai.update(enemies);
                break;
            case PLAYER2:
                Game.map.update();
                player.update(input);
                break;
            case DEFAULT:
        }
    }

    private void render () {
        Display.clear();
        switch (GAME_TYPE) {
            case CONSTRUCTOR:
                anInterface.render(graphics);
                map.renderUnder(graphics);
                map.renderOver(graphics);
                flag.render(graphics);
                cursor.render(graphics);
                break;
            case PLAYER1:
                anInterface.render(graphics);
                map.renderUnder(graphics);
                flag.render(graphics);
                player.render(graphics);
                enemies.render(graphics);
                map.renderOver(graphics);
                break;
            case PLAYER2:
                player.render(graphics);
                break;
            case DEFAULT:
        }
        Display.swapBuffers();
    }

    private void cleanUp () {
        Display.destroy();
    }

    public void run () {
        int fps = 0;
        int upd = 0;
        int updl = 0;

        long count = 0;

        float delta = 0;

        long lastTime = Time.get();
        while (running) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;

            boolean render = false;
            delta+= (elapsedTime / UPDATE_INTERVAL);
            while (delta > 1) {
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                upd++;
                delta--;
                if (render) {
                    updl++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count >= Time.SECOND) {
                Display.setTitle(TITLE + " || fps: " + fps + " | Upd: " + upd + " | Updl: " + updl + " | Level: " + map.getLevelTitle());
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }
        }
    }

}
