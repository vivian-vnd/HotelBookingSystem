package Exceptions;

public class outOfStockException extends RuntimeException {
    public outOfStockException(String productName) {
        // Mimic I am doing some calculation here and then passing the final value to the parent class
        super(productName + " is out of stock.");
    }
}
