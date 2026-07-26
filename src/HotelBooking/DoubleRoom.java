package HotelBooking;

public class DoubleRoom extends Room {

    public DoubleRoom(String roomNumber, String status) {
        super(roomNumber, status);
    }

    @Override
    public String getType() {
        return "Double";
    }

    @Override
    public double getPrice() {
        return 80.00;
    }
}