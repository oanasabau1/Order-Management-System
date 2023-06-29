package Model;

/**
 * The Bill class represents a bill containing information such as the customer name, product name, price, and address.
 */
public record Bill(String name, String productName, double price, String address) {

    /**
     * Constructs a new Bill record with the specified details.
     *
     * @param name         the customer name
     * @param productName  the product name
     * @param price        the price of the product
     * @param address      the customer address
     */
}
