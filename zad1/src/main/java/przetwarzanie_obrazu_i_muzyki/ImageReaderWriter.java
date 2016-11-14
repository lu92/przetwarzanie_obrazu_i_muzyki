package przetwarzanie_obrazu_i_muzyki;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class ImageReaderWriter {

    String toRead;
    String toSavePath;
    Mat toSave;

    public ImageReaderWriter(String path) {
        this.toRead = path;
    }

    public ImageReaderWriter() {
    }

    /**
     * Zwraca macierz obrazu z podanej cie¿ki.
     *
     * @param path
     *            cie¿ka do pliku.
     * @return Macierz obrazu.
     */
    public Mat readMatrix(String path) {
        Mat toReturn = Highgui.imread(path);
        return toReturn;
    }

    /**
     * Zwraca czarnobia³¹ macierz obrazu z podanej cie¿ki.
     *
     * @param path
     *            cie¿ka do pliku.
     * @return Macierz obrazu.
     */
    public Mat readBWMatrix(String path) {
        Mat sour = readMatrix(path);
        Mat tar = sour.clone();
        Imgproc.cvtColor(sour, tar, Imgproc.COLOR_BGR2GRAY);
        return tar;
    }

    /**
     * Zwraca macierz obrazu. Musi zostaæ wczeniej podana cie¿ka za pomoc¹
     * konstruktora b¹d metody <b>setToRead(String)</b>;
     *
     * @return Macierz odczytanego obrazu.
     */
    public Mat readMatrix() {
        if (toRead != null) {
            return readMatrix(toRead);
        }
        return null;
    }

    public String getToRead() {
        return toRead;
    }

    public void setToRead(String toRead) {
        this.toRead = toRead;
    }

    public String getToSavePath() {
        return toSavePath;
    }

    public void setToSave(String toSavePath, Mat toSave) {
        this.toSave = toSave;
        this.toSavePath = toSavePath;
    }

    public Mat getToSave() {
        return toSave;
    }

    public void setToSave(Mat toSave) {
        this.toSave = toSave;
    }

    /**
     * Zapisuje macierz <b>mat</b> do cie¿ki <b>path</b>.
     *
     * @param mat
     *            Macierz do zapisania.
     * @param path
     *            cie¿ka do zapisu.
     * @return TRUE dla poprawnego zapisu, FALSE w przeciwnym razie.
     */
    public boolean saveMat(Mat mat, String path) {
        return Highgui.imwrite(path, mat);
    }

}