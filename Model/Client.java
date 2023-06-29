package Model;

/**
 * The Client class represents a client with information such as ID, name, address, and email.
 */
public class Client {
    private int id;
    private String clientName;
    private String clientAddress;
    private String clientEmail;

    /**
     * Constructs a new Client with default values.
     */
    public Client() {
    }

    /**
     * Constructs a new Client with the specified name, address, and email.
     *
     * @param clientName    the name of the client
     * @param clientAddress the address of the client
     * @param clientEmail   the email of the client
     */
    public Client(String clientName, String clientAddress, String clientEmail) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
    }

    /**
     * Constructs a new Client with the specified ID, name, address, and email.
     *
     * @param id            the ID of the client
     * @param clientName    the name of the client
     * @param clientAddress the address of the client
     * @param clientEmail   the email of the client
     */
    public Client(int id, String clientName, String clientAddress, String clientEmail) {
        this.id = id;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
    }

    /**
     * Returns the ID of the client.
     *
     * @return the ID of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the client.
     *
     * @param id the ID of the client
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the client.
     *
     * @return the name of the client
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the name of the client.
     *
     * @param clientName the name of the client
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Returns the address of the client.
     *
     * @return the address of the client
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * Sets the address of the client.
     *
     * @param clientAddress the address of the client
     */
    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    /**
     * Returns the email of the client.
     *
     * @return the email of the client
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * Sets the email of the client.
     *
     * @param clientEmail the email of the client
     */
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    /**
     * Returns a string representation of the Client object.
     *
     * @return a string representation of the Client object
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                '}';
    }
}
