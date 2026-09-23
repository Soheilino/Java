public class MethodeMitParameter {
    public static void main(String[] args) {
        String n="Anna";
        String v="Ben";
        begruesse(n);
        begruesse(v);

    }
    public static void begruesse(String name)
    {
        System.out.println("Hallo "+name+", willkommen!");
    }
}
