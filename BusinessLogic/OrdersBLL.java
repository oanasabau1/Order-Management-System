package BusinessLogic;

import Model.Orders;
import DataAccess.OrdersDAO;

import java.util.List;

/**
 * The OrdersBLL class provides business logic operations for the Orders model.
 */
public class OrdersBLL {

    private final OrdersDAO ordersDAO = new OrdersDAO();

    /**
     * Retrieves the field names of the Orders model.
     *
     * @return an array of field names
     */
    public String[] getFieldNames() {
        String[] s = ordersDAO.takeFieldNames();
        return s;
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return a two-dimensional array representing the list of orders
     */
    public String[][] getListOfOrders() {
        String[][] s = ordersDAO.listOfObject(ordersDAO.findAll());
        return s;
    }

    /**
     * Inserts a new order.
     *
     * @param product the order to insert
     * @return the inserted order
     */
    public Orders insertOrders(Orders product) {
        return ordersDAO.insert(product);
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return a list of all orders
     */
    public List<Orders> findAllOrders() {
        return ordersDAO.findAll();
    }
}
