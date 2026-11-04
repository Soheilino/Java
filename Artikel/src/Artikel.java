public class Artikel {
    public String name;
    public double preis;
    public int menge;
    public  Artikel(String name,double preis,int menge){
        this.name=name;
        this.preis=preis;
        this.menge=menge;
    }
    public double gesamtPreis(){
        double ergebnis =this.preis*this.menge;
        return ergebnis;
    }
}
