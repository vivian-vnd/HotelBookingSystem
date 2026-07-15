package HotelBooking;

public class Reservation {
    // === Attributes ===
    private int reservationId;
    private Guest guest;
    //  private Room room;
    // private LocalDate checkInDate
    // private LocalDate checkOutDate
    private boolean breakfastIncluded;
    private String status;

    // === Constructor ===
    public Reservation(int reservationId, boolean breakfastIncluded, String status) {
        this.reservationId = reservationId;
        this.breakfastIncluded = breakfastIncluded;
        this.status = "Active";     // Default staus when booking is made
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

    // === Methods ===
    
}
