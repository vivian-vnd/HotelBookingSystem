package HotelBooking;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String readValidName(Scanner scanner) {
        while (true) {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            try {
                Guest.validateName(guestName);
                return guestName.trim().replaceAll("\\s+", " ");
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

        // ==== LOAD DATA FROM CSV FILES AT STARTUP ====
        System.out.println("Loading hotel data from CSV database...");
        CsvDatabase.loadAllData(hotel);
        // =============================================

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
            System.out.print("Enter your choice: ");

            // Get user choice
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter a number from 1 to 7.");
                continue;
            }

            // Call appropriate Hotel based on choice
            switch (choice) {

                case 1: // Randall - View Available Rooms
                    System.out.println("\n===== View Available Rooms =====");
                    System.out.println("1. Show All Available Rooms");
                    System.out.println("2. Filter by Room Type");
                    System.out.println("3. Filter by Price per Night");
                    System.out.print("Enter your choice: ");

                    int filterChoice;
                    try {
                        filterChoice = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid input. Showing all the available rooms.");
                        filterChoice = 1;
                    }

                    List<Room> filteredRooms = new ArrayList<>();

                    switch (filterChoice) {
                        case 1:
                            filteredRooms = hotel.getAvailableRooms();
                            break;

                        case 2:
                            System.out.print("Enter Room Type (e.g., Single, Double, Suite): ");
                            String type = scanner.nextLine();
                            filteredRooms = hotel.getAvailableRoomsByType(type);
                            break;

                        case 3:
                            try {
                                System.out.print("Enter maximum price per night: ");
                                double maxPrice = Double.parseDouble(scanner.nextLine());
                                filteredRooms = hotel.getRoomByMaxPrice(maxPrice);
                            } catch (Exception e) {
                                System.out.println("Invalid price. Showing all the available rooms.");
                                filteredRooms = hotel.getAvailableRooms();
                            }
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
                                    + room.getType() + "\t\t€"
                                    + room.getPrice());
                        }
                    }
                    break;

                case 2: // Vivian - Make a reservation
                    System.out.println("\n===== Make a reservation =====");

                    String roomNumber;
                    LocalDate checkInDate = null;
                    LocalDate checkOutDate = null;
                    boolean breakfastIncluded;

                    // === Guest Details ===
                    String guestName = readValidName(scanner);
                    String telephone = readValidPhoneNumber(scanner);
                    String email = readValidEmail(scanner);

                    Guest newGuest = new Guest(guestName, telephone, email);
                    hotel.addGuest(newGuest);

                    // === Room Number ===
                    while (true) {
                        try {
                            System.out.print("Enter room number: ");
                            roomNumber = scanner.nextLine().trim();

                            Room selectedRoom = hotel.findRoomByNumber(roomNumber);

                            if (selectedRoom == null) {
                                throw new IllegalArgumentException("Invalid room number! This room does not exist.");
                            }

                            if (!selectedRoom.isAvailable()) {
                                throw new IllegalArgumentException("Room is already booked. Please choose another room.");
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }


                    // === Date input ===
                    while (true) {
                        try {
                            System.out.print("Enter Check-in Date (dd-MM-yyyy): ");
                            checkInDate = LocalDate.parse(scanner.nextLine(), formatter);
                            break;  // correct date -> leave the loop
                        } catch (Exception e) {
                            System.out.println("Invalid date format! Please use dd-MM-yyyy (example: 25-07-2026)");
                        }
                    }

                    while (true) {
                        try {
                            System.out.print("Enter Check-out Date (dd-MM-yyyy): ");
                            checkOutDate = LocalDate.parse(scanner.nextLine(), formatter);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid date format! Please use dd-MM-yyyy (example: 25-07-2026)");
                        }
                    }

                    // === Breakfast ===
                    while (true) {
                        try {
                            System.out.print("Include Breakfast? (true/false): ");
                            breakfastIncluded = Boolean.parseBoolean(scanner.nextLine().trim());
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input! Please use true or false.");
                        }
                    }

                    // === Make the reservation ===
                    Reservation reservation = hotel.makeReservation(newGuest.getId(), roomNumber, checkInDate, checkOutDate, breakfastIncluded);
                    if (reservation != null) {
                        System.out.println("\nReservation Successful!");
                        System.out.println(reservation.getReservationDetails());
                        CsvDatabase.saveAllData(hotel);
                    } else {
                        System.out.println("Reservation failed. Please check and try again.");
                    }
                    break;

                case 3: // Vivian - Cancel a reservation
                    System.out.println("\n===== Cancel a reservation =====");

                    int reservationId;

                    try {
                        System.out.print("Enter Reservation ID to cancel: ");
                        reservationId = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid reservation ID! Please enter a number for Reservation ID");
                        break;
                    }

                    boolean cancelled = hotel.cancelReservation(reservationId);

                    if (cancelled) {
                        System.out.println("Reservation #" + reservationId + " has been cancelled!");
                        CsvDatabase.saveAllData(hotel);
                    } else {
                        System.out.println("Could not cancel the reservation. Please check the ID and try again.");
                    }
                    break;

                case 4: // Collins - Check-in
                    System.out.println("\n===== Check-In =====");

                    System.out.print("Enter Reservation ID for Check-in: ");
                    try {
                        int checkInId = Integer.parseInt(scanner.nextLine());
                        hotel.checkIn(checkInId);
                        CsvDatabase.saveAllData(hotel);
                    } catch (Exception e) {
                        System.out.println("Invalid reservation ID! Please enter a valid number.");
                    }
                    break;

                case 5: // Collins - Check-out
                    System.out.println("\n===== Check-Out =====");

                    System.out.print("Enter Reservation ID for Check-out: ");
                    try {
                        int checkOutId = Integer.parseInt(scanner.nextLine());
                        hotel.checkOut(checkOutId);
                        CsvDatabase.saveAllData(hotel);
                    } catch (Exception e) {
                        System.out.println("Invalid reservation ID! Please enter a valid number.");
                    }
                    break;

                case 6: // Joana - View all reservation
                    hotel.getReservations();
                    break;

                case 7:
                    System.out.println("Saving all data to CSV database...");
                    CsvDatabase.saveAllData(hotel);
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