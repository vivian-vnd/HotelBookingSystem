package HotelBooking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reservation {
    // === Attributes ===
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private int reservationId;              // Unique number for the booking
    private Guest guest;                    // Which guest made the booking
    private Room room;                      // Which room was booked
    private LocalDate checkInDate;          // when the guest is staying
    private LocalDate checkOutDate;
    private boolean breakfastIncluded;
    private String status;                  // Current status: "Active", "Cancelled", "CheckedIn", "Completed"

    // === Constructor ===
    public Reservation(int newId, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, boolean breakfastIncluded) {
        this.reservationId = newId;
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

    // Calculates how many nights the guest is staying
    public int getNumberOfNights() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    // Get a summary of the reservation (printing)
    public String getReservationDetails() {
        return "Reservation ID: " + reservationId +
                "\nGuest: " + guest.getName() +
                "\nRoom: " + room.getRoomNumber() + " (" + room.getType() + ")" +
                "\nCheck-in: " + checkInDate.format(formatter) +
                "\nCheck-out: "+ checkOutDate.format(formatter) +
                "\nBreakfast Included: " + (breakfastIncluded ? "Yes" : "No") +
                "\nStatus: " + status;
    }

    // Cancel the reservation
    public void cancelReservation() {
        this.status = "Cancelled";
    }

    // Check if reservation is till active
    public boolean isActive() {
        return status.equals("Active");
    }

    // Checks the guest in
    public void checkIn() {
        if (this.status.equals("Active")) {      // Changes the status of the reservation to "CheckedIn"
            this.status = "CheckedIn";           // This should only happen if the reservation is Active
        }
    }

    // Completes the reservation
    public void completeReservation() {
        if (this.status.equals("CheckedIn") || this.status.equals("Active")) {      // Same goes for this
            this.status = "Completed";                                              // Only used when guest is checking out
        }
    }
}
