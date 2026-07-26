package HotelBooking;

public abstract class Room implements priceable {
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

    public void bookRoom() {
        this.status = "Booked";
    }

    public void releaseRoom() {
        this.status = "Available";
    }

    // Polymorphism method from priceable interface implemented in subclasses
    @Override
    public abstract double getPrice();

    @Override
    public String toString() {
        return "Room " + roomNumber + " | Type: " + getType() + " | Price: €" + getPrice() + " | Status: " + status;
    }
}