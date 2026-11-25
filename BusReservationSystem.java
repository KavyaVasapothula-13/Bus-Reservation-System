import java.util.*;
public class BusReservationSystem {
    static final int ROWS = 5;
    static final int COLS = 4;
    static String[][] seats = new String[ROWS][COLS];
    static Queue<String> waitingList = new LinkedList<>();
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Initialize seats
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                seats[i][j] = "Empty";
            }
        }

        while (true) {
            System.out.println("\n--- Bus Reservation System ---");
            System.out.println("1. Book Seat");
            System.out.println("2. Cancel Seat");
            System.out.println("3. View Seats");
            System.out.println("4. View Waiting List");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    bookSeat(sc);
                    break;
                case 2:
                    cancelSeat(sc);
                    break;
                case 3:
                    displaySeats();
                   break;
                case 4:
                    displayWaitingList();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    // Seat label (A1, B2...)
    static String getSeatLabel(int row, int col) {
        char rowChar = (char) ('A' + row);
        return rowChar + "" + (col + 1);
    }
    // Convert seat label to index
    static int[] getSeatIndex(String label) {
        int row = label.charAt(0) - 'A';
        int col = Integer.parseInt(label.substring(1)) - 1;
        return new int[]{row, col};
    }
       static void bookSeat(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter seat (e.g., A1): ");
        String seatChoice = sc.nextLine().toUpperCase();
       int[] index = getSeatIndex(seatChoice);
        int i = index[0], j = index[1];
        if (i < 0 || i >= ROWS || j < 0 || j >= COLS) {
            System.out.println("Invalid seat!");
            return;
        }
        if (!seats[i][j].equals("Empty")) {
            System.out.println("Seat already booked!");
            return;
        }
       seats[i][j] = name;
        System.out.println("Seat booked: " + seatChoice);
    }
      static void cancelSeat(Scanner sc) {
        System.out.print("Enter your name to cancel: ");
        String name = sc.nextLine();
         for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (seats[i][j].equals(name)) {
                    seats[i][j] = "Empty";
    System.out.println("Seat cancelled: " + getSeatLabel(i, j));
                 if (!waitingList.isEmpty()) {
                        String next = waitingList.poll();
                        seats[i][j] = next;
    System.out.println("Seat given to waiting person: " + next);
                    }
                    return;
                }
            }
        }
        System.out.println("No booking found!");
    }
    static void displaySeats() {
        System.out.println("\nSeat Layout:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
  System.out.print(getSeatLabel(i, j) + "(" + seats[i][j] + ")\t");
            }
            System.out.println();
        }
    }
       static void displayWaitingList() {
        System.out.println("\nWaiting List:");
        if (waitingList.isEmpty()) {
            System.out.println("No one waiting.");
        } else {
            for (String person : waitingList) {
                System.out.println(person);
            }
        }
    }
}
