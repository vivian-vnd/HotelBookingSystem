package HotelBooking;

public class Room {
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private String status;

    public Room(String roomNumber, String type, double pricePerNight, String status) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    private double getPriceByType(String type) {
        switch (type.toLowerCase()) {
            case "single":
                return 50.00;
            case "double":
                return 80.00;
            case "deluxe":
                return 120.00;
            case "suite":
                return 200.00;
            default:
                return 0.00;
        }
    }

    // Getters and setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
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

    // ==== Methods ======

    // Check if the room is available for booking
    public boolean isAvailable() {
        return status.equalsIgnoreCase("Available");
    }

    // Marks the room as booked
    public void bookRoom() {
        this.status = "Booked";
    }

    // Mark the room as available again (after cancellation or checkout)
    public void releaseRoom() {
        this.status = "Available";
    }

    // ====== PRINTING =======
    @Override
    public String toString() {
        return "Room " + roomNumber + " | Type: " + type + " | Price: €" + pricePerNight + " | Status: " + status;
    }

}
