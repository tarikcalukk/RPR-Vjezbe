package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Double> unesiBrojeve() {
        List<Double> brojevi = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Unesite broj ili 'stop' za zaustavljanje unosa: ");
            String unos = scanner.nextLine();

            if (unos.equalsIgnoreCase("stop"))
                break;

            try {
                double broj = Double.parseDouble(unos);
                brojevi.add(broj);
            } catch (NumberFormatException e) {
                System.out.println("Uneseni podatak nije broj. Pokušajte ponovno.");
            }
        }
        return brojevi;
    }


    public static double minimum(List<Double> brojevi) {
        if (brojevi.isEmpty())
            throw new IllegalArgumentException("Lista brojeva je prazna.");

        double min = brojevi.get(0);

        for (double broj : brojevi) {
            if (broj < min) {
                min = broj;
            }
        }
        return min;
    }


    public static double maksimum(List<Double> brojevi) {
        if (brojevi.isEmpty())
            throw new IllegalArgumentException("Lista brojeva je prazna.");

        double max = brojevi.get(0);

        for (double broj : brojevi)
            if (broj > max)
                max = broj;

        return max;
    }


    public static double srednjaVrijednost(List<Double> brojevi) {
        if (brojevi.isEmpty())
            throw new IllegalArgumentException("Lista brojeva je prazna.");

        double suma = 0;

        for (double broj : brojevi)
            suma += broj;

        return suma / brojevi.size();
    }


    public static double standardnaDevijacija(List<Double> brojevi) {
        if (brojevi.isEmpty())
            throw new IllegalArgumentException("Lista brojeva je prazna.");

        double mean = srednjaVrijednost(brojevi);
        double sumSquare = 0;

        for (double broj : brojevi)
            sumSquare += Math.pow(broj - mean, 2);

        return Math.sqrt(sumSquare / brojevi.size());
    }


    public static void main(String[] args) {
        List<Double> brojevi = unesiBrojeve();

        if (brojevi.isEmpty()) {
            System.out.println("Nema unesenih brojeva.");
        } else {
            double min = minimum(brojevi);
            double max = maksimum(brojevi);
            double mean = srednjaVrijednost(brojevi);
            double standardDeviation = standardnaDevijacija(brojevi);

            System.out.println("Minimum: " + min);
            System.out.println("Maksimum: " + max);
            System.out.println("Srednja vrijednost: " + mean);
            System.out.println("Standardna devijacija: " + standardDeviation);
        }
    }
}
