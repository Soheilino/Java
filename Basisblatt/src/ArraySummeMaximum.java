public class ArraySummeMaximum {
    public static void main(String[] args) {
        int[] zahlen = {4, 9, 2, 7, 5};

        int summe = 0;
        int max = zahlen[0];
        for( int i =0;i< zahlen.length;i++){
            summe+=zahlen[i];
            if(zahlen[i]>max){
                max=zahlen[i];
            }
        }
        System.out.println("d) Summe = "+summe+", Maximum = "+max);
    }
}
