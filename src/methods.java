public class methods {

    //1. VOID Methods  - no return value
    public static void greet(String name) {
        System.out.println("Hello " + name + "!");
    }

    // 2. Return Value Method
    public static int add(int a, int b, int c) {
        return a + b;
    }

    // 3. Method with multiple parameters
    public static double calculateTotal(double price, int quantity) {
        return price * quantity;
    }

    // 4. Method overloading
    public static double calculateTotal(double price, int quantity, double discount) {
        double total = price * quantity;
        return total - discount;
    }

    public static double calculateTotal(double price, double quantity, double discount) {
        double total = price * quantity;
        return total - discount;
    }

    // 5. Method Calling Method
    public static void printReciept(String item, double price, int quantity) {
        double total = calculateTotal(price, quantity); // Method defined previously
        System.out.println(item + " x" + quantity + " -> € " + total);
    }


    public static void main(String[] args) {
        System.out.println("====== Void Methods ======");
        greet("Alice");
        greet("Bob");

        System.out.println("\n====== Return Value Methods ======");
        int result = add(20,20,7);
        System.out.println("20 + 20 + 7 = " + result);

        System.out.println("\n====== Multiple Parameters Methods ======");
        double total = calculateTotal(2.50,3);
        System.out.println("2.50 + 3.50 = €" + total);

        System.out.println("\n====== Overloading Parameter Methods ======");
        double totalWithoutDiscount = calculateTotal(2.50,3);
        double totalWithDiscount = calculateTotal(2.50,3,1);
        System.out.println("Without Discount €" +  totalWithoutDiscount);
        System.out.println("With Discount €" +  totalWithDiscount);

        System.out.println("\n====== Method Calling Methods ======");
        printReciept("Coffee", 2.50,2);
    }
}
