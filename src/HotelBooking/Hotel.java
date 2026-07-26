package HotelBooking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    // ==== ATTRIBUTES (Data the Hotel Stores) ======

    // This list stores all the rooms in the hotel
    private final ArrayList<Room> rooms;                  // List of all rooms
    private final ArrayList<Reservation> reservations;    // This list stores all the reservations that have been made, every time someone books a room, we add the Reservation object here
    private final GuestManager guestManager;              // Used to find guests


    // ====== CONSTRUCTOR =========
    public Hotel() {
        // create the 2 lists when a Hotel object is created
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.guestManager = new GuestManager();
    }

    // ==== ROOM HELPER METHOD ======
    public Room findRoomByNumber(String roomNumber) {       // Searches or a room using its room number and returns the `Room` object
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }

    // ===== ROOM MANAGEMENT METHODS (Randall) =========

    public void addRoom(Room room) {        // adds a new room to the hotel's list of rooms
        if (room != null) {
            rooms.add(room);
        }
    }

    public List<Room> getAllRooms() {       // returns a list of all rooms in the hotel (both available and booked)
        return rooms;
    }

    public List<Room> getAvailableRooms() { // Returns only the room that are currently available for booking
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                available.add(room);
            }
        }
        return available;
    }

    public List<Room> getAvailableRoomsByType(String type) {    // Returns only the available rooms of a specific type eg. single or double
        List<Room> result = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getType().equalsIgnoreCase(type)) {
                result.add(room);
            }
        }
        return result;
    }

    public void updateRoomStatus(String roomNumber, String status) {
        Room room = findRoomByNumber(roomNumber);
        if (room != null) {
            room.setStatus(status);
        } else {
            System.out.println("Room " + roomNumber + " not found");
        }
    }

    // ====== RESERVATION / BOOKING METHODS (Vivian) =======

    // Creates a new booking (finds guest + room, saves it )
    public Reservation makeReservation(int guestId, String roomNumber, LocalDate checkIn, LocalDate checkOut, boolean breakfastIncluded) {


        // Find the guest using GuestManager
        Guest guest = guestManager.findGuestById(guestId);
        if (guest == null) {
            System.out.println("Guest not found with ID: " + guestId);
            return null;
        }

        // Find room using its number
        Room room = findRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Room not found with ID: " + roomNumber);
            return null;
        }

        // check if room is available
        if (!room.isAvailable()) {
            System.out.println("Room " + roomNumber + " is already booked.");
            return null;
        }

        // Make a new reservation
        int newId = reservations.size() + 1;
        Reservation newReservation = new Reservation(
                newId,
                guest,
                room,
                checkIn,
                checkOut,
                breakfastIncluded);


        // save reservation and book the room
        reservations.add(newReservation);
        room.bookRoom();
        return newReservation;
    }

    public boolean cancelReservation(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId && res.isActive()) {
                res.cancelReservation();        // Change status to Cancelled
                res.getRoom().releaseRoom();    // Free the room
                return true;
            }
        }
        System.out.println("Reservation not found or already cancelled");
        return false;
    }

    public Reservation getReservationById(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId) {
                return res;
            }
        }
        return null;
    }

    public void getReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found. To make a reservation, please select 2.");
            return;
        }

        for (Reservation reservation : reservations) {
            System.out.println("\n----------------------------");
            System.out.println(reservation.getReservationDetails());
            System.out.println("----------------------------");
        }
    }

    // ===== CHECK-IN/CHECK-OUT =====

    // Checks in a guest using reservation ID
    // Changes the status from "Active" to "CheckedIn"
    public boolean checkIn(int reservationId) {
        Reservation res = getReservationById(reservationId);    // Find the reservation by ID
        if (res == null) {                                      // Check if the reservation exist
            System.out.println("Check-in failed: Reservation not found");
            return false;
        }

        if (!res.isActive()) {          // Check if the reservation is still Active
            System.out.println("Check-in failed: Reservation is not active (current status: " + res.getStatus() + ")");
            return false;
        }

        res.checkIn(); // Change status to "CheckedIn"

        System.out.println("Check-in successful for Reservation #" + res.getReservationId());
        System.out.println("Guest: " + res.getGuest().getName());
        System.out.println("Room: " + res.getRoom().getRoomNumber());
        return true;
    }

    // Checks out a guest using reservation ID
    // Changes the status to "Completed" and frees the room
    public boolean checkOut(int reservationId) {
        Reservation res = getReservationById(reservationId);

        if (res == null) {      // Checks if the reservation exists
            System.out.println("Check-out failed: Reservation not found");
            return false;
        }

        String currentStatus = res.getStatus(); // Get current status

        if (currentStatus.equals("Completed") || currentStatus.equals("Cancelled")) {    // Prevents check-out if already completed or cancelled
            System.out.println("Check-out failed: Reservation is already" + currentStatus);
            return false;
        }

        res.completeReservation(); // Changes the status to "Completed"

        res.getRoom().releaseRoom(); // Releases the room so it becomes available again

        // === Creates and Prints the bill ===
        Invoice invoice = new Invoice(res);     // uses the default breakfast rate
        invoice.setPaid(true);
        invoice.printReceipt();

        // === Successful message ===
        System.out.println("Check-out successful for Reservation #" + res.getReservationId());
        System.out.println("Guest: " + res.getGuest().getName());
        System.out.println("Room: " + res.getRoom().getRoomNumber() + " is now available");
        return true;


    }


    // ===== ADDS GUEST TO THE SYSTEM =====
    public void addGuest(Guest guest) {
        guestManager.addGuest(guest);
    }

    // show all guests (for testing)
    public void showAllGuests() {
        System.out.println("=== Guests in the system ===");
        for (Guest g : guestManager.getAllGuests()) {
            System.out.println("ID: " + g.getId() + " | Name: " + g.getName());
        }
    }

    // ==== Filtering Price ======
    public List<Room> getRoomByMaxPrice(double maxPrice) {
        List<Room> result = new ArrayList<>();

        for (Room room : rooms) {   // Goes through every room in the hotel one by one
            if (room.isAvailable() && room.getPrice() <= maxPrice) {    // Checks if the room price is less than or equal to the price the user entered
                result.add(room);
            }
        }
        return result;
    }
}
