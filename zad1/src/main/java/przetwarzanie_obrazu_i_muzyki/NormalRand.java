package przetwarzanie_obrazu_i_muzyki;

import java.util.Random;

public class NormalRand {
    private static Random random = new Random();

    /**
     *
     * @param m
     *            - rednia
     * @param s
     *            - Odchylenie standardowe
     * @return Losowa wartoæ rozk³adu normalnego o podanych parametrach
     *         <b>m</b> i <b>s</b>.
     */
    public static double next(double m, double s) {
        double wynik;
        wynik = random.nextGaussian();
        wynik += m;
        wynik *= s;
        return wynik;
    }
}
