package przetwarzanie_obrazu_i_muzyki;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public enum Picture {
    LENA("/LENA_512.jpg"),
    EAGLE("/eagle.jpg"),
    LAKE("/lake.jpg"),
    CASTLE("/castle.jpg"),
    RAIN("/rain.jpg"),
    BALL("/kola.jpg"),
    TEXTURE("/8.jpg"),
    S1("/s1.png"),
    S2("/s2.png"),
    KING1("/krol1.png"),
    KING2("/krol2.png"),
    CIRCLE("/ko4.jpg"),
    PEPPERS("/peppers.png"),
    FACE("/face.jpg"),
    FACE2("/face2.jpg");



    private String filename;
    private BufferedImage image;
    private int width, height;


    Picture(String filename) {
        this.filename = filename;
        try {
            // try to read from file in working directory
            File file = new File(filename);
            if (file.isFile()) {
                image = ImageIO.read(file);
            }

            // now try to read from file in same directory as this .class file
            else {
                URL url = getClass().getResource(filename);
                if (url == null) {
                    url = new URL(filename);
                }
                image = ImageIO.read(url);
            }

            if (image == null) {
                throw new IllegalArgumentException("Invalid lena file: " + filename);
            }

            width = image.getWidth(null);
            height = image.getHeight(null);
        } catch (IOException e) {
            // e.printStackTrace();
            throw new RuntimeException("Could not open file: " + filename);
        }

    }

    public String getFilename() {
        return filename;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
