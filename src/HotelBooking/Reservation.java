package HotelBooking;

public class Reservation {
    // === Attributes ===
    private int reservationId;
    private Guest guest;
    private Room room;
    // private LocalDate checkInDate
    // private LocalDate checkOutDate
    private boolean breakfastIncluded;
    private String status;

    // === Constructor ===
    public Reservation(int reservationId, boolean breakfastIncluded, String status, Guest guest, Room room) {
        this.reservationId = reservationId;
        this.room = room;
        this.breakfastIncluded = breakfastIncluded;
        this.guest = guest;
        this.status = "Active";     // Default staus when booking is made
    }

    public Reservation(int newId, Guest guest, boolean breakfastIncluded, Room room) {
        this.reservationId = newId;
        this.guest = guest;
        this.room = room;
        this.breakfastIncluded = breakfastIncluded;
        this.status = "Active";
    }


    // === Getters ===
    public int getReservationId() {
        return reservationId;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public Guest getGuest() {
        return guest;
    }

    public String getStatus() {
        return status;
    }

    public Room getRoom() {
        return room;
    }

    // === Methods ===

    // Calculates how many nights the guest is staying

    // Get a summary of the reservation (printing)
    public String getReservationDetails() {
        return "Reservation ID: " + reservationId + "\nGuest: " + guest.getName() + "\nRoom: " + room.getRoomNumber() + " (" + room.getType() + ")" + "\nBreakfast Included: " + (breakfastIncluded ? "Yes" : "No") + "\nStatus: " + status;
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
