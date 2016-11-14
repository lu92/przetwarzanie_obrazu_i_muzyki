package przetwarzanie_obrazu_i_muzyki;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Zadanie5 extends Zadanie {
    // http://atol.am.gdynia.pl/tc/Radzienski/Szum.htm
    // http://www.algorytm.org/przetwarzanie-obrazow/filtrowanie-obrazow.html
    // http://www.mif.pg.gda.pl/homepages/marcin/Wyklad6.pdf

    public Zadanie5(JFrame frame) {
        super(frame);
    }

    JMenu buildMenu() {
        JMenuItem menuItem51 = new JMenuItem("szum o rozkladzie jednostajnym (color)");
        menuItem51.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.zaciemnienieRozkładJednostajny(algorithms.generateRGBMatrix(lena), 51, 100, NoiseType.COLOR);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem52 = new JMenuItem("szum o rozkladzie jednostajnym (grey)");
        menuItem52.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.zaciemnienieRozkładJednostajny(algorithms.generateRGBMatrix(lena), 51, 100, NoiseType.BLACK_AND_WHITE);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem53 = new JMenuItem("zaklocenie o rozkladzie normalnym (color)");
        menuItem53.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.zaciemnienieRozkladNormalny(algorithms.generateRGBMatrix(lena), 0, 29, 100, NoiseType.COLOR);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem54 = new JMenuItem("zaklocenie o rozkladzie normalnym (grey)");
        menuItem54.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.zaciemnienieRozkladNormalny(algorithms.generateRGBMatrix(lena), 0, 29, 100, NoiseType.BLACK_AND_WHITE);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem55 = new JMenuItem("zaklocenie typu pieprz i sol (kolor)");
        menuItem55.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.zaciemnienieSolAndPieprz(algorithms.generateRGBMatrix(lena), 27, NoiseType.COLOR);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem56 = new JMenuItem("zaklocenie typu pieprz i sol (kolor)");
        menuItem56.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.zaciemnienieSolAndPieprz(algorithms.generateRGBMatrix(lena), 27, NoiseType.BLACK_AND_WHITE);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem57 = new JMenuItem("filtr sredniej ruchomej");
        menuItem57.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.avgFilter(algorithms.generateRGBMatrix(lena));
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem57a = new JMenuItem("filtr sredniej ruchomej 1");
        menuItem57a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.avgFilter(algorithms.generateRGBMatrix(algorithms.zaciemnienieRozkładJednostajny(algorithms.generateRGBMatrix(lena),51, 100, NoiseType.BLACK_AND_WHITE)));
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem57b = new JMenuItem("filtr sredniej ruchomej 2");
        menuItem57b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.avgFilter(algorithms.generateRGBMatrix(algorithms.zaciemnienieRozkladNormalny(algorithms.generateRGBMatrix(lena), 0, 29, 100, NoiseType.BLACK_AND_WHITE)));
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem57c = new JMenuItem("filtr sredniej ruchomej 3");
        menuItem57c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.avgFilter(algorithms.generateRGBMatrix(algorithms.zaciemnienieSolAndPieprz(algorithms.generateRGBMatrix(lena), 27, NoiseType.BLACK_AND_WHITE)));
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem58 = new JMenuItem("filtr medianowy");
        menuItem58.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.medFilter(algorithms.generateRGBMatrix(lena));
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenu menu = new JMenu("zad5");
        menu.add(menuItem51);
        menu.add(menuItem52);
        menu.add(menuItem53);
        menu.add(menuItem54);
        menu.add(menuItem55);
        menu.add(menuItem56);
        menu.add(menuItem57);
        menu.add(menuItem57a);
        menu.add(menuItem57b);
        menu.add(menuItem57c);
        menu.add(menuItem58);

        return menu;
    }
}
