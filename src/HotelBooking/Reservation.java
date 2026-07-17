package HotelBooking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    // === Attributes ===
    private int reservationId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean breakfastIncluded;
    private String status;

    // === Constructor ===
    public Reservation(int newId, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, boolean breakfastIncluded) {
        this.reservationId = reservationId;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.breakfastIncluded = breakfastIncluded;
        this.guest = guest;
        this.status = "Active";     // Default staus when booking is made
    }

    // === Getters ===
    public int getReservationId() {
        return reservationId;
    }
    public Guest getGuest() {
        return guest;
    }
    public Room getRoom() {
        return room;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }
    public String getStatus() {
        return status;
    }



    // === Methods ===

    // Calculates how many nights the guest is staying (needs changes)
    public int getNumberOfNights() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    // Get a summary of the reservation (printing)
    public String getReservationDetails() {
        return "Reservation ID: " + reservationId + "\nGuest: " + guest.getName() + "\nRoom: " + room.getRoomNumber() + " (" + room.getType() + ")" + "\nCheck-in: " + checkInDate + "\nCheck-out: "+ checkOutDate + "\nBreakfast Included: " + (breakfastIncluded ? "Yes" : "No") + "\nStatus: " + status;
    }

    // Cancel the reservation
    public void cancelReservation() {
        this.status = "Cancelled";
    }

    // Check if reservation is till active
    public boolean isActive() {
        return status.equals("Active");
    }
}
