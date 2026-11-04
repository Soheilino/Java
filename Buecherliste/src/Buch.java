public class Buch {
    public String titel;
    public String autor;
    public int jahr;

    public Buch(String titel, String autor, int jahr) {
        this.titel = titel;
        this.autor = autor;
        this.jahr = jahr;
    }

    public Buch() {

    }
    public String toCsv(){
        return (this.titel+";"+this.autor+";"+this.jahr);
    }
}
