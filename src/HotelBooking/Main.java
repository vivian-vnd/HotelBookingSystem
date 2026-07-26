package HotelBooking;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Hotel hotel = new Hotel();      // Create Hotel object
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // ====TEMPORARY SETUP FOR TESTING===============

        hotel.addRoom(new Room("101", "Single", 50.0, "Available"));
        hotel.addRoom(new Room("102", "Double", 80.0, "Available"));
        hotel.addRoom(new Room("103", "Suite", 150.0, "Available"));

        // Create a guest
        Guest guest1 = new Guest("Bob", "98873647", "tiramisulover.@gmail.com");
        System.out.println("TestGuestID: " + guest1.getId());
        hotel.addGuest(guest1);
        hotel.showAllGuests();

        // ==================================================

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
                    System.out.println("\n===== View Available Rooms =====");
                    System.out.println("1. Show All Available Rooms");
                    System.out.println("2. Filter by Room Type");
                    System.out.println("3. Filter by Price per Night");
                    System.out.println("Enter your choice: ");

                    int filterChoice = scanner.nextInt();
                    scanner.nextLine(); // Clear input buffer

                    List<Room> filteredRooms = new ArrayList<>();

                    switch (filterChoice) {
                        case 1:
                            filteredRooms = hotel.getAvailableRooms();
                            break;

                        case 2:
                            System.out.println("Enter Room Type (e.g., Single, Double, Suite): ");
                            String type = scanner.nextLine();
                            filteredRooms = hotel.getAvailableRooms ();
                            break;

                        case 3:
                            System.out.println("Enter Price per Night: ");
                            double getPricePerNight = scanner.nextDouble();
                            scanner.nextLine(); // Clear input buffer
                            filteredRooms = hotel.getPricePerNight(getPricePerNight);
                            break;

                        default:
                            System.out.println("Invalid option. Showing all available rooms.");
                            filteredRooms = hotel.getAvailableRooms();
                            break;
                    }

                    // Display Results using println
                    System.out.println();
                    if (filteredRooms == null || filteredRooms.isEmpty()) {
                        System.out.println("No available rooms match your search criteria.");
                    } else {
                        System.out.println("Room Number\tRoom Type\tPrice/Night");
                        System.out.println("------------------------------------------------");

                        for (Room room : filteredRooms) {
                            System.out.println(room.getRoomNumber() + "\t\t"
                                    + room.getType() + "\t\t$"
                                    + room.getPricePerNight());
                        }
                    }
                    break;

                    case 2: // Vivian - Make a reservation
                    System.out.println("\n===== Make a reservation =====");

                    int guestId;
                    String roomNumber;
                    LocalDate checkInDate = null;
                    LocalDate checkOutDate = null;
                    boolean breakfastIncluded;

                    // === Guest ID ===
                    try {
                        System.out.print("Enter Guest ID: ");
                        guestId = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid Guest ID! Please enter a number");
                        scanner.nextLine();
                        break;
                    }

                    // === Room Number ===
                    System.out.print("Enter Room Number: ");
                    roomNumber = scanner.nextLine();

                    // === Date input ===

                    try {
                        System.out.print("Enter Check-in Date (dd-MM-yyyy): ");
                        checkInDate = LocalDate.parse(scanner.nextLine(), formatter);

                        System.out.print("Enter Check-out Date (dd-MM-yyyy): ");
                        checkOutDate = LocalDate.parse(scanner.nextLine(), formatter);
                    } catch (Exception e) {
                        System.out.println("Invalid date format! Please use dd-MM-yyyy (example: 25-07-2026)");
                        break;  // Go back to menu
                    }

                    // === Breakfast ===
                    try {
                        System.out.print("Include Breakfast? (true/false): ");
                        breakfastIncluded = scanner.nextBoolean();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid breakfast included! Please type only true or false");
                        scanner.nextLine();
                        break;
                    }

                    // === Make the reservation ===
                    Reservation reservation = hotel.makeReservation(guestId, roomNumber, checkInDate, checkOutDate, breakfastIncluded);
                    if (reservation != null) {
                        System.out.println("\nReservation Successful!");
                        System.out.println(reservation.getReservationDetails());
                    } else {
                        System.out.println("Reservation failed. Please check and try again.");
                    }
                    break;

                case 3: // Vivian - Cancel a reservation
                    System.out.println("\n===== Cancel a reservation =====");

                    int reservationId;

                    try {
                        System.out.print("Enter Reservation ID to cancel: ");
                        reservationId = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid reservation ID! Please enter a number for Reservation ID");
                        scanner.nextLine();
                        break;
                    }

                    boolean cancelled = hotel.cancelReservation(reservationId);

                    if (cancelled) {
                        System.out.println("Reservation #" + reservationId + " has been cancelled!");
                    } else {
                        System.out.println("Could not cancel the reservation. Please check the ID and try again.");
                    }
                    break;

                case 4: // Collins - Check-in
                    System.out.println("\n===== Check-In =====");

                    int checkInId;

                    try {
                        System.out.print("Enter Reservation ID for Check-in: ");
                        checkInId = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter a number for Reservation ID");
                        scanner.nextLine();
                        break;
                    }

                    hotel.checkIn(checkInId);
                    break;

                case 5: // Collins - Check-out
                    System.out.println("\n===== Check-Out  =====");

                    int checkOutId;

                    try {
                        System.out.print("Enter Reservation ID for Check-out: ");
                        checkOutId = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter a number for Reservation ID");
                        scanner.nextLine();
                        break;
                    }


                    hotel.checkOut(checkOutId);
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
