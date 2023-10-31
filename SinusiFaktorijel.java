package ba.unsa.etf.rpr;

public class SinusiFaktorijel {

    public static double sinus(double n) {
        return Math.sin(n);
    }

    private static long faktorijel(int n) {
        if (n == 0) return 1;
        else return n * faktorijel(n - 1);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Unesite jedan broj kao argument.");
            return;
        }

        try {
            double n = Double.parseDouble(args[0]);

            double sinus = sinus(n);
            long faktorijel = faktorijel((int) n);

            System.out.println("Sinus(" + n + ") = " + sinus);
            System.out.println(n + "! = " + faktorijel);
        } catch (NumberFormatException e) {
            System.out.println("Pogrešan format broja.");
        }
    }
}


