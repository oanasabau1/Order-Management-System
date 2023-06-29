package Model;

/**
 * The Orders class represents an order with information such as ID, client ID, product ID, and quantity.
 */
public class Orders {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;

    /**
     * Constructs a new Orders object with default values.
     */
    public Orders() {
    }

    /**
     * Constructs a new Orders object with the specified client ID, product ID, and quantity.
     *
     * @param clientId   the ID of the client placing the order
     * @param productId  the ID of the product being ordered
     * @param quantity   the quantity of the product being ordered
     */
    public Orders(int clientId, int productId, int quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Constructs a new Orders object with the specified ID, client ID, product ID, and quantity.
     *
     * @param id         the ID of the order
     * @param clientId   the ID of the client placing the order
     * @param productId  the ID of the product being ordered
     * @param quantity   the quantity of the product being ordered
     */
    public Orders(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Returns the ID of the order.
     *
     * @return the ID of the order
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id the ID of the order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the ID of the client placing the order.
     *
     * @return the ID of the client placing the order
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets the ID of the client placing the order.
     *
     * @param clientId the ID of the client placing the order
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Returns the ID of the product being ordered.
     *
     * @return the ID of the product being ordered
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the ID of the product being ordered.
     *
     * @param productId the ID of the product being ordered
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Returns the quantity of the product being ordered.
     *
     * @return the quantity of the product being ordered
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product being ordered.
     *
     * @param quantity the quantity of the product being ordered
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the Orders object.
     *
     * @return a string representation of the Orders object
     */
    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
