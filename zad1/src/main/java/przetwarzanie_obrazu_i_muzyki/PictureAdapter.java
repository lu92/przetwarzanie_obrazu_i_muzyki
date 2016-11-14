package przetwarzanie_obrazu_i_muzyki;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.win32.StdCallLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public final class PictureAdapter implements ActionListener {
    private BufferedImage currentImage;
    private BufferedImage lena;
    private JFrame frame;                      // on-screen view
    private boolean isOriginUpperLeft = true;  // location of origin
    private int width, height;           // width and height

    public PictureAdapter() {
        lena = Picture.LENA.getImage();
//        currentImage = lena;
    }

//    private RealMatrix generateRGBMatrix(BufferedImage image) {
//        RealMatrix decodedRGBMatrix = MatrixUtils.createRealMatrix(image.getWidth(), image.getHeight());
//        for (int i = 0; i < image.getWidth(); i++) {
//            for (int j = 0; j < image.getHeight(); j++) {
//                decodedRGBMatrix.addToEntry(i, j, image.getRGB(i, j));
//            }
//        }
//        return decodedRGBMatrix;
//    }
//
//    private BufferedImage zwiekszKolorObrazu(RealMatrix matrix, int color) {
//        BufferedImage bufferedImage = new BufferedImage(matrix.getRowDimension(), matrix.getColumnDimension(), BufferedImage.TYPE_INT_RGB);
//        for (int x = 0; x < lena.getWidth(); x++) {
//            for (int y = 0; y < lena.getHeight(); y++) {
//                int pixel = (int) matrix.getEntry(x, y);
//                bufferedImage.setRGB(x, y, pixel+color);
//            }
//        }
//        return bufferedImage;
//    }

    /**
     * Returns a JLabel containing this picture, for embedding in a JPanel,
     * JFrame or other GUI widget.
     *
     * @return the {@code JLabel}
     */
    public JLabel getJLabel() {
        if (lena == null) return null;         // no lena available
        ImageIcon icon = new ImageIcon(lena);
        return new JLabel(icon);
    }

    public JLabel getJLabel(BufferedImage image) {
        if (image == null) return null;         // no lena available
        ImageIcon icon = new ImageIcon(image);
        return new JLabel(icon);
    }

    /**
     * Sets the origin to be the upper left pixel. This is the default.
     */
    public void setOriginUpperLeft() {
        isOriginUpperLeft = true;
    }

    /**
     * Sets the origin to be the lower left pixel.
     */
    public void setOriginLowerLeft() {
        isOriginUpperLeft = false;
    }

    /**
     * Displays the picture in a window on the screen.
     */
    public void show() {

        // create the GUI for viewing the lena if needed
        if (frame == null) {
            frame = new JFrame();

            JMenuBar menuBar = new JMenuBar();

            menuBar.add(new Zadanie1(frame).buildMenu());
            menuBar.add(new Zadanie2(frame).buildMenu());
            menuBar.add(new Zadanie3(frame).buildMenu());
            menuBar.add(new Zadanie5(frame).buildMenu());

            frame.setJMenuBar(menuBar);

            frame.setContentPane(getJLabel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        }

        // draw
        frame.repaint();
    }

    /**
     * Returns the height of the picture.
     *
     * @return the height of the picture (in pixels)
     */
    public int height() {
        return height;
    }

    /**
     * Returns the width of the picture.
     *
     * @return the width of the picture (in pixels)
     */
    public int width() {
        return width;
    }

    private void validateRow(int row) {
        if (row < 0 || row >= height())
            throw new IndexOutOfBoundsException("row must be between 0 and " + (height() - 1) + ": " + row);
    }

    private void validateCol(int col) {
        if (col < 0 || col >= width())
            throw new IndexOutOfBoundsException("col must be between 0 and " + (width() - 1) + ": " + col);
    }

    /**
     * Returns the color of pixel ({@code col}, {@code row}).
     *
     * @param col the column index
     * @param row the row index
     * @return the color of pixel ({@code col}, {@code row})
     * @throws IndexOutOfBoundsException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     */
    public Color get(int col, int row) {
        validateCol(col);
        validateRow(row);
        if (isOriginUpperLeft) return new Color(lena.getRGB(col, row));
        else return new Color(lena.getRGB(col, height - row - 1));
    }

    /**
     * Sets the color of pixel ({@code col}, {@code row}) to given color.
     *
     * @param col   the column index
     * @param row   the row index
     * @param color the color
     * @throws IndexOutOfBoundsException unless both {@code 0 <= col < width} and {@code 0 <= row < height}
     * @throws NullPointerException      if {@code color} is {@code null}
     */
    public void set(int col, int row, Color color) {
        validateCol(col);
        validateRow(row);
        if (color == null) throw new NullPointerException("can't set Color to null");
        if (isOriginUpperLeft) lena.setRGB(col, row, color.getRGB());
        else lena.setRGB(col, height - row - 1, color.getRGB());
    }

    /**
     * Returns true if this picture is equal to the argument picture.
     *
     * @param other the other picture
     * @return {@code true} if this picture is the same dimension as {@code other}
     * and if all pixels have the same color; {@code false} otherwise
     */
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        PictureAdapter that = (PictureAdapter) other;
        if (this.width() != that.width()) return false;
        if (this.height() != that.height()) return false;
        for (int col = 0; col < width(); col++)
            for (int row = 0; row < height(); row++)
                if (!this.get(col, row).equals(that.get(col, row))) return false;
        return true;
    }

    /**
     * This operation is not supported because pictures are mutable.
     *
     * @return does not return a value
     * @throws UnsupportedOperationException if called
     */
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because pictures are mutable");
    }

    /**
     * Saves the picture to a file in a standard lena format.
     * The filetype must be .png or .jpg.
     *
     * @param name the name of the file
     */
    public void save(String name) {
        save(new File(name));
    }

    /**
     * Saves the picture to a file in a PNG or JPEG lena format.
     *
     * @param file the file
     */
    public void save(File file) {
//        filename = file.getName();
//        if (frame != null) frame.setTitle(filename);
//        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
//        suffix = suffix.toLowerCase();
//        if (suffix.equals("jpg") || suffix.equals("png")) {
//            try {
//                ImageIO.write(lena, suffix, file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Error: filename must end in .jpg or .png");
//        }
    }

    /**
     * Opens a save dialog box when the user selects "Save As" from the menu.
     */
    public void actionPerformed(ActionEvent e) {
        FileDialog chooser = new FileDialog(frame,
                "Use a .png or .jpg extension", FileDialog.SAVE);
        chooser.setVisible(true);
        if (chooser.getFile() != null) {
            save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }

//    public interface PenniesLib extends StdCallLibrary {
//        PenniesLib INSTANCE = (PenniesLib) Native.loadLibrary(
//                "PenniesLib", PenniesLib.class);
//
//        int a();
//    }

    /**
     * Unit tests this {@code PictureAdapter} data type.
     * Reads a picture specified by the command-line argument,
     * and shows it in a window on the screen.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
//        int value = PenniesLib.INSTANCE.a();
//        System.out.println(value);
        PictureAdapter picture = new PictureAdapter();
//        PictureAdapter picture = new PictureAdapter("/lake.jpg");
        System.out.printf("%d-by-%d\n", picture.width(), picture.height());
        picture.show();

    }
}
