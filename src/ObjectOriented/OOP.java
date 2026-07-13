package ObjectOriented;

public class OOP {
    public static void main(String[] args) {
        System.out.println("==== 1. Creating Object ==== ");
        // Create a coffee object
        // Creating getters and setters
        // Creating setters with validation
        Product coffee = new Product("Coffee", 2.50);
        System.out.println(coffee.getName());
        System.out.println(coffee.getPrice());
        System.out.println("The product count is " + Product.productCount);
        coffee.setPrice(3.0);
        System.out.println("The new price of the coffee is " + coffee.getPrice());


        // Create a tea object
        Product tea = new Product("Tea", 1.50);
        System.out.println(tea.getName());
        System.out.println(tea.getPrice());
        tea.setPrice(2.0);
        System.out.println("The new price of the tea is " + tea.getPrice());
        System.out.println("The product count is " + Product.productCount);


        // Create a Juice object
        Product juice = new Product("Juice", 3.50);

        // toString() Method which is inbuild in Java
        System.out.println(coffee);
        System.out.println(tea);

        System.out.println("The product count is " + Product.productCount);


        // Inheritance and Method Overriding
        System.out.println("\n==== 2. Inheritance ====");
        DiscountedProduct cake = new DiscountedProduct("Cake", 3.99, 1.00);

        System.out.println(cake);
        System.out.println("Price after discount: €" + cake.getPrice());

        // Interitance and Method Overriding
        System.out.println("\n ==== 3. Interfaces Priceable ====");
        // Product item1 = new Product("Juice", 2.20);
        // DiscountedProduct item2 = new DiscountedProduct("Muffin", 2.50, 0.50);

        Priceable item1 = new Product("Juice", 2.20);
        Priceable item2 = new DiscountedProduct("Muffin", 2.50, 0.50);

        System.out.println(item1.getName() + " cost €" + item1.getPrice());
        System.out.println(item2.getName() + " cost €" + item2.getPrice());

        // Composition -  Cart hold products, discounted product which implements Priceable
        // Cart hold any Class that implements Priceable interface
        System.out.println("\n === 4. Composition ====");
        Cart cart = new Cart();
        cart.add(coffee, 2);
        cart.add(tea, 1);
        cart.add(juice, 1);

        DiscountedProduct cookie = new DiscountedProduct("Cookie", 1.50, 0.25);
        cart.add(cookie, 3);

        cart.print();

        // Polymorphism - Product and Discounted Product have 2 different ways of implementing getPrice
        // At runtime each object will call its own implementation
        System.out.println("\n === 5. Polymorphism ===");
        Priceable[] catalog = {
            new Product("Espresso", 1.80),
            new Product("Steak", 18.00),
            new DiscountedProduct("Wine", 12.00, 4.00),
            new DiscountedProduct("Lobster", 35.00, 5.00)
        };

        for(Priceable product : catalog) {
            System.out.println(" " + product.getName() + " (€" + product.getPrice() + ") -> is expensive: " + product.isExpensive());
        }

    }
}
