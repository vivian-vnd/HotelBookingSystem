package HotelBooking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reservation implements priceable {
    // === Attributes ===
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private int reservationId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean breakfastIncluded;
    private String status;  // "Active", "Cancelled", "CheckedIn", "Completed"

    // === Constructor for new reservations (Guest + Room objects) ===
    public Reservation(int newId, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, boolean breakfastIncluded) {
        this.reservationId = newId;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.breakfastIncluded = breakfastIncluded;
        this.guest = guest;
        this.status = "Active";
    }

    // === Constructor for loading from CSV (IDs + room number — resolved later by Hotel) ===
    // Stores raw IDs temporarily; Hotel.addReservation() resolves them to objects.
    private int _guestId;
    private String _roomNumber;
    private boolean _csvLoaded = false;

    public Reservation(int resId, int guestId, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate, boolean breakfastIncluded, String status) {
        this.reservationId = resId;
        this._guestId = guestId;
        this._roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.breakfastIncluded = breakfastIncluded;
        this.status = status;
        this._csvLoaded = true;
    }

    // Called by Hotel.addReservation() after guests/rooms are all loaded
    public void resolve(Guest guest, Room room) {
        this.guest = guest;
        this.room = room;
        this._csvLoaded = false;
    }

    // === Getters ===
    public int getReservationId() { return reservationId; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public boolean isBreakfastIncluded() { return breakfastIncluded; }
    public String getStatus() { return status; }

    // Getters used by CsvDatabase when saving
    public int getGuestId() { return (_csvLoaded || guest == null) ? _guestId : guest.getId(); }
    public String getRoomNumber() { return (_csvLoaded || room == null) ? _roomNumber : room.getRoomNumber(); }

    // === Methods ===

    public int getNumberOfNights() {
        return (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public String getReservationDetails() {
        String guestName = (guest != null) ? guest.getName() : "Guest #" + _guestId;
        String roomInfo  = (room  != null) ? room.getRoomNumber() + " (" + room.getType() + ")" : _roomNumber;
        return "Reservation ID: " + reservationId +
                "\nGuest: " + guestName +
                "\nRoom: " + roomInfo +
                "\nCheck-in: " + checkInDate.format(formatter) +
                "\nCheck-out: " + checkOutDate.format(formatter) +
                "\nBreakfast Included: " + (breakfastIncluded ? "Yes" : "No") +
                "\nStatus: " + status;
    }

    public void cancelReservation() { this.status = "Cancelled"; }

    public boolean isActive() { return status.equals("Active"); }

    public void checkIn() {
        if (this.status.equals("Active")) {
            this.status = "CheckedIn";
        }
    }

    public void completeReservation() {
        if (this.status.equals("CheckedIn") || this.status.equals("Active")) {
            this.status = "Completed";
        }
    }

    @Override
    public double getPrice() {
        double roomTotal = room.getPrice() * getNumberOfNights();
        double breakfastCost = 0;
        if (this.breakfastIncluded) {
            breakfastCost = 15.0 * getNumberOfNights();
        }
        return roomTotal + breakfastCost;
    }
}