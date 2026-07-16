package HotelBooking;

public class Room {
    private int roomNumber;
    private String type;
    private double pricePerNight;
    private String status; // "Available" or "Booked"

    public Room(int roomNumber, String type, double pricePerNight, String status) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }


    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Methods

    // Check if the room is available
    public boolean isAvailable() {
        return status.equalsIgnoreCase("available");
    }

    // Mark the room as Booked
    public void bookRoom() {
        this.status = "Booked";
    }

    // Mark the room as available again
    public void releaseRoom() {
        this.status = "Available";
    }

    // printing room information
    @Override
    public String toString() {
        return "Room " + roomNumber + " | Type: " + type + " | Price: €" + pricePerNight + " | Status: " + status;
    }

}