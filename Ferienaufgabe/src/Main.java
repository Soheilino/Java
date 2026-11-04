import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String[] KATEGORIEN = {"Einkommen", "Essen", "Freizeit", "Handy", "Mobilitaet", "Sonstiges"};

    public static void main(String[] args) {
        System.out.println("=== HAUSHALTSBUCH ===");
        ArrayList<Buchung> b = lade("haushalt.csv");
        Scanner sc = new Scanner(System.in);
        int eingabe;
        do {
            System.out.println("1 Tabelle 2 Neue Buchung 3 Filter 4 Bericht 5 Pivot 0 Ende");
            System.out.print("Auswahl: ");
            eingabe = sc.nextInt();
            switch (eingabe) {
                case 1:
                    zeigeTabelle(b, null);
                    break;
                case 2:
                    System.out.print("Neue Buchung erstellen? (j/n): ");
                    String antwort = sc.next();
                    if (antwort.equalsIgnoreCase("n")) {
                        continue;
                    }
                    if (antwort.equalsIgnoreCase("j")) {
                        System.out.print("Datum: ");
                        String datum = sc.next();
                        sc.nextLine();
                        System.out.print("Beschreibung: ");
                        String beschreibung = sc.nextLine();
                        sc.nextLine();
                        System.out.print("Kategorie: ");
                        String kategorie = sc.nextLine();

                        System.out.print("Betrag: ");
                        double betrag = sc.nextDouble();
                        System.out.print("Einnahme? (j/n): ");
                        boolean einnahme = sc.next().equalsIgnoreCase("j");
                        Buchung buchung = new Buchung(datum, beschreibung, kategorie, betrag, einnahme);
                        b.add(buchung);

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("haushalt.csv", true))) {
                            writer.write(String.join(";", datum, beschreibung, kategorie, String.valueOf(betrag), einnahme ? "E" : "A"));
                            writer.newLine();
                        } catch (IOException e) {
                            System.out.println("Fehler! " + e.getMessage());
                        }
                    }


                    break;
                case 3:
                    System.out.print("Welche Kategorie?: ");
                    String kategorie = sc.next();
                    zeigeTabelle(b, kategorie);
                    break;
                case 4:
                    schreibeBericht(b, "bericht.csv");
                    break;
                case 5:
                    pivot(b,"pivot.csv");
                    break;
                case 0:
                    System.out.println("Programm beenden");
                    break;
                default:
                    System.out.println("Ungültige Auswahl: " + eingabe);
                    break;
            }
        } while (eingabe != 0);
    }


    public static ArrayList<Buchung> lade(String datei) {
        ArrayList<Buchung> arrayList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(datei))) {
            String zeile;
            reader.readLine(); // Header überspringen

            while ((zeile = reader.readLine()) != null) {
                String[] daten = zeile.split(";");

                try {
                    switch (daten.length) {
                        case 2:
                            Buchung buchung = new Buchung(daten[0], Double.parseDouble(daten[1]));
                            arrayList.add(buchung);
                            break;

                        case 4:
                            Buchung buchungv = new Buchung(daten[0], daten[1], daten[2], Double.parseDouble(daten[3]));
                            arrayList.add(buchungv);
                            break;

                        case 5:
                            Buchung buchungn = new Buchung(daten[0], daten[1], daten[2], Double.parseDouble(daten[3]), daten[4].equals("E"));
                            arrayList.add(buchungn);
                            break;

                        default:
                            System.out.println("Übersprungen (Spaltenanzahl): " + zeile);
                            break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Übersprungen (Betrag): " + zeile);
                }
            }

        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

        return arrayList;
    }

    public static double summeEinnahmen(ArrayList<Buchung> b) {
        double summe = 0;
        for (Buchung buchung : b) {
            if (buchung.einnahme) {
                summe += buchung.betrag;
            }
        }
        return summe;
    }

    public static double summeAusgaben(ArrayList<Buchung> b) {
        double summe = 0;
        for (Buchung buchung : b) {
            if (!buchung.einnahme) {
                summe += buchung.betrag;
            }
        }
        return summe;
    }

    public static double saldo(ArrayList<Buchung> b) {
        double saldo = summeEinnahmen(b) - summeAusgaben(b);
        return saldo;
    }

    public static double summeKategorie(ArrayList<Buchung> b, String kategorie) {
        double sum = 0;
        for (Buchung buchung : b) {
            if (kategorie.equals(buchung.kategorie)) {
                sum += buchung.betrag;
            }
        }
        return sum;
    }

    public static Buchung groessteAusgabe(ArrayList<Buchung> b) {
        double maxBetrag = 0;
        Buchung groesste = null;
        for (Buchung buchung : b) {
            if (!buchung.einnahme) {
                if (buchung.betrag > maxBetrag) {
                    maxBetrag = buchung.betrag;
                    groesste = buchung;
                }
            }
        }
        return groesste;
    }

    static void zeigeTabelle(ArrayList<Buchung> b, String filter) {

        System.out.printf("%-5s | %-10s | %-30s | %-12s | %10s%n", "Nr", "Datum", "Beschreibung", "Kategorie", "Betrag");

        System.out.println("------+------------+--------------------------------+--------------+-----------");

        int nr = 1;

        for (Buchung buchung : b) {
            if (filter == null || filter.equals(buchung.kategorie)) {

                double betrag = buchung.einnahme ? buchung.betrag : -buchung.betrag;

                System.out.printf("%-5d | %-10s | %-30s | %-12s | %10.2f%n", nr, buchung.datum, buchung.beschreibung, buchung.kategorie, betrag);

                nr++;
            }
        }

        System.out.println("------+------------+--------------------------------+--------------+-----------");

        if (filter == null) {

            double einnahmen = summeEinnahmen(b);
            double ausgaben = summeAusgaben(b);
            double saldo = saldo(b);

            System.out.printf("%-20s | %10.2f%n", "Einnahmen", einnahmen);
            System.out.printf("%-20s | %10.2f%n", "Ausgaben", -ausgaben);
            System.out.printf("%-20s | %10.2f%n", "SALDO", saldo);

            if (saldo < 0) {
                System.out.println("WARNUNG: Saldo ist negativ!");
            }

            Buchung groesste = groessteAusgabe(b);

            if (groesste != null) {
                System.out.printf("Größte Ausgabe: %s (%.2f)%n", groesste.beschreibung, groesste.betrag);
            }

        } else {
            System.out.printf("Summe %s: %.2f%n", filter, summeKategorie(b, filter));
        }
    }

    public static void schreibeBericht(ArrayList<Buchung> b, String datei) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(datei))) {
            writer.write("kategorie;summe");
            writer.newLine();
            for (int i = 0; i < KATEGORIEN.length; i++) {
                double summe = summeKategorie(b, KATEGORIEN[i]);
                if (summe > 0) {
                    writer.write(String.join(";", KATEGORIEN[i], String.format("%.2f", summe)));
                    writer.newLine();
                }
            }
            writer.write(String.join(";", "SALDO", String.format("%.2f", saldo(b))));
            writer.newLine();
        } catch (IOException o) {
            System.out.println("Fehler! " + o.getMessage());
        }
    }

    static int kategorieIndex(String k) {
        switch (k) {
            case "Einkommen":
                return 0;
            case "Essen":
                return 1;
            case "Freizeit":
                return 2;
            case "Handy":
                return 3;
            case "Mobilitaet":
                return 4;
            case "Sonstiges":
                return 5;
            default:
                return 5;
        }
    }

    public static void pivot(ArrayList<Buchung> b, String datei) {

        double[][] p = new double[KATEGORIEN.length][12];
        boolean[] monate = new boolean[12];

        for (Buchung buchung : b) {

            if (!buchung.einnahme && !buchung.datum.equals("unbekannt")) {

                String[] teile = buchung.datum.split("-");
                int monat = Integer.parseInt(teile[1]);

                int zeile = kategorieIndex(buchung.kategorie);

                p[zeile][monat - 1] += buchung.betrag;
                monate[monat - 1] = true;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(datei))) {

            StringBuilder kopf = new StringBuilder("Kategorie");

            for (int i = 0; i < 12; i++) {
                if (monate[i]) {
                    kopf.append(";").append(String.format("%02d", i + 1));
                }
            }

            kopf.append(";Gesamt");

            System.out.println(kopf);
            writer.write(kopf.toString());
            writer.newLine();

            for (int i = 1; i < KATEGORIEN.length; i++) {

                double gesamt = 0;

                for (int j = 0; j < 12; j++) {
                    gesamt += p[i][j];
                }

                if (gesamt > 0) {

                    StringBuilder zeile = new StringBuilder(KATEGORIEN[i]);

                    for (int j = 0; j < 12; j++) {
                        if (monate[j]) {
                            zeile.append(";").append(String.format("%.2f", p[i][j]));
                        }
                    }

                    zeile.append(";").append(String.format("%.2f", gesamt));

                    System.out.println(zeile);
                    writer.write(zeile.toString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
}