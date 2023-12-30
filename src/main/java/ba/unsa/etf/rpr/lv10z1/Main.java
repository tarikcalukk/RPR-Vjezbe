package ba.unsa.etf.rpr.lv10z1;

import java.util.Scanner;

public class Main {
    public static String ispisiGradove() {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        StringBuilder rezultatBuilder = new StringBuilder();

        for (Grad grad : dao.gradovi()) {
            rezultatBuilder.append(grad.getNaziv())
                    .append(" (")
                    .append(grad.getDrzava().getNaziv())
                    .append(") - ")
                    .append(grad.getBrojStanovnika())
                    .append("\n");
        }
        return rezultatBuilder.toString();
    }

    public static void glavniGrad() {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Unesite naziv drzave: ");
        String naziv = ulaz.nextLine();
        Grad grad = dao.glavniGrad(naziv);
        if (grad == null)
            System.out.println("Nepostojeca drzava");
        else
            System.out.println("Glavni grad drzave " + naziv +" je " + grad.getNaziv());
    }

    public static void main(String[] args) {
        System.out.println("Gradovi su:\n" + ispisiGradove());
        glavniGrad();
    }
}
