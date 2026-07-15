package HotelBooking;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hotel {
    // ==== ATTRIBUTES (Data the Hotel Stores) ======

    // This list stores all the rooms in the hotel
    // waiting for room class

    // This list stores all the reservations that have been made
    // every time someone books a room, we add the Reservation object here
    private ArrayList<Reservation> reservations;

    private GuestManager guestManager;


    // ====== CONSTRUCTOR =========
    public Hotel() {
        // create the 2 lists when a Hotel object is created
        // this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.guestManager = new GuestManager();
    }

    // ===== ROOM MANAGEMENT METHODS (Randall) =========

    // ====== RESERVATION / BOOKING METHODS (Vivian) =======

    public Reservation makeReservation(int guestId, boolean breakfastIncluded) {

        // Find the guest using GuestManager
        Guest guest = guestManager.findGuestById(guestId);
        if (guest == null) {
            System.out.println("Guest not found with ID: " + guestId);
            return null;
        }

        // Find room
        // waiting for room class

        // Make a new reservation
        int newId = reservations.size() + 1;
        Reservation newReservation = new Reservation(newId, guest, breakfastIncluded);

        // add reservation to the list
        reservations.add(newReservation);

        // mark room as booked
        // waiting for room class

        return newReservation;
    }

    public boolean cancelReservation(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId) {
                ////////
            }
        }
    }
}
