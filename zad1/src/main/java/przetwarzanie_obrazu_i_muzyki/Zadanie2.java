package przetwarzanie_obrazu_i_muzyki;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Zadanie2 extends Zadanie {

    public Zadanie2(JFrame frame) {
        super(frame);
    }

    @Override
    JMenu buildMenu() {

        JMenuItem menuItem21 = new JMenuItem("skala szarosci");
        menuItem21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.skalaSzarosci(algorithms.generateRGBMatrix(lena));
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem22 = new JMenuItem("negatyw obrazu");
        menuItem22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.negatyw(algorithms.generateRGBMatrix(lena));
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem23 = new JMenuItem("sepia");
        menuItem23.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.sepia(algorithms.generateRGBMatrix(lena), 20);

                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

//        JMenuItem menuItem241 = new JMenuItem("obrot 90");
//        menuItem241.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Selected: " + e.getActionCommand());
//                BufferedImage bufferedImage = algorithms.obrot(algorithms.generateRGBMatrix(lena), 90);
//                frame.setContentPane(getJLabel(bufferedImage));
//                SwingUtilities.updateComponentTreeUI(frame);
//            }
//        });

        JMenuItem menuItem25 = new JMenuItem("przesuniecie");
        menuItem25.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.przesuniecie(algorithms.generateRGBMatrix(lena), 90);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenu menu = new JMenu("zad2");
        menu.add(menuItem21);
        menu.add(menuItem22);
        menu.add(menuItem23);
//        menu.add(menuItem241);
        menu.add(menuItem25);
        return menu;
    }
}
