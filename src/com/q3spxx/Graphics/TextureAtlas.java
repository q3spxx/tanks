package com.q3spxx.Graphics;

import com.q3spxx.utils.ResourceLoader;

import java.awt.image.BufferedImage;

public class TextureAtlas {
    BufferedImage image;
    public TextureAtlas (String imageName) {
        image = ResourceLoader.loadImage(imageName);
    }

    public BufferedImage cut (int x, int y, int w, int h) {
        return image.getSubimage(x, y, w, h);
    }
}
