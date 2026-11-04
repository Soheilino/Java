public class Buchung {
    public String datum;
    public String beschreibung;
    public String kategorie;
    public double betrag;
    public boolean einnahme;

    public Buchung(String beschreibung, double betrag) {
        this("unbekannt", beschreibung, "Sonstiges", betrag, false);
    }

    public Buchung(String datum, String beschreibung, String kategorie, double betrag) {
        this(datum, beschreibung, kategorie, betrag, false);
    }

    public Buchung(String datum, String beschreibung, String kategorie, double betrag, boolean einnahme) {
        this.datum = datum;
        this.beschreibung=beschreibung;
        this.kategorie=kategorie.isEmpty()?"Sonstiges":kategorie;
        this.betrag=betrag<0?Math.abs(betrag):betrag;
        this.einnahme=einnahme;
    }
}
