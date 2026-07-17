package HotelBooking;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hotel {
    // ==== ATTRIBUTES (Data the Hotel Stores) ======

    // This list stores all the rooms in the hotel
    private ArrayList<Room> rooms;

    // This list stores all the reservations that have been made
    // every time someone books a room, we add the Reservation object here
    private ArrayList<Reservation> reservations;

    private GuestManager guestManager;


    // ====== CONSTRUCTOR =========
    public Hotel() {
        // create the 2 lists when a Hotel object is created
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.guestManager = new GuestManager();
    }

    // ==== ROOM HELPER METHOD ======
    public Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    // ===== ROOM MANAGEMENT METHODS (Randall) =========

    // ====== RESERVATION / BOOKING METHODS (Vivian) =======

    public Reservation makeReservation(int guestId, int roomNumber,boolean breakfastIncluded) {


        // Find the guest using GuestManager
        Guest guest = guestManager.findGuestById(guestId);
        if (guest == null) {
            System.out.println("Guest not found with ID: " + guestId);
            return null;
        }

        // Find room
        Room room = findRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Room not found with ID: " + roomNumber);
            return null;
        }

        ///// updating

        // Make a new reservation


        // add reservation to the list
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
}
