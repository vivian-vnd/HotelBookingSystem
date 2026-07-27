package HotelBooking;

public class SuiteRoom extends Room {
    private double luxuryTax = 30.00; // Example custom attribute unique to Suites

    public SuiteRoom(String roomNumber, String status) {
        super(roomNumber, status);
    }

    @Override
    public String getType() {
        return "Suite";
    }

    @Override
    public double getPrice() {
        return 200.00 + luxuryTax;
    }
}