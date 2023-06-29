package BusinessLogic;

import BusinessLogic.Validators.EmailValidator;
import BusinessLogic.Validators.Validator;
import Model.Client;
import DataAccess.ClientDAO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The ClientBLL class provides business logic operations for the Client model.
 */
public class ClientBLL {

    private final ClientDAO clientDAO;
    private List<Validator<Client>> validators;

    /**
     * Constructs a new ClientBLL object.
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * Finds a client by ID.
     *
     * @param id the ID of the client to find
     * @return the found client
     * @throws NoSuchElementException if the client with the specified ID is not found
     */
    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            JOptionPane.showMessageDialog(null, "Error: Client not found!");
            throw new NoSuchElementException("The Client with id =" + id + " was not found!");
        }
        return client;
    }

    /**
     * Retrieves the field names of the Client model.
     *
     * @return an array of field names
     */
    public String[] getFieldNames() {
        String[] s = clientDAO.takeFieldNames();
        return s;
    }

    /**
     * Retrieves a list of all clients.
     *
     * @return a two-dimensional array representing the list of clients
     */
    public String[][] getListOfClients() {
        String[][] s = clientDAO.listOfObject(clientDAO.findAll());
        return s;
    }

    /**
     * Inserts a new client.
     *
     * @param x the client to insert
     * @return the inserted client
     */
    public Client insertClient(Client x) {
        for (Validator<Client> v : validators) {
            v.validate(x);
        }
        return clientDAO.insert(x);
    }

    /**
     * Deletes a client by ID.
     *
     * @param clientId the ID of the client to delete
     */
    public void deleteClient(int clientId) {
        clientDAO.delete(clientId);
    }

    /**
     * Retrieves a list of all clients.
     *
     * @return a list of all clients
     */
    public List<Client> findAllClient() {
        return clientDAO.findAll();
    }

    /**
     * Updates a client.
     *
     * @param client the client to update
     * @return the updated client
     */
    public Client updateClient(Client client) {
        return clientDAO.update(client, client.getId());
    }
}
