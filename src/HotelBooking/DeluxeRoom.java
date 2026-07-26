package HotelBooking;

public class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber, String status) {
        super(roomNumber, status);
    }

    @Override
    public String getType() {
        return "Deluxe";
    }

    @Override
    public double getPrice() {
        return 120.00;
    }
}