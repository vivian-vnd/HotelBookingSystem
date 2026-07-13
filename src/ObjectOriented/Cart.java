package ObjectOriented;

import java.util.ArrayList;

public class Cart {

    // Cart will have multiple CartItem
    private ArrayList<CartItem> items = new ArrayList<>();

    // Empty Constructor
    // If constructor is empty JAVA will create it automatically for you in Run Time.
     public Cart() {
    }

    public void add(Priceable product, int quantity) {
        // new to create an object of this CartItem
        // To create an object of cart item we need to call the constructor e.g new keyword
        // new CartItem(product, quantity)
        CartItem cartItem = new CartItem(product, quantity);
        items.add(cartItem);
    }

    public double getTotal() {
        double sum = 0;
        for (CartItem item : items) {
            sum += item.totalPrice();
        }
        return sum;
    }

    public void print() {
        System.out.println("==== Cart ===");
        for (CartItem item : items) {
            System.out.println(" " + item);
        }
        System.out.println("Total : €" + this.getTotal());
    }
}
