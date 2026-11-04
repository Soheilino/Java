public class Main {
    public static void main(String[] args) {
        // Aufgabe 1
        Konzertkarte k1 = new Konzertkarte();
        Konzertkarte k2 = new Konzertkarte("Bilderbuch", "Linz");
        Konzertkarte k3 = new Konzertkarte("Seiler und Speer", "Wien", 3, 12, 179.00);
        Konzertkarte k4 = new Konzertkarte("Wanda", "Ried", -2, 0, 39.90, false);
        System.out.println(k1.kuenstler + " | " + k1.ort + " | " + k1.preis);
        System.out.println(k2.kuenstler + " | " + k2.ort + " | Reihe " + k2.reihe);
        System.out.println(k3.kuenstler + " | VIP: " + k3.vip);
        System.out.println(k4.kuenstler + " | Reihe " + k4.reihe + ", Platz " + k4.platz);
// Aufgabe 2
        Kunde c1 = new Kunde("Anna", "Huber", 19, true);
        Kunde c2 = new Kunde("Max", "Gruber");
        Kunde c3 = new Kunde("Lena", "Bauer", -5, false);
        System.out.println(c2.vorname + " | Alter " + c2.alter);
        System.out.println(c3.vorname + " | Alter " + c3.alter);
// Aufgabe 3
        Konzertkarte k5 = new Konzertkarte("Bilderbuch", "Linz", 2, 7, 80.00, c1);
        Konzertkarte k6 = new Konzertkarte("Wanda", "Ried", 5, 3, 200.00, c2);
        System.out.println(k5.besitzer.vorname + " | Preis " + k5.preis);
        System.out.println(k6.besitzer.vorname + " | Preis " + k6.preis + " | VIP: " + k6.vip);
        System.out.println("Besitzer k1: " + k1.besitzer);
    }
}
