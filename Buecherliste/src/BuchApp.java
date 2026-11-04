import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BuchApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Buch> buecher = alleEinlesen(sc, 3);
        listeAusgeben(buecher);
        inCsvSchreiben(buecher, "buecher.csv");
    }

    public static Buch buchEinlesen(Scanner sc) {
        System.out.print("Geben Sie bitte den Titel ein: ");
        String titel = sc.nextLine();
        System.out.print("Geben Sie bitte den Name des Autors ein: ");
        String autor = sc.nextLine();
        System.out.print("Geben Sie bitte das Erscheinungsjahr ein: ");
        int jahr = Integer.parseInt(sc.nextLine());
        return new Buch(titel, autor, jahr);
    }

    public static ArrayList<Buch> alleEinlesen(Scanner sc, int anzahl) {
        ArrayList<Buch> buecher = new ArrayList<>();
        for (int i = 0; i < anzahl; i++) {
            System.out.println("--- Buch " + (i + 1) + " ---");
            Buch b = buchEinlesen(sc);
            buecher.add(b);
        }
        return buecher;
    }

    public static void listeAusgeben(ArrayList<Buch> buecher) {
        for (int i = 0; i < buecher.size(); i++) {
            Buch b = buecher.get(i);
            System.out.println(i + 1 + ". " + b.titel + " von " + b.autor + " (" + b.jahr + ")");
        }
    }

    public static void inCsvSchreiben(ArrayList<Buch> buecher, String dateiname) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dateiname))) {
            writer.write("Titel;Autor;Jahr");
            writer.newLine();
            for (int i = 0; i < buecher.size(); i++) {
                Buch b = buecher.get(i);
                writer.write(b.toCsv());
                writer.newLine();
            }
            System.out.println(buecher.size() + " Bücher in " + dateiname + " gespeichert.");
        } catch (IOException o) {
            System.out.println("Fehler: " + o.getMessage());
        }
    }
}
