package com.q3spxx.Graphics;

import com.q3spxx.Graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    private SpriteSheet sheet;
    private  float scale;

    public Sprite (SpriteSheet sheet, float scale) {
        this.sheet = sheet;
        this.scale = scale;
    }

    public void render (Graphics2D g, float x, float y, int frame) {
        BufferedImage image = sheet.getSprite(frame);
        g.drawImage(image, (int) (x), (int) (y), (int) (image.getWidth() * scale), (int) (image.getHeight() * scale), null);
    }
}
