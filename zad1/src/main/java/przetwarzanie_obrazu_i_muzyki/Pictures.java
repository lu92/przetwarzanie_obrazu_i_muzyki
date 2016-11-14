package przetwarzanie_obrazu_i_muzyki;

import org.opencv.core.Mat;

public enum Pictures {

    LENA("/LENA_512.jpg");

//    private static final String dir = "D:\\Images";
    private final String name;
    private final ImageReaderWriter readerWriter;

    Pictures(String name) {
        this.name = name;
        this.readerWriter = new ImageReaderWriter(name);
    }

    public String getName() {
        return name;
    }

    public Mat getMat() {
        return readerWriter.readMatrix();
    }

//    public String getPath() {
//        return dir + "\\" + name;
//    }

}
