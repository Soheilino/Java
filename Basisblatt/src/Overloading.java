public class Overloading {
    public static void main(String[] args) {
        System.out.println("e) max(4, 9) = "+max(4,9)+" | max(4, 9, 2) = "+max(4,9,2));

    }
    public static int max(int a, int b){
        if (a>=b){
            return a;
        }
        return b;
    }
    public static int max(int a, int b, int c){
        if(c>=max(a,b)){
            return c;
        }
        return max(a,b);
    }
}
