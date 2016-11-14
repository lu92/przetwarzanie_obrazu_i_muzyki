package przetwarzanie_obrazu_i_muzyki;


import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;
import java.util.Random;

public class Algorithms {

    public void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
    }

    public double[] splitIntoRgb(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        double[] rgb = {red, green, blue};
        return rgb;
    }

    public int toRGB(double red, double green, double blue) {
        if (red > 255)
            red = 255;
        if (red < 0)
            red = 0;

        if (green > 255)
            green = 255;
        if (green < 0)
            green = 0;

        if (blue > 255)
            blue = 255;
        if (blue < 0)
            blue = 0;

        return new Color((int) red, (int) green, (int) blue).getRGB();
    }

    public BufferedImage transformToProperMatrix(RealMatrix matrix, RGBType type) {
        BufferedImage bufferedImage = new BufferedImage(matrix.getColumnDimension(), matrix.getRowDimension(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < matrix.getColumnDimension(); i++) {
            for (int j = 0; j < matrix.getRowDimension(); j++) {
                int pixel = (int) matrix.getEntry(i, j);
                switch (type) {
                    case R:
                        int red = (pixel >> 16) & 0xff;
                        bufferedImage.setRGB(i, j, new Color(red, 0, 0).getRGB());
                        break;

                    case G:
                        int green = (pixel >> 8) & 0xff;
                        bufferedImage.setRGB(i, j, new Color(0, green, 0).getRGB());
                        break;

                    case B:
                        int blue = (pixel) & 0xff;
                        bufferedImage.setRGB(i, j, new Color(0, 0, blue).getRGB());
                        break;
                }
            }
        }
        return bufferedImage;
    }

    public RealMatrix generateRGBMatrix(BufferedImage image) {
        RealMatrix decodedRGBMatrix = MatrixUtils.createRealMatrix(image.getWidth(), image.getHeight());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                decodedRGBMatrix.addToEntry(i, j, image.getRGB(i, j));
            }
        }
        return decodedRGBMatrix;
    }

//    public Mat generateMatMatrix(BufferedImage image) {
//        Mat decodedRGBMatrix = Mat.zeros(image.getWidth(), image.getHeight(), CvType.CV_32FC1);
//        for (int i = 0; i < image.getWidth(); i++) {
//            for (int j = 0; j < image.getHeight(); j++) {
//                decodedRGBMatrix.put(i, j, image.getRGB(i, j));
//            }
//        }
//        return decodedRGBMatrix;
//    }

    public Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }

    public BufferedImage skalaSzarosci(RealMatrix matrix) {
        BufferedImage bufferedImage = new BufferedImage(matrix.getColumnDimension(), matrix.getRowDimension(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < matrix.getColumnDimension(); i++) {
            for (int j = 0; j < matrix.getRowDimension(); j++) {
                int pixel = (int) matrix.getEntry(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                int rgbGrey = (red + green + blue) / 3;
                bufferedImage.setRGB(i, j, new Color(rgbGrey, rgbGrey, rgbGrey).getRGB());
            }
        }
        return bufferedImage;
    }

    public BufferedImage negatyw(RealMatrix matrix) {
        BufferedImage bufferedImage = new BufferedImage(matrix.getColumnDimension(), matrix.getRowDimension(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < matrix.getColumnDimension(); i++) {
            for (int j = 0; j < matrix.getRowDimension(); j++) {
                int pixel = (int) matrix.getEntry(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                bufferedImage.setRGB(i, j, new Color(255 - red, 255 - green, 255 - blue).getRGB());
            }
        }
        return bufferedImage;
    }

    public BufferedImage sepia(RealMatrix matrix, int W) {
        if (W < 20 || W > 40) {
            throw new IllegalArgumentException("range of W should be between 20 to 40");
        }
        BufferedImage skalaSzarosci = skalaSzarosci(matrix);
        RealMatrix skalaSzarosciDecoded = generateRGBMatrix(skalaSzarosci);
        BufferedImage sepia = new BufferedImage(matrix.getColumnDimension(), matrix.getRowDimension(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < skalaSzarosciDecoded.getColumnDimension(); i++) {
            for (int j = 0; j < skalaSzarosciDecoded.getRowDimension(); j++) {
//                int pixel = (int) matrix.getEntry(i, j);
                int pixel = (int) skalaSzarosciDecoded.getEntry(i, j);
                int red = (pixel >> 16) & 0xff - 1;
                int green = (pixel >> 8) & 0xff - 1;
                int blue = (pixel) & 0xff - 1;
                System.out.println("[" + i + "|" + j + "}" + "R=" + red + ", G=" + green + ",B=" + blue);
//                sepia.setRGB(i, j, new Color((int)(red * 0.3), (int)(green*0.59), (int)(blue*0.11)).getRGB());
                sepia.setRGB(i, j, new Color(red + 2 * W - 2, green + W - 2, blue).getRGB());
            }
        }
        return sepia;
    }


    public BufferedImage obrot(RealMatrix matrix, int stopien) {
        BufferedImage bufferedImage = new BufferedImage(matrix.getColumnDimension(), matrix.getRowDimension(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < matrix.getColumnDimension(); x++) {
            for (int y = 0; y < matrix.getRowDimension(); y++) {
                int pixel = (int) matrix.getEntry(x, y);
                int x2 = (int) (x * Math.cos(stopien) - y * Math.sin(stopien));
                int y2 = (int) (x * Math.sin(stopien) + y * Math.cos(stopien));
                System.out.println("x'=" + x2 + ", y'=" + y2);
                bufferedImage.setRGB(x2, y2, pixel);
            }
        }
        return bufferedImage;
    }

    public BufferedImage przesuniecie(RealMatrix matrix, int pixelsToMove) {
        BufferedImage bufferedImage = new BufferedImage(matrix.getColumnDimension(), matrix.getRowDimension(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < matrix.getColumnDimension(); x++) {
            for (int y = 0; y < matrix.getRowDimension(); y++) {
                int pixel = (int) matrix.getEntry((x + pixelsToMove) % matrix.getRowDimension(), y);
                bufferedImage.setRGB(x, y, pixel);
            }
        }
        return bufferedImage;
    }

    public BufferedImage zwiekszKolorObrazu(RealMatrix matrix, int color) {
        BufferedImage bufferedImage = new BufferedImage(matrix.getRowDimension(), matrix.getColumnDimension(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < matrix.getRowDimension(); x++) {
            for (int y = 0; y < matrix.getColumnDimension(); y++) {
                int pixel = (int) matrix.getEntry(x, y);
                bufferedImage.setRGB(x, y, pixel + color);
            }
        }
        return bufferedImage;
    }

    public BufferedImage toBufferedImage(RealMatrix matrix) {
        BufferedImage bufferedImage = new BufferedImage(matrix.getRowDimension(), matrix.getColumnDimension(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                int pixel = (int) matrix.getEntry(i, j);
                bufferedImage.setRGB(i, j, pixel);
            }
        }
        return bufferedImage;
    }

    public BufferedImage dodajObrazy(RealMatrix matrix1, RealMatrix matrix2, double W1) {
        double maks = 256;
        RealMatrix image3 = matrix1.copy();
        for (int i = 0; i < image3.getRowDimension(); i++) {
            for (int j = 0; j < image3.getColumnDimension(); j++) {
                double[] data1 = splitIntoRgb((int) matrix1.getEntry(i, j));
                double[] data2 = splitIntoRgb((int) matrix2.getEntry(i, j));
                double[] data3 = splitIntoRgb((int) image3.getEntry(i, j));
                for (int k = 0; k < 3; k++) {
                    data1[k] = W1 * data1[k];
                    data2[k] = (1 - W1) * data2[k];
                    if (data1[k] + data2[k] > maks)
                        data3[k] = maks;
                    else
                        data3[k] = (data1[k] + data2[k]) % 256;
                }
                image3.setEntry(i, j, toRGB(data3[0], data3[1], data3[2]));
            }
        }
        return toBufferedImage(image3);
    }

    public BufferedImage dodajObrazySaturacja(RealMatrix image1, RealMatrix image2, double W1) {
        double maks = 256;
        RealMatrix image3 = image1.copy();
        for (int i = 0; i < image3.getRowDimension(); i++) {
            for (int j = 0; j < image3.getColumnDimension(); j++) {
                double[] data1 = splitIntoRgb((int) image1.getEntry(i, j));
                double[] data2 = splitIntoRgb((int) image2.getEntry(i, j));
                double[] data3 = splitIntoRgb((int) image3.getEntry(i, j));
                for (int k = 0; k < 3; k++) {
                    data1[k] = data1[k];
                    data2[k] = (1 - W1) * data2[k];
                    if (data1[k] + data2[k] > maks)
                        data3[k] = maks;
                    else
                        data3[k] = (data1[k] + data2[k]) % 256;

                }
                image3.setEntry(i, j, toRGB(data3[0], data3[1], data3[2]));
            }
        }
        return toBufferedImage(image3);
    }

    public BufferedImage odejmijObrazy(RealMatrix image1, RealMatrix image2, double W1) {
        RealMatrix image3 = image1.copy();
        for (int i = 0; i < image3.getRowDimension(); i++) {
            for (int j = 0; j < image3.getColumnDimension(); j++) {
                double[] data1 = splitIntoRgb((int) image1.getEntry(i, j));
                double[] data2 = splitIntoRgb((int) image2.getEntry(i, j));
                double[] data3 = splitIntoRgb((int) image3.getEntry(i, j));
                for (int k = 0; k < 3; k++) {
                    data1[k] = W1 * data1[k];
                    data2[k] = (1 - W1) * data2[k];
                    if (data1[k] - data2[k] < 0)
                        data3[k] = 0;
                    else
                        data3[k] = (data1[k] - data2[k] + 256) % 256;
                }
                image3.setEntry(i, j, toRGB(data3[0], data3[1], data3[2]));
            }
        }
        return toBufferedImage(image3);
    }

    public BufferedImage przemnozObrazy(RealMatrix image1, RealMatrix image2, double W1) {
        double maks = 256;
        RealMatrix image3 = image1.copy();
        for (int i = 0; i < image3.getRowDimension(); i++) {
            for (int j = 0; j < image3.getColumnDimension(); j++) {
                double[] data1 = splitIntoRgb((int) image1.getEntry(i, j));
                double[] data2 = splitIntoRgb((int) image2.getEntry(i, j));
                double[] data3 = splitIntoRgb((int) image3.getEntry(i, j));
                for (int k = 0; k < 3; k++) {
                    data1[k] = W1 * data1[k];
                    data2[k] = (1 - W1) * data2[k];
                    if (data1[k] * (data2[k] / 255) > maks)
                        data3[k] = maks;
                    else
                        data3[k] = data1[k] * (data2[k] / 255);
                }

                image3.setEntry(i, j, toRGB(data3[0], data3[1], data3[2]));
            }

        }
        return toBufferedImage(image3);
    }

    public BufferedImage podzielObrazy(RealMatrix image1, RealMatrix image2, double W1) {
        RealMatrix image3 = image1.copy();
        for (int i = 0; i < image3.getRowDimension(); i++) {
            for (int j = 0; j < image3.getColumnDimension(); j++) {
                double[] data1 = splitIntoRgb((int) image1.getEntry(i, j));
                double[] data2 = splitIntoRgb((int) image2.getEntry(i, j));
                double[] data3 = splitIntoRgb((int) image3.getEntry(i, j));
                for (int k = 0; k < 3; k++) {
                    data1[k] = W1 * data1[k];
                    data2[k] = (1 - W1) * data2[k];
                    if (data2[k] == 0)
                        data3[k] = data1[k] / 0.00000001;
                    else
                        data3[k] = data1[k] / data2[k];
                }

                image3.setEntry(i, j, toRGB(data3[0], data3[1], data3[2]));
            }

        }
        return toBufferedImage(image3);
    }

    public BufferedImage normalizuj(BufferedImage bufferedImage) {
        RealMatrix realMatrix = generateRGBMatrix(bufferedImage);
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int j = 0; j < realMatrix.getColumnDimension(); j++) {
                double point = realMatrix.getEntry(i, j);
                if (point < 0) {
                    realMatrix.setEntry(i, j, 0);
                } else if (point > 255) {
                    realMatrix.setEntry(i, j, 255);
                } else {
                    realMatrix.setEntry(i, j, Math.floor(point));
                }
            }
        }
        return toBufferedImage(realMatrix);
    }


    public BufferedImage zaciemnienieRozkładJednostajny(RealMatrix source, int level, double probability, NoiseType noiseType) {
        RealMatrix target = null;
        Random r = new Random();
        switch (noiseType) {
            case BLACK_AND_WHITE:
                target = generateRGBMatrix(skalaSzarosci(source));
                break;

            case COLOR:
                target = source.copy();
                break;
        }

        for (int row = 0; row < source.getColumnDimension(); ++row) {
            for (int col = 0; col < source.getRowDimension(); ++col) {
                if (r.nextDouble() < probability) {
                    int a = r.nextInt(2 * level);
                    a -= level;
                    if (a == 0)
                        a = level;
                    double[] rgb = splitIntoRgb((int) target.getEntry(row, col));
                    for (int k = 0; k < 3; k++) {
                        rgb[k] = rgb[k] + a;
                    }
                    int newRgb = toRGB(rgb[0], rgb[1], rgb[2]);
                    target.setEntry(row, col, newRgb);
                }
            }
        }
        return toBufferedImage(target);
    }

    public BufferedImage zaciemnienieRozkladNormalny(RealMatrix source, int mean, int standardDeviation, double probability, NoiseType noiseType) {
        RealMatrix target = null;
        Random r = new Random();
        switch (noiseType) {
            case BLACK_AND_WHITE:
                target = generateRGBMatrix(skalaSzarosci(source));
                break;

            case COLOR:
                target = source.copy();
                break;
        }

        for (int row = 0; row < source.getColumnDimension(); ++row) {
            for (int col = 0; col < source.getRowDimension(); ++col) {
                if (r.nextDouble() < probability) {
                    int a = (int) NormalRand.next(mean, standardDeviation);
                    double[] rgb = splitIntoRgb((int) target.getEntry(row, col));
                    for (int k = 0; k < 3; k++) {
                        rgb[k] = rgb[k] + a;
                    }
                    int newRgb = toRGB(rgb[0], rgb[1], rgb[2]);
                    target.setEntry(row, col, newRgb);
                }
            }
        }
        return toBufferedImage(target);
    }

    public BufferedImage zaciemnienieSolAndPieprz(RealMatrix source, double probability, NoiseType noiseType) {
        RealMatrix target = null;
        Random r = new Random();
        switch (noiseType) {
            case BLACK_AND_WHITE:
                target = generateRGBMatrix(skalaSzarosci(source));
                break;

            case COLOR:
                target = source.copy();
                break;
        }

        for (int row = 0; row < source.getColumnDimension(); ++row) {
            for (int col = 0; col < source.getRowDimension(); ++col) {
                if (r.nextDouble() < probability) {
                    int a = r.nextInt(2) * 255;
                    double[] rgb = splitIntoRgb((int) target.getEntry(row, col));
                    for (int k = 0; k < 3; k++) {
                        rgb[k] = rgb[k] + a;
                    }
                    int newRgb = toRGB(rgb[0], rgb[1], rgb[2]);
                    target.setEntry(row, col, newRgb);
                }
            }
        }
        return toBufferedImage(target);
    }

    public BufferedImage avgFilter(RealMatrix source) {
        double[] srednia = new double[3];
        RealMatrix target = source.copy();
        for (int i = 1; i < source.getRowDimension() - 1; ++i) {
            for (int j = 1; j < source.getColumnDimension() - 1; ++j) {

                for (int n = 0; n < 3; n++) {
                    srednia[n] = ( splitIntoRgb((int) target.getEntry(i - 1, j - 1))[n] + splitIntoRgb((int) target.getEntry(i - 1, j))[n]
                            + splitIntoRgb((int) target.getEntry(i - 1, j + 1))[n] + splitIntoRgb((int) target.getEntry(i, j - 1))[n]
                            + splitIntoRgb((int) target.getEntry(i, j))[n] + splitIntoRgb((int) target.getEntry(i, j + 1))[n]
                            + splitIntoRgb((int) target.getEntry(i + 1, j - 1))[n] + splitIntoRgb((int) target.getEntry(i + 1, j))[n]
                            + splitIntoRgb((int) target.getEntry(i + 1, j + 1))[n] ) / 9;
                }
                int newColor = toRGB(srednia[0], srednia[1], srednia[2]);
                target.setEntry(i, j, newColor);
            }
        }
        return toBufferedImage(target);
    }

    public BufferedImage medFilter(RealMatrix source) {
        RealMatrix target = source.copy();
        double[] srednia = new double[9];
        for (int i = 1; i < source.getColumnDimension() - 1; ++i) {
            for (int j = 1; j < source.getRowDimension() - 1; ++j) {

                double[] out = splitIntoRgb((int) source.getEntry(i, j));
                for (int nr = 0; nr < 3; ++nr) {
                    for (int k = 0; k < 9; ++k) {
                        srednia[k] = splitIntoRgb((int)source.getEntry(i - 1 + k / 3, j - 1 + k % 3))[nr];
                    }
                    Arrays.sort(srednia);
                    out[nr] = srednia[4];
                }
                int newColor = toRGB(out[0], out[1], out[2]);
                target.setEntry(i, j, newColor);
            }
        }
        return toBufferedImage(target);
    }
}
