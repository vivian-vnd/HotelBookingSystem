package HotelBooking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Hotel hotel = new Hotel();      // Create Hotel object
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // show menu
            System.out.println("\n===== HOTEL BOOKING SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Check-in");
            System.out.println("5. Check-out");
            System.out.println("6. View All Reservations");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");

            // Get user choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Call appropriate Hotel based on choice
        }
    }
}
