public class MethodeMitArrayParameterUndRueckgabe {
    public static void main(String[] args) {
        int []arr={3,6,1,9};
        System.out.println("d) Summe = "+summe(arr));

    }
    public static int summe(int[] werte){
        int summe=0;
        for(int i=0;i< werte.length;i++){
            summe+=werte[i];
        }
        return summe;
    }
}
