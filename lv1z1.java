import java.util.Scanner;

public class  lv1z1{
    public static void main(String[] args) {
        
        Scanner unos = new Scanner(System.in);

        System.out.print("Unesite prvi cijeli broj: ");
        int prviBroj = unos.nextInt();

        
        System.out.print("Unesite drugi cijeli broj: ");
        int drugiBroj = unos.nextInt();

        
        System.out.println("Prvi broj: " + prviBroj);
        System.out.println("Drugi broj: " + drugiBroj);
    }
}
