package ObjectOriented;

// Extends = IS A relationship between classes
public class DiscountedProduct extends Product {

    private double discount; // amount substracted from the price

    // The subclass constructor calls super() to initialize the parents fields first.
    public DiscountedProduct(String name, double price, double discount) {
        super(name, price);
        this.discount = discount;
    }

    // Override replaces the pattern getPrice with this version of the discounted product.
    @Override
    public double getPrice() {
        // Get the price of the product and substract the discount
        return super.getPrice() - discount;
    }

    @Override
    public String toString() {
        return super.getName() + " -> €" + super.getPrice() + " (discounted)";
    }
}
