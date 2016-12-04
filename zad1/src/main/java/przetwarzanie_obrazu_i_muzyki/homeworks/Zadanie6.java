package przetwarzanie_obrazu_i_muzyki.homeworks;

import przetwarzanie_obrazu_i_muzyki.GreenPeppers;
import przetwarzanie_obrazu_i_muzyki.NoiseType;
import przetwarzanie_obrazu_i_muzyki.RedEye;
import przetwarzanie_obrazu_i_muzyki.Skin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Zadanie6 extends Zadanie {
    public Zadanie6(JFrame frame) {
        super(frame);
    }

    public JMenu buildMenu() {
        JMenuItem menuItem61 = new JMenuItem("konwersja z formatu RGB na YCbCr");
        menuItem61.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem62 = new JMenuItem("konwersja z formatu YCbCr na RGB");
        menuItem62.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem63 = new JMenuItem("konwersja z formatu RGB na HLS");
        menuItem63.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem64 = new JMenuItem("zielone papryczki");
        menuItem64.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.toBufferedImage(new GreenPeppers(algorithms.generateRGBMatrix(peppers)).getResult());
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem65 = new JMenuItem("detekcja skory");
        menuItem65.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.toBufferedImage(new Skin(algorithms.generateRGBMatrix(face)).getResult());
//                BufferedImage bufferedImage = algorithms.toBufferedImage(algorithms.convertRGBToHSV(algorithms.generateRGBMatrix(lena)));
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem66 = new JMenuItem("detekcja efektu czerwonych oczu");
        menuItem66.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.toBufferedImage(new RedEye(algorithms.generateRGBMatrix(face2)).getResult());
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });


        JMenu menu = new JMenu("zad6");
        menu.add(menuItem61);
        menu.add(menuItem62);
        menu.add(menuItem63);
        menu.add(menuItem64);
        menu.add(menuItem65);
        menu.add(menuItem66);
        return menu;
    }
}
