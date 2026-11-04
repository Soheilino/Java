public class Kunde {
    public String vorname;
    public String nachname;
    public int alter;
    public boolean student;
    public Kunde(String vorname, String nachname){
        this(vorname,nachname,18,false);
    }
    public Kunde(String vorname,String nachname,int alter,boolean student){
        this.vorname=vorname;
        this.nachname=nachname;
        this.alter=alter<0?0:alter;
        this.student=student;
    }
}
