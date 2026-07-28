package HotelBooking;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvDatabase {

    private static final String ROOMS_FILE = "rooms.csv";
    private static final String GUESTS_FILE = "guests.csv";
    private static final String RESERVATIONS_FILE = "reservations.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void loadAllData(Hotel hotel) {
        loadRooms(hotel);
        loadGuests(hotel);
        loadReservations(hotel);
    }

    public static void saveAllData(Hotel hotel) {
        saveRooms(hotel);
        saveGuests(hotel);
        saveReservations(hotel);
    }

    // ==================== ROOMS ====================
    private static void loadRooms(Hotel hotel) {
        File file = new File(ROOMS_FILE);
        if (!file.exists()) {
            seedInitialRooms(hotel);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("RoomNumber")) continue;

                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String num = parts[0].trim();
                    String type = parts[1].trim();
                    String status = parts[2].trim();

                    Room room = switch (type.toLowerCase()) {
                        case "double" -> new DoubleRoom(num, status);
                        case "deluxe" -> new DeluxeRoom(num, status);
                        case "suite" -> new SuiteRoom(num, status);
                        default -> new SingleRoom(num, status);
                    };
                    hotel.addRoom(room);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading rooms CSV: " + e.getMessage());
        }
    }

    private static void saveRooms(Hotel hotel) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ROOMS_FILE))) {
            writer.println("RoomNumber,Type,Status");
            List<Room> rooms = hotel.getAllRooms();
            if (rooms != null) {
                for (Room room : rooms) {
                    writer.println(room.getRoomNumber() + "," + room.getType() + "," + room.getStatus());
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing rooms CSV: " + e.getMessage());
        }
    }

    private static void seedInitialRooms(Hotel hotel) {
        hotel.addRoom(new SingleRoom("101", "Available"));
        hotel.addRoom(new DoubleRoom("102", "Available"));
        hotel.addRoom(new DeluxeRoom("103", "Available"));
        hotel.addRoom(new SuiteRoom("201", "Available"));
        hotel.addRoom(new SingleRoom("104", "Available"));
        hotel.addRoom(new DoubleRoom("105", "Available"));
        saveRooms(hotel);
    }

    // ==================== GUESTS ====================
    private static void loadGuests(Hotel hotel) {
        File file = new File(GUESTS_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("GuestID")) continue;

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String phone = parts[2].trim();
                    String email = parts[3].trim();

                    Guest guest = new Guest(id, name, phone, email);
                    hotel.addGuest(guest);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading guests CSV: " + e.getMessage());
        }
    }

    private static void saveGuests(Hotel hotel) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(GUESTS_FILE))) {
            writer.println("GuestID,Name,Phone,Email");
            List<Guest> guests = hotel.getAllGuests();
            if (guests != null) {
                for (Guest guest : guests) {
                    writer.println(guest.getId() + "," + guest.getName() + "," + guest.getPhoneNumber() + "," + guest.getEmail());
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing guests CSV: " + e.getMessage());
        }
    }

    // ==================== RESERVATIONS ====================
    private static void loadReservations(Hotel hotel) {
        File file = new File(RESERVATIONS_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("ReservationID")) continue;

                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    int resId = Integer.parseInt(parts[0].trim());
                    int guestId = Integer.parseInt(parts[1].trim());
                    String roomNum = parts[2].trim();
                    LocalDate checkIn = LocalDate.parse(parts[3].trim(), DATE_FORMATTER);
                    LocalDate checkOut = LocalDate.parse(parts[4].trim(), DATE_FORMATTER);
                    boolean breakfast = Boolean.parseBoolean(parts[5].trim());
                    String status = parts[6].trim();

                    Reservation res = new Reservation(resId, guestId, roomNum, checkIn, checkOut, breakfast, status);
                    hotel.addReservation(res);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading reservations CSV: " + e.getMessage());
        }
    }

    private static void saveReservations(Hotel hotel) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESERVATIONS_FILE))) {
            writer.println("ReservationID,GuestID,RoomNumber,CheckInDate,CheckOutDate,BreakfastIncluded,Status");
            List<Reservation> reservations = hotel.getAllReservations();
            if (reservations != null) {
                for (Reservation res : reservations) {
                    writer.println(res.getId() + "," +
                            res.getGuestId() + "," +
                            res.getRoomNumber() + "," +
                            res.getCheckInDate().format(DATE_FORMATTER) + "," +
                            res.getCheckOutDate().format(DATE_FORMATTER) + "," +
                            res.isBreakfastIncluded() + "," +
                            res.getStatus());
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing reservations CSV: " + e.getMessage());
        }
    }
}