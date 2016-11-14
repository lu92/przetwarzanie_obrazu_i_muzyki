package przetwarzanie_obrazu_i_muzyki;


import org.apache.commons.math3.linear.RealMatrix;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Zadanie1 extends Zadanie{

    public Zadanie1(JFrame frame) {
        super(frame);
    }

    @Override
    JMenu buildMenu() {
        JMenuItem menuItem11 = new JMenuItem("R");
        menuItem11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.transformToProperMatrix(algorithms.generateRGBMatrix(lena), RGBType.R);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem12 = new JMenuItem("G");
        menuItem12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                RealMatrix realMatrix = algorithms.generateRGBMatrix(lena);
                BufferedImage bufferedImage = algorithms.transformToProperMatrix(realMatrix, RGBType.G);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenuItem menuItem13 = new JMenuItem("B");
        menuItem13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected: " + e.getActionCommand());
                BufferedImage bufferedImage = algorithms.transformToProperMatrix(algorithms.generateRGBMatrix(lena), RGBType.B);
                frame.setContentPane(getJLabel(bufferedImage));
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JMenu menu = new JMenu("zad1");
        menu.add(menuItem11);
        menu.add(menuItem12);
        menu.add(menuItem13);
        return menu;
    }
}
