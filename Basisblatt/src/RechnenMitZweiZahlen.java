import java.util.Scanner;

public class RechnenMitZweiZahlen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Gib die erste Zahl ein: ");
        int zahl1 = scanner.nextInt();
        System.out.print("Gib die andere Zahl ein: ");
        int zahl2 = scanner.nextInt();

        int summe = zahl1 + zahl2;
        int differenz = zahl1 - zahl2;
        int produkt = zahl1 * zahl2;
        int rest = zahl1 % zahl2;

        System.out.println("a) " + zahl1 + " + " + zahl2 + " = " + summe
                + " | " + zahl1 + " - " + zahl2 + " = " + differenz
                + " | " + zahl1 + " * " + zahl2 + " = " + produkt
                + " | Rest = " + rest);

        scanner.close();
    }
}


