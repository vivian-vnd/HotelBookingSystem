package ObjectOriented;

public class CartItem {
    // CartItem has a Product
    // private Product product;
    private Priceable product;
    private int quantity;

    public CartItem(Priceable product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double totalPrice() {
        return product.getPrice() * quantity;
    }
    @Override
    public String toString() {
        return product.getName() + " x" + quantity + " = €" + this.totalPrice();
    }
}
