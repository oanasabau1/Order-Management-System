package Model;

/**
 * The Product class represents a product with information such as ID, name, price, and stock.
 */
public class Product {
    private int id;
    private String productName;
    private int price;
    private int stock;

    /**
     * Constructs a new Product with default values.
     */
    public Product() {
    }

    /**
     * Constructs a new Product with the specified name, price, and stock.
     *
     * @param name   the name of the product
     * @param price  the price of the product
     * @param stock  the stock quantity of the product
     */
    public Product(String name, int price, int stock) {
        this.productName = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructs a new Product with the specified ID, name, price, and stock.
     *
     * @param id            the ID of the product
     * @param productName   the name of the product
     * @param price         the price of the product
     * @param stock         the stock quantity of the product
     */
    public Product(int id, String productName, int price, int stock) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Returns the ID of the product.
     *
     * @return the ID of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id the ID of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the name of the product.
     *
     * @param productName the name of the product
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns the stock quantity of the product.
     *
     * @return the stock quantity of the product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the product.
     *
     * @param stock the stock quantity of the product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns a string representation of the Product object.
     *
     * @return a string representation of the Product object
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
