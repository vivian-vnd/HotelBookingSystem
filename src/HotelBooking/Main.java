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
            switch (choice) {
                case 1: // - Randall
                    System.out.println("View Available Rooms: (not yet implemented)");
                    break;

                case 2: // - Vivian
                    System.out.println("Make a reservation: (not yet implemented)");
                    break;

                case 3: // - Vivian
                    System.out.println("Cancel a reservation: (not yet implemented)");
                    break;
case 4: // Check-in
                System.out.print("Enter Reservation ID for Check-in: ");
                int checkInId = scanner.nextInt();
                scanner.nextLine(); //consume newline
                
                hotel.checkIn(checkInId);
                break;

            case 5: // Check-out
                System.out.print("Enter Reservation ID for Check-out: ");
                int checkOutId = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                hotel.checkOut(checkOutId);
                break;

                case 6: // Joana
                    System.out.println("View All Reservations: (not yet implemented)");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}