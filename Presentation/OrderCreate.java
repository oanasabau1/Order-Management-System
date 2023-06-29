package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrdersBLL;
import BusinessLogic.ProductBLL;
import Model.Bill;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
/**
 * The view class for creating a new order.
 */
public class OrderCreate implements ActionListener {

    private JFrame frame;
    private JTable clientTable, productTable;
    private JPanel panel, upPanel, bottomPanel, leftPanel, rightPanel;
    private JScrollPane j1, j2;
    private JLabel labelQuantity;
    private JTextField textQuantity;
    private JButton newOrder, backButton;
    private ClientsTable c = new ClientsTable(false);
    private ProductTable p = new ProductTable(false);
    private String[][] clientData, productsData;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private List<Client> clientList;
    private List<Product> productList;

    public OrderCreate() {

        frame = new JFrame("Create an Order");
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setContentPane(panel);

        upPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        newOrder = new JButton("Add Order");
        newOrder.setFont(new Font("Garamond", Font.BOLD, 16));
        newOrder.setBackground(Color.WHITE);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Garamond", Font.BOLD, 16));
        backButton.setBackground(Color.WHITE);

        labelQuantity = new JLabel("Enter the desired quantity:");
        labelQuantity.setFont(new Font("Garamond", Font.BOLD, 16));

        textQuantity = new JTextField(10);

        clientBLL = new ClientBLL();
        clientData = new String[200][4];
        clientList = clientBLL.findAllClient();
        int i = 0;
        for (Client c : clientList) {
            clientData[i][0] = String.valueOf(c.getId());
            clientData[i][1] = c.getClientName();
            clientData[i][2] = c.getClientAddress();
            clientData[i][3] = c.getClientEmail();
            i++;
        }

        productBLL = new ProductBLL();
        productsData = new String[200][4];
        productList = productBLL.findAllProducts();
        int j = 0;
        for (Product p : productList) {
            productsData[j][0] = String.valueOf(p.getId());
            productsData[j][1] = p.getProductName();
            productsData[j][2] = String.valueOf(p.getPrice());
            productsData[j][3] = String.valueOf(p.getStock());
            j++;
        }

        clientTable = c.getTable();
        productTable = p.getTable();

        j1 = new JScrollPane(clientTable);
        j2 = new JScrollPane(productTable);

        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
        leftPanel.add(j1, BorderLayout.CENTER);

        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10));
        rightPanel.add(j2, BorderLayout.CENTER);

        upPanel.add(leftPanel);
        upPanel.add(rightPanel);

        bottomPanel.add(labelQuantity);
        bottomPanel.add(textQuantity);
        bottomPanel.add(newOrder);
        bottomPanel.add(backButton);

        panel.add(upPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Set up action listeners
        newOrder.addActionListener(this);
        backButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void insertBill(String clientName, String productName, double price, String address) {
        ResultSet rs;
        Connection connection;
        Statement statement;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/ordermanagementdb?user=root&password=password&serverTimezone=UTC");
            statement = connection.createStatement();
            statement.execute("call ordermanagementdb.new_procedure('" + clientName + "', '" + productName + "', " + price + ", '" + address + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newOrder) {
            if (clientTable.getSelectedRow() == -1 || productTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(newOrder, "Pick a Client and a Product!");
            } else if (textQuantity.getText().equals("")) {
                JOptionPane.showMessageDialog(newOrder, "Enter a valid quantity!");
            } else {
                int availableStock = Integer.parseInt(productsData[productTable.getSelectedRow()][3]);
                int productID = Integer.parseInt(productsData[productTable.getSelectedRow()][0]);
                int clientID = Integer.parseInt(clientData[clientTable.getSelectedRow()][0]);
                if (availableStock < Integer.parseInt(textQuantity.getText())) {
                    JOptionPane.showMessageDialog(newOrder, "Insufficient stock!");
                } else {
                    OrdersBLL ordersBLL = new OrdersBLL();
                    ProductBLL productBLL = new ProductBLL();
                    ClientBLL clientBLL = new ClientBLL();
                    Orders order = new Orders(clientID, productID, Integer.parseInt(textQuantity.getText()));
                    Orders o = ordersBLL.insertOrders(order);
                    Product p = productBLL.findProductById(productID);
                    Client c = clientBLL.findClientById(clientID);
                    try {
                        Bill bill = new Bill(c.getClientName(), p.getProductName(), p.getPrice(), c.getClientAddress());
                        insertBill(c.getClientName(), p.getProductName(), p.getPrice(), c.getClientAddress());
                        PrintWriter printWriter = new PrintWriter("Bill no. " + o.getId() + ".txt");
                        printWriter.write("The Order with the number " + o.getId() + " has been successfully made!\n\n");
                        printWriter.write("Client: " + c.getClientName() + "; Email: " + c.getClientEmail() + "\n\n");
                        printWriter.write("Total for payment: " + p.getPrice() * Integer.parseInt(textQuantity.getText()) + " USD");
                        printWriter.close();
                        JOptionPane.showMessageDialog(newOrder, "The Order has been done successfully!");
                        p.setStock(p.getStock() - o.getQuantity());
                        productBLL.updateProduct(p);
                        frame.dispose();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if (e.getSource() == backButton) {
            frame.dispose();
        }
    }
}
