package com.q3spxx.utils;

import com.q3spxx.Map.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ResourceLoader  {
    public static final String RES_PATH = "res/";
    public static BufferedImage loadImage (String fileName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(RES_PATH + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cutBlack(image);

    }
    private static BufferedImage cutBlack (BufferedImage image) {
        int [] data = image.getRGB(0,0,image.getWidth(),image.getHeight(), null,0, image.getWidth());

        for (int i = 0; i < data.length; i ++) {
            Color c = new Color(data[i]);
            if (c.getRed() <= 1 && c.getGreen() <= 1 && c.getBlue() <= 1) {
                data[i] = data[i]^0xff000000;
            }

        }

        image.setRGB(0,0,image.getWidth(),image.getHeight(), data,0, image.getWidth());

        return image;
    }
    public static void serialise (String path, Level object) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.flush();
        oos.close();
    }
    public static Level deserialise (String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Level level = (Level) ois.readObject();
        return level;
    }
}
