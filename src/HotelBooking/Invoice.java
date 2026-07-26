package HotelBooking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Invoice {
    private static int idCounter = 1001; // Generates unique invoice numbers

    private final int invoiceId;
    private final Reservation reservation;
    private final LocalDate issueDate;
    private final double breakfastRatePerNight; // Cost per night for breakfast
    private boolean isPaid;

    // Constructor
    public Invoice(Reservation reservation, double breakfastRatePerNight) {
        this.invoiceId = idCounter++;
        this.reservation = reservation;
        this.issueDate = LocalDate.now();
        this.breakfastRatePerNight = breakfastRatePerNight;
        this.isPaid = false;
    }

    // Getters
    public int getInvoiceId() { return invoiceId; }
    public Reservation getReservation() { return reservation; }

    // Default breakfast rate €15
    public Invoice(Reservation reservation) {
        this(reservation, 10.0);
    }


    // 1. Calculate number of nights stayed
    public long getNumberOfNights() {
        long nights = ChronoUnit.DAYS.between(
                reservation.getCheckInDate(),
                reservation.getCheckOutDate());
        return (nights > 0) ? nights : 1; // Default to 1 night if check-in & check-out are same day
    }

    // 2. Calculate room cost: (price per night) * (number of nights)
    public double calculateRoomTotal() {
        return reservation.getRoom().getPricePerNight() * getNumberOfNights();
    }

    // 3. Calculate breakfast cost: (breakfast rate) * (number of nights) if selected
    public double calculateBreakfastTotal() {
        if (reservation.isBreakfastIncluded()) {
            return breakfastRatePerNight * getNumberOfNights();
        }
        return 0.0;
    }

    // 4. Calculate subtotal / total price
    public double calculateTotalPrice() {
        return calculateTotalPrice() + calculateBreakfastTotal();
    }

    // 5.Mark invoice as paid
    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    // 6.Print the receipt
    public void printReceipt() {
        System.out.println("\n========================================");
        System.out.println("            HOTEL RECEIPT               ");
        System.out.println("========================================");
        System.out.println("Invoice ID:      " + invoiceId);
        System.out.println("Date:            " + issueDate);
        System.out.println("Status:          " + (isPaid ? "PAID" : "UNPAID"));
        System.out.println("----------------------------------------");
        System.out.println("Guest ID:        " + reservation.getGuest().getId());
        System.out.println("Room Number:     " + reservation.getRoom().getRoomNumber());
        System.out.println("Room Type:       " + reservation.getRoom().getType());
        System.out.println("Price / Night:   €" + reservation.getRoom().getPricePerNight());
        System.out.println("Check-In:        " + reservation.getCheckInDate());
        System.out.println("Check-Out:       " + reservation.getCheckOutDate());
        System.out.println("Nights Stayed:   " + getNumberOfNights());
        System.out.println("----------------------------------------");
        System.out.println("Room Charge:     €" + calculateRoomTotal());

        if (reservation.isBreakfastIncluded()) {
            System.out.println("Breakfast Fee:   €" + calculateBreakfastTotal() + " (€" + breakfastRatePerNight + "/night)");
        } else {
            System.out.println("Breakfast Fee:   €0.0 (Not Included)");
        }

        System.out.println("----------------------------------------");
        System.out.println("TOTAL PRICE:     €" + calculateTotalPrice());
        System.out.println("========================================\n");
    }


}