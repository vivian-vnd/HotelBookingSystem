package HotelBooking;

import java.util.ArrayList;

public class Hotel {

    private ArrayList<Reservation> reservations;    // List of all bookings
    private GuestManager guestManager;

    // Constructor
    public Hotel() {
        this.reservations = new ArrayList<>();
        this.guestManager = new GuestManager();     //
    }

    public Reservation makeReservation(int guestId, int roomNumber, boolean breakfastIncluded) {

        // Find guest using GuestManager
        Guest guest = guestManager.findGuestById(guestId);
        if (guest == null) {
            System.out.println("Guest not found.");
            return null;
        }

        // Find the room
        // Waiting for Room class

        // Make the reservation
        int newReservationId = reservations.size() + 1;
        Reservation newReservation =///////
    }
}
