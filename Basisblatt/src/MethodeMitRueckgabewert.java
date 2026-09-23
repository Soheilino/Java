import java.util.Scanner;

public class MethodeMitRueckgabewert {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Geben Sie eine Zahl ein: ");
        int zahl=scanner.nextInt();
        int i=0;
        while(zahl>=0){
            i++;
            System.out.println("c"+i+") quadrat("+zahl+") = "+quadrat(zahl));
            System.out.print("Geben Sie eine neue Zahl ein: ");
            zahl=scanner.nextInt();
        }
            i++;
            System.out.println("c"+i+") quadrat("+zahl+") = \"ungültige Eingabe\"");
            scanner.close();
    }
    public static int quadrat(int n) {
        return n*n;
    }
}
