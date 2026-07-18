package HotelBooking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    // ==== ATTRIBUTES (Data the Hotel Stores) ======

    // This list stores all the rooms in the hotel
    private ArrayList<Room> rooms;                  // List of all rooms
    private ArrayList<Reservation> reservations;    // This list stores all the reservations that have been made, every time someone books a room, we add the Reservation object here
    private GuestManager guestManager;              // Used to find guests


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

    public List<Reservation> getReservations() {
        return reservations;
    }

    // ===== Check in and check out =====

    // Finds the reservation using the reservationId
    public void checkIn(int reservationId) {
        Reservation res = getReservationById(reservationId);
        if (res != null && res.isActive()) {
            System.out.println("Check-in succesful for reservation #" + reservationId);
        } else {
            System.out.println("Check-in failed for reservation #" + reservationId);
        }
    }

    public void checkOut(int reservationId) {
        Reservation res = getReservationById(reservationId);
        if (res != null && res.isActive()) {
            System.out.println("Check-out succesful for reservation #" + reservationId);
        } else {
            System.out.println("Check-out failed for reservation #" + reservationId);
        }
    }

    // only prints a message for now. Doesn't change the status of the reservation yet.
}
