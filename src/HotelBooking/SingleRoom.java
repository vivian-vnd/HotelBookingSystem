package HotelBooking;

public class SingleRoom extends Room {

    public SingleRoom(String roomNumber, String status) {
        super(roomNumber, status);
    }

    @Override
    public String getType() {
        return "Single";
    }

    @Override
    public double getPrice() {
        return 50.00;
    }
}