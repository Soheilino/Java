import java.util.Scanner;

public class KinoplatzReservierung {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = readNumber(sc, "Bitte geben Sie die Anzahl der Reihen ein (1-8): ", 1, 8);
        int seatsPerRow = readNumber(sc, "Bitte geben Sie die Anzahl der Plätze pro Reihe ein (1-10): ", 1, 10);
        boolean[][] hall = createHall(rows, seatsPerRow);
        while (true) {
            printHall(hall);
            System.out.print("Welchen Platz reservieren? (z. B. B4, x = Ende): ");
            String eingabe = sc.nextLine();
            if (eingabe.equalsIgnoreCase("x") || eingabe.isEmpty())
                break;
            int row = parseRow(eingabe);
            int seat = parseSeat(eingabe);
            if (!isValidSeat(hall, row, seat)) {
                System.out.println("Diesen Platz gibt es nicht.");
                continue;
            }
            if (reserveSeat(hall, row, seat)) {
                System.out.println("Platz " + eingabe.toUpperCase() + " wurde reserviert.");
            } else {
                System.out.println("Platz " + eingabe.toUpperCase() + " ist bereits reserviert.");
            }

        }
        System.out.println("Auf Wiedersehen!");
        sc.close();
    }

    public static int readNumber(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            int eingabe = Integer.parseInt(sc.nextLine());
            if (eingabe >= min && eingabe <= max) {
                return eingabe;
            }
            System.out.println("Fehler: Bitte geben Sie eine Zahl von " + min + " bis " + max + " ein.");

        }
    }

    public static boolean[][] createHall(int rows, int seatsPerRow) {
        boolean[][] hall = new boolean[rows][seatsPerRow];
        return hall;
    }

    public static char rowLetter(int row) {
        char letter = (char) ('A' + row);
        return letter;
    }

    public static void printHall(boolean[][] hall) {
        for (int row = 0; row < hall.length; row++) {
            for (int seatPerRow = 0; seatPerRow < hall[row].length; seatPerRow++) {
                String label = "" + rowLetter(row) + (seatPerRow + 1);
                String text;
                if (hall[row][seatPerRow]) {
                    text = "[" + label + "]";
                } else {
                    text = label;
                }
                System.out.printf("%-5s", text);
            }
            System.out.println();
        }
    }

    public static int parseRow(String eingabe) {
        char buchstabe = Character.toUpperCase(eingabe.charAt(0));
        return buchstabe - 'A';
    }

    public static int parseSeat(String eingabe) {
        int nummer = Integer.parseInt(eingabe.substring(1));
        return nummer - 1;
    }

    public static boolean isValidSeat(boolean[][] hall, int row, int seat) {
        return row >= 0 && row < hall.length
                && seat >= 0 && seat < hall[row].length;
    }

    public static boolean reserveSeat(boolean[][] hall, int row, int seat) {
        if (hall[row][seat] == true) {
            return false;
        } else {
            hall[row][seat] = true;
            return true;
        }
    }
}








