package HotelBooking;

public abstract class Room implements Priceable {
    private String roomNumber;
    private String status;

    public Room(String roomNumber, String status) {
        this.roomNumber = roomNumber;
        this.status = status;
    }

    // Getters and Setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Abstract method: Every subclass must define its own type name
    public abstract String getType();

    // ==== Room Status Management ====
    public boolean isAvailable() {
        return "Available".equalsIgnoreCase(status);
    }

    // Marks the room as booked
    public void bookRoom() {
        this.status = "Booked";
    }

    // Mark the room as available again (after cancellation or checkout)
    public void releaseRoom() {
        this.status = "Available";
    }

    // Polymorphism method from priceable interface implemented in subclasses
    @Override
    public abstract double getPrice();

    // ====== PRINTING =======
    @Override
    public String toString() {
        return "Room " + roomNumber + " | Type: " + getType() + " | Price: €" + getPrice() + " | Status: " + status;
    }
}