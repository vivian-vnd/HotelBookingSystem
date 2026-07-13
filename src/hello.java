public class hello {
    public static void main(String[] args) {
        // variables and datatypes
        int quantity = 5;
        double price = 3.5; //double should have point/decimal
        String productName = "apple";
        boolean inStock = true;

        // Operators and Output
        double total = quantity * price;
        System.out.println("Product Name: " + productName);

        // If else > conditions or branches
        if (total > 5.0 && quantity > 3) {
            double discount = total * 0.10;
            System.out.printf("10%% discount applied to %s: -%.2f%n", productName, discount);
        } else {
            System.out.println("No discount applied");
        }

        // Switch: Menu Selection
        int choice = 2;
        switch (choice) {
            case 1 -> System.out.println("View Product");
            case 2 -> System.out.println("Add to carts");
            case 3 -> System.out.println("Checkout");
            default -> System.out.println("Invalid choice");
        }
    }
}
