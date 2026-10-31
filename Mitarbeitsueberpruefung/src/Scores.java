import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Scores {
    public static void main(String[] args) {
        System.out.println(rank(1500));
        System.out.println(rank(600));
        System.out.println(rank(250));
        System.out.println(rank(249));
        int[] punkte = {1200, 300, 700, 100, 500};
        System.out.println(countReached(punkte, 500));
        System.out.println(countReached(new int[0], 500));
        writeReport(new int[]{4, -7, 10}, "report.txt");
        writeCountdown(3, "countdown.txt");
    }

    public static String rank(int score) {
        if (score >= 1000) {
            return "Meister";
        } else if (score >= 750) {
            return "Profi";
        } else if (score >= 500) {
            return "Fortgeschritten";
        } else if (score >= 250) {
            return "Anfaenger";
        } else return "Neuling";
    }

    public static int countReached(int[] scores, int target) {
        int count = 0;
        if (scores.length == 0) return 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= target) {
                count++;
            }
        }
        return count;
    }

    public static void writeReport(int[] values, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            int summe = 0;
            writer.write("Nr ; Wert ; Art");
            writer.newLine();
            if (values.length == 0) {
                writer.write("Summe ;0");
            } else {
                for (int i = 0; i < values.length; i++) {
                    if (values[i] % 2 == 0) {
                        writer.write((i + 1) + " ; " + values[i] + " ; gerade");
                        writer.newLine();
                        summe += values[i];
                    } else {
                        writer.write((i + 1) + " ; " + values[i] + " ; ungerade");
                        writer.newLine();
                        summe += values[i];
                    }
                }
                writer.write("Summe ; " + summe);
            }
        } catch (IOException o) {
            System.out.println("Fehler: " + o.getMessage());
        }
    }

    public static void writeCountdown(int start, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            while (start >= 1) {
                writer.write(String.valueOf(start));
                writer.newLine();
                start--;
            }
            writer.write("Start!");
        } catch (IOException o) {
            System.out.println("Fehler: " + o.getMessage());
        }
    }
}