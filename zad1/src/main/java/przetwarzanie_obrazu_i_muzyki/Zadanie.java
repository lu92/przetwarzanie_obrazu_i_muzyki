package przetwarzanie_obrazu_i_muzyki;

import javax.swing.*;
import java.awt.image.BufferedImage;

public abstract class Zadanie {

    protected Algorithms algorithms = new Algorithms();
    protected BufferedImage lena = Picture.LENA.getImage();
    protected BufferedImage eagle = Picture.EAGLE.getImage();
    protected BufferedImage lake = Picture.LAKE.getImage();
    protected BufferedImage castle = Picture.CASTLE.getImage();
    protected BufferedImage rain = Picture.RAIN.getImage();
    protected BufferedImage s1 = Picture.S1.getImage();
    protected BufferedImage s2 = Picture.S2.getImage();
    protected BufferedImage king1 = Picture.KING1.getImage();
    protected BufferedImage king2 = Picture.KING2.getImage();
    protected BufferedImage circle = Picture.CIRCLE.getImage();
    protected BufferedImage ball = Picture.BALL.getImage();
    protected BufferedImage texture = Picture.TEXTURE.getImage();
    protected JFrame frame;

    public Zadanie(JFrame frame) {
        this.frame = frame;
    }

    abstract JMenu buildMenu();

    protected JLabel getJLabel(BufferedImage image) {
        if (image == null) return null;
        ImageIcon icon = new ImageIcon(image);
        return new JLabel(icon);
    }
}
