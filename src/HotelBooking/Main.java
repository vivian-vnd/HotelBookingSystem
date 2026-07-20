package HotelBooking;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Hotel hotel = new Hotel();      // Create Hotel object
        Scanner scanner = new Scanner(System.in);
        boolean running = true;


        // temporary setup for testing
        hotel.addRoom(new Room("101", "Single", 50.0, "Available"));
        hotel.addRoom(new Room("102", "Double", 80.0, "Available"));
        hotel.addRoom(new Room("103", "Suite", 150.0, "Available"));

        // Create a guest
        Guest guest1 = new Guest("Vivian", "58858870", "iwannaeaticecream.@gmail.com");


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
                case 1: // Randall - View Available Rooms
                    System.out.println("View  Available Rooms: - not yet implemented");
                    break;

                case 2: // Vivian - Make a reservation
                    System.out.println("\n===== Make a reservation =====");

                    System.out.print("Enter Guest ID: ");
                    int guestId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Room Number: ");
                    String roomNumber = scanner.nextLine();

                    System.out.print("Enter Check-in Date (dd-mm-yyyy): ");
                    LocalDate checkInDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Enter Check-out Date (dd-mm-yyyy): ");
                    LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());

                    System.out.println("Include Breakfast? (true/false): ");
                    boolean breakfastIncluded = scanner.nextBoolean();
                    scanner.nextLine();

                    Reservation reservation = hotel.makeReservation(guestId, roomNumber, checkInDate, checkOutDate, breakfastIncluded);
                    if (reservation != null) {
                        System.out.println("\nReservation Successful!");
                        System.out.println("Reservation ID: " + reservation.getReservationId());
                        System.out.println(reservation.getReservationDetails());
                    } else {
                        System.out.println("Reservation failed. Please check and try again.");
                    }
                    break;

                case 3: // Vivian - Cancel a reservation
                    System.out.println("\n===== Cancel a reservation =====");

                    System.out.print("Enter Reservation ID to cancel: ");
                    int reservationId = scanner.nextInt();
                    scanner.nextLine();

                    boolean cancelled = hotel.cancelReservation(reservationId);

                    if  (cancelled) {
                        System.out.println("Reservation Cancelled!");
                    } else {
                        System.out.println("Could not cancel the reservation. Please check the ID and try again.");
                    }
                    break;

                case 4: // Collins - Check-in
                    System.out.println("Check-in: (not yet implemented)");
                    break;

                case 5: // Collins - Check-out
                    System.out.println("Check-out: (not yet implemented)");
                    break;

                case 6: // Joana - View all reservation
                    System.out.println("View All Reservations: (not yet implemented)");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
        scanner.close();
    }

}
