package ba.unsa.etf.rpr;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite n: ");
        int n = scanner.nextInt();
        for(int i = 1; i < n; i++)
            if(DjeljiviSaSumomCifara(i))
                System.out.println(i);
    }

    public static boolean DjeljiviSaSumomCifara(int n) {
        int sumaCifara = SumaCifara(n);
        return n % sumaCifara == 0;
    }

    public static int SumaCifara(int cifra) {
        int suma = 0;
        while(cifra > 0) {
            suma += cifra % 10;
            cifra /= 10;
        }
        return suma;
    }
}
