import java.util.Scanner;
import java.util.ArrayList;

public class ListeStattArray {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        ArrayList<Integer> werte = new ArrayList<>();
        System.out.print("Geben Sie eine Zahl ein: ");
        int zahl= scanner.nextInt();
        while(zahl!=0){
            werte.add(zahl);
            System.out.print("Geben Sie eine Zahl ein: ");
            zahl= scanner.nextInt();
        }
        int summe=0;
        for(int w:werte){

            summe+=w;
        }
        System.out.println("e) "+werte.size()+" Werte gesammelt, Summe = "+summe);
        scanner.close();

    }
}
