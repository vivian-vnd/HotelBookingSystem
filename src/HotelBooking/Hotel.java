package HotelBooking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private GuestManager guestManager;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.guestManager = new GuestManager();
    }

    // ==== ROOM HELPERS ====

    public Room findRoomByNumber(String roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }

    public void addRoom(Room room) {
        if (room != null) rooms.add(room);
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) available.add(room);
        }
        return available;
    }

    public List<Room> getAvailableRoomsByType(String type) {
        List<Room> result = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getType().equalsIgnoreCase(type)) result.add(room);
        }
        return result;
    }

    public void updateRoomStatus(String roomNumber, String status) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null) room.setStatus(status);
        else System.out.println("Room " + roomNumber + " not found");
    }

    public List<Room> getRoomByMaxPrice(double maxPrice) {
        List<Room> result = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getPrice() <= maxPrice) result.add(room);
        }
        return result;
    }

    // ==== GUEST MANAGEMENT ====

    public void addGuest(Guest guest) {
        guestManager.addGuest(guest);
    }

    public List<Guest> getAllGuests() {
        return guestManager.getAllGuests();
    }

    public void showAllGuests() {
        System.out.println("=== Guests in the system ===");
        for (Guest g : guestManager.getAllGuests()) {
            System.out.println("ID: " + g.getId() + " | Name: " + g.getName());
        }
    }

    // ==== RESERVATION MANAGEMENT ====

    public Reservation makeReservation(int guestId, String roomNumber, LocalDate checkIn, LocalDate checkOut, boolean breakfastIncluded) {
        Guest guest = guestManager.findGuestById(guestId);
        if (guest == null) {
            System.out.println("Guest not found with ID: " + guestId);
            return null;
        }

        Room room = findRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Room not found: " + roomNumber);
            return null;
        }

        if (!room.isAvailable()) {
            System.out.println("Room " + roomNumber + " is already booked.");
            return null;
        }

        int newId = reservations.size() + 1;
        Reservation newReservation = new Reservation(newId, guest, room, checkIn, checkOut, breakfastIncluded);
        reservations.add(newReservation);
        room.bookRoom();
        return newReservation;
    }

    // Used by CsvDatabase to load reservations from CSV (resolves IDs to objects)
    public void addReservation(Reservation res) {
        Guest guest = guestManager.findGuestById(res.getGuestId());
        Room room = findRoomByNumber(res.getRoomNumber());

        if (guest == null || room == null) {
            System.out.println("Warning: Could not resolve reservation #" + res.getReservationId()
                    + " — guest or room not found. Skipping.");
            return;
        }

        res.resolve(guest, room);

        // Sync room status from reservation status so loaded state is consistent
        if (res.getStatus().equals("Active") || res.getStatus().equals("CheckedIn")) {
            room.setStatus("Booked");
        }

        reservations.add(res);
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    // Prints all reservations (called from Main case 6)
    public void getReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        System.out.println("\n===== All Reservations =====");
        for (Reservation res : reservations) {
            System.out.println("-----------------------------");
            System.out.println(res.getReservationDetails());
        }
    }

    public boolean cancelReservation(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId && res.isActive()) {
                res.cancelReservation();
                res.getRoom().releaseRoom();
                return true;
            }
        }
        System.out.println("Reservation not found or already cancelled.");
        return false;
    }

    public Reservation getReservationById(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId) return res;
        }
        return null;
    }

    // ==== CHECK-IN / CHECK-OUT ====

    public void checkIn(int reservationId) {
        Reservation res = getReservationById(reservationId);
        if (res == null) {
            System.out.println("Check-in failed: Reservation not found.");
            return;
        }
        if (!res.isActive()) {
            System.out.println("Check-in failed: Reservation is not active (status: " + res.getStatus() + ")");
            return;
        }
        res.checkIn();
        System.out.println("Check-in successful for Reservation #" + res.getReservationId());
        System.out.println("Guest: " + res.getGuest().getName());
        System.out.println("Room: " + res.getRoom().getRoomNumber());
    }

    public void checkOut(int reservationId) {
        Reservation res = getReservationById(reservationId);
        if (res == null) {
            System.out.println("Check-out failed: Reservation not found.");
            return;
        }
        String currentStatus = res.getStatus();
        if (currentStatus.equals("Completed") || currentStatus.equals("Cancelled")) {
            System.out.println("Check-out failed: Reservation is already " + currentStatus + ".");
            return;
        }
        res.completeReservation();
        res.getRoom().releaseRoom();

        // Print invoice on check-out
        Invoice invoice = new Invoice(res);
        invoice.setPaid(true);
        invoice.printReceipt();

        System.out.println("Check-out successful for Reservation #" + res.getReservationId());
        System.out.println("Guest: " + res.getGuest().getName());
        System.out.println("Room: " + res.getRoom().getRoomNumber() + " is now available.");
    }
}