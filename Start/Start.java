package Start;

import Presentation.OrderManagement;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * The starting point of the application.
 */
public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args the command line arguments
     * @throws SQLException if a database access error occurs
     */
    public static void main(String[] args) throws SQLException {
        OrderManagement orderManagementSystem = new OrderManagement();
    }
}
