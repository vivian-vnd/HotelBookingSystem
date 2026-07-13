package ObjectOriented;

public class Product implements Priceable {

    // Declaring Fields with encapsulation
    // Private -> only accessible inside the class
    private String name;
    private double price;

    // Static Field = Belongs to the class and not to a single object
    static int productCount = 0;

    // Constructor -> Automatically called with the new keyword
    // Initializes the fields -> Name and Price
    public Product(String name, double price) {
        // this keyword -> highlights the current product
        this.name = name;
        this.price = price;
        productCount++;
    }


    // Getters and Setters
    // Getter for the name field
    public String getName() {
        return this.name;
    }

    public boolean isExpensive() {
        return this.getPrice() > 3.00;
    }

    // We will elimated the use of setting the product name.
    // Once the prodcut is created, the name cannot be changed.
    //  public void setName(String name) {
    //      this.name = name;
    //  }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Invalid price - must be > 0");
        }
    }

    public static int getProductCount() {
        return productCount;
    }

    // @Override
    //public String toString() {
    //    return "Product [name=" + name + ", price=" + price + "]";
    //}

}

