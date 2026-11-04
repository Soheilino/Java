import java.util.ArrayList;
import java.util.Scanner;

public class EinkaufApp {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<Artikel> einkauf= alleEinlesen(sc,3);
        listeAusgeben(einkauf);
        System.out.println("Gesamtbetrag: "+gesamtBetrag(einkauf)+" Euro");
    }
    public static Artikel artikelEinlesen(Scanner sc) {
        System.out.print("Geben Sie bitte den Name des Produkts ein: ");
        String name = sc.nextLine();
        System.out.print("Geben Sie bitte die Preis des Produkts ein: ");
        double preis = Double.parseDouble(sc.nextLine());
        System.out.print("Geben Sie bitte die Menge des Produkts ein: ");
        int menge = Integer.parseInt(sc.nextLine());
        Artikel e = new Artikel(name, preis, menge);
        return e;
    }

    public static ArrayList<Artikel> alleEinlesen(Scanner sc, int anzahl) {
        ArrayList<Artikel> einkauf = new ArrayList<>();
        for (int i = 0; i < anzahl; i++) {
            System.out.println("--- Produkt " + (i + 1) + " ---");
            Artikel e = artikelEinlesen(sc);
            einkauf.add(e);
        }
        return einkauf;
    }

    public static void listeAusgeben(ArrayList<Artikel> einkauf) {
        for (int i = 0; i < einkauf.size(); i++) {
            Artikel e = einkauf.get(i);
            System.out.println(i + 1 + ". " + e.name + " - " + e.preis + " Euro - Menge: " + e.menge + " - Gesamt: " + e.gesamtPreis() + " Euro");
        }
    }
    public static double gesamtBetrag(ArrayList<Artikel> einkauf){
        double gesamt=0;
        for (int i=0;i< einkauf.size();i++){
            Artikel e=einkauf.get(i);
            gesamt+=e.gesamtPreis();
        }
        return gesamt;
    }
}
