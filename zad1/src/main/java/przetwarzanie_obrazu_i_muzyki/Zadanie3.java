package przetwarzanie_obrazu_i_muzyki;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Zadanie3 extends Zadanie {

    public Zadanie3(JFrame frame) {
        super(frame);
    }


    @Override
    JMenu buildMenu() {
        JMenuItem menuItem31 = new JMenuItem("zwieksz kolor obrazu");
        menuItem31.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.zwiekszKolorObrazu(algorithms.generateRGBMatrix(lena), 90);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem32 = new JMenuItem("dodaj obrazy");
        menuItem32.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.dodajObrazy(algorithms.generateRGBMatrix(lake), algorithms.generateRGBMatrix(eagle), 0.5);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem33 = new JMenuItem("dodaj obrazy z saturacja");
        menuItem33.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.dodajObrazySaturacja(algorithms.generateRGBMatrix(castle), algorithms.generateRGBMatrix(rain), 0.2);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem34 = new JMenuItem("kola i fale");
        menuItem34.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.dodajObrazySaturacja(algorithms.generateRGBMatrix(ball), algorithms.generateRGBMatrix(texture), 0.2);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem35 = new JMenuItem("odejmij obrazy");
        menuItem35.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.odejmijObrazy(algorithms.generateRGBMatrix(lake), algorithms.generateRGBMatrix(eagle), 0.6);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem36a = new JMenuItem("3 roznice - kopciuszek");
        menuItem36a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.odejmijObrazy(algorithms.generateRGBMatrix(s1), algorithms.generateRGBMatrix(s2), 0.6);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem36b = new JMenuItem("3 roznice - krol");
        menuItem36b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.odejmijObrazy(algorithms.generateRGBMatrix(king1), algorithms.generateRGBMatrix(king2), 0.6);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem37 = new JMenuItem("przemnoz obrazy");
        menuItem37.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.przemnozObrazy(algorithms.generateRGBMatrix(circle), algorithms.generateRGBMatrix(lena), 0.6);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem38 = new JMenuItem("podziel obrazy");
        menuItem38.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.podzielObrazy(algorithms.generateRGBMatrix(lake), algorithms.generateRGBMatrix(eagle), 0.6);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem39a = new JMenuItem("3 roznice - kopciuszek(dzielenie)");
        menuItem39a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.podzielObrazy(algorithms.generateRGBMatrix(s1), algorithms.generateRGBMatrix(s2), 0.9);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem39b = new JMenuItem("3 roznice - krol(dzielenie)");
        menuItem39b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.podzielObrazy(algorithms.generateRGBMatrix(king1), algorithms.generateRGBMatrix(king2), 0.97);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenu menu = new JMenu("zad3");
        menu.add(menuItem31);
        menu.add(menuItem32);
        menu.add(menuItem33);
        menu.add(menuItem34);
        menu.add(menuItem35);
        menu.add(menuItem36a);
        menu.add(menuItem36b);
        menu.add(menuItem37);
        menu.add(menuItem38);
        menu.add(menuItem39a);
        menu.add(menuItem39b);
        return menu;
    }
}
