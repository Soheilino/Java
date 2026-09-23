import java.util.Scanner;

public class TemperaturKlassifizieren {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie eine Temperatur ein: ");
        double temperatur = scanner.nextDouble();
        String meldung;

        if (temperatur < 0) {
            meldung = "Gefrierpunkt unterschritten";
        } else if (temperatur <= 25) {
            meldung = "Normalbereich";
        } else {
            meldung = "Warnung: zu warm";
        }

        System.out.println("b) " + temperatur + " -> " + meldung);

        scanner.close();
    }
}
