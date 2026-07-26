package HotelBooking;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static String readValidName(Scanner scanner) {
        while (true) {
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            try {
                Guest.validateName(name);
                return name.trim().replaceAll("\s+", " ");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String readValidPhoneNumber(Scanner scanner) {
        while (true) {
            System.out.print("Enter telephone: ");
            String telephone = scanner.nextLine();

            try {
                Guest.validatePhoneNumber(telephone);
                return telephone;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String readValidEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            try {
                Guest.validateEmail(email);
                return email;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();      // Create Hotel object
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // ====TEMPORARY SETUP FOR TESTING===============

        hotel.addRoom(new Room("101", "Single", 50.0, "Available"));
        hotel.addRoom(new Room("102", "Double", 80.0, "Available"));
        hotel.addRoom(new Room("103", "Suite", 150.0, "Available"));

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
            String choiceInput = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number from 1 to 7.");
                continue;
            }

            // Call appropriate Hotel based on choice
            switch (choice) {
                case 1: // Randall - View Available Rooms
                    System.out.println("View  Available Rooms: - not yet implemented");
                    break;

                case 2: // Vivian - Make a reservation
                    System.out.println("\n===== Make a reservation =====");

                    String name = readValidName(scanner);
                    String telephone = readValidPhoneNumber(scanner);
                    String email = readValidEmail(scanner);

                    Guest newGuest = new Guest(name, telephone, email);
                    hotel.addGuest(newGuest);

                    System.out.print("Enter Room Number: ");
                    String roomNumber = scanner.nextLine();

                    System.out.print("Enter Check-in Date (dd-mm-yyyy): ");
                    LocalDate checkInDate = LocalDate.parse(scanner.nextLine(), formatter);

                    System.out.print("Enter Check-out Date (dd-mm-yyyy): ");
                    LocalDate checkOutDate = LocalDate.parse(scanner.nextLine(), formatter);

                    System.out.println("Include Breakfast? (true/false): ");
                    boolean breakfastIncluded = scanner.nextBoolean();
                    scanner.nextLine();

                    Reservation reservation = hotel.makeReservation(newGuest.getId(), roomNumber, checkInDate, checkOutDate, breakfastIncluded);
                    if (reservation != null) {
                        System.out.println("\nReservation Successful!");
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
                    System.out.print("Enter Reservation ID for Check-in: ");
                    int checkInId = scanner.nextInt();
                    scanner.nextLine(); //consume newline

                    hotel.checkIn(checkInId);
                    break;

                case 5: // Collins - Check-out
                    System.out.print("Enter Reservation ID for Check-out: ");
                    int checkOutId = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    hotel.checkOut(checkOutId);
                    break;

                case 6: // Joana - View all reservation
                   hotel.getReservations();
                    break;

                case 7:
                    System.out.println("Goodbye!! Thank your for using the Hotel Booking System!!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
        scanner.close();
    }

}
