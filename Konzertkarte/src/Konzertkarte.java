public class Konzertkarte {
    public String kuenstler;
    public String ort;
    public int reihe;
    public int platz;
    public double preis;
    public boolean vip;
    public Kunde besitzer;

    public Konzertkarte() {
        this("Unbekannt", "Stadthalle", 1, 1, 49.90, false);
    }

    public Konzertkarte(String kuenstler, String ort) {
        this(kuenstler, ort, 1, 1, 49.90, false);
    }

    public Konzertkarte(String kuenstler, String ort, int reihe, int platz, double preis) {
        this(kuenstler, ort, reihe, platz, preis, preis > 150);
    }

    public Konzertkarte(String kuenstler, String ort, int reihe, int platz, double preis, boolean vip) {
        this.kuenstler = kuenstler;
        this.ort = ort;
        this.reihe = reihe < 1 ? 1 : reihe;
        this.platz = platz < 1 ? 1 : platz;
        this.preis = preis;
        this.vip = vip;
    }

    public Konzertkarte(String kuenstler, String ort, int reihe, int platz, double preis, Kunde besitzer) {
        this(kuenstler, ort, reihe, platz, preis, preis > 150);
        this.besitzer = besitzer;
        if (besitzer != null && besitzer.student) {
            this.preis = preis * 0.8;
        }
    }
}
