package Exceptions;

public class Exceptions {

    // Basic Try Catch
    static void divide(int a, int b) {
        try {
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
    }

    //
    static double parsePrice(String input) {
        // "3.99" -> String \ 3.99 -> double
        // "abc" -> NumberFormatException
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("is not a valid price. Therefore returning 0");
            return 0;
        }
    }

    // ArrayIndexOutOfBoundException
    static void getMenuItem(String[] menu, int index) {
        try {
            System.out.println("Item: " + menu[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No item at position: " + index + ". Menu only has " + menu.length + " items.");
        }
    }

    // Multiple catch blocks
    // To throw an error
    static double validatePrice(String input) {
        try {
            double value = Double.parseDouble(input);
            if (value < 0) {
                throw new IllegalArgumentException("Price cannot be negative.");
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println("is not a valid price. Therefore returning 0");
            return 0;
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Failed." + e.getMessage());
            return 0;
        }
    }

    // Finally Block
    // Put card in, attempt withdrawal
    // not enough balance
    // gives us money if balance is enough
    // Card is ejected back -> (Finally)
    static void withdraw(double balance, double amount) {
        System.out.println("Card Is Inserted. ");
        try {
            if (amount > balance) {
                throw new IllegalArgumentException("Not enough balance.");
            }
            System.out.println("Dispensing the amount €" + amount + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Transaction Failed." + e.getMessage());
        } finally {
            System.out.println("Card is Ejected.");
        }
    }

    // Custom Exceptions
    static void orderItem(String name, int stock) {
        if(stock == 0) {
            throw new outOfStockException(name);
        }
        System.out.println("Product ordered: " + name + ".");
    }

    public static void main(String[] args) {
        System.out.println("=== Exception Handling ====");

        // 1. Divide by zero
        divide(10, 2);
        divide(10, 0);

        // 2. Parsing invalid input
        System.out.println("\n ==== Number Format Exception ====");
        double result1 = parsePrice("3.99");
        double result2 = parsePrice("abc");

        System.out.println("Correct Parsed Value: " + result1);
        System.out.println("Incorrect Parsed Value: " + result2);

        // 3. Array out of bounds
        System.out.println("\n ==== Array out of bound Exception ====");
        String[] menu = {
                "Coffee",
                "Tee",
                "Juice"
        };

        getMenuItem(menu, 2);
        getMenuItem(menu, 4);

        // 4. Multiple Catch blocks
        System.out.println("\n ==== Multiple Catch Blocks ====");
        double result3 = validatePrice("9.99");
        double result4 = validatePrice("abc");
        double result5 = validatePrice("-1.20");

        System.out.println("Correct Validation: " + result3);
        System.out.println("Incorrect Validation: " + result4);
        System.out.println("Incorrect Validation: " + result5);

        // 5. Finally
        System.out.println("\n ==== Finally ====");
        withdraw(100.00, 50.00);
        withdraw(100.00, 150.00);

        // 6. Custom Exceptions
        System.out.println("\n ==== Custom Exception ====");
        try {
            orderItem("Cake", 3);
            orderItem("Muffin", 0);
        } catch(outOfStockException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
