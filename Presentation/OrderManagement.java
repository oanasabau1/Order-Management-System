package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for managing the order management system.
 */
public class OrderManagement implements ActionListener {
    private JFrame frame;
    private JPanel panel, clientPanel, ordersPanel, productsPanel;
    private JLabel title;
    private JButton clientButton, productButton, ordersButton;

    public OrderManagement() {
        frame = new JFrame("Order Management System");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridLayout(4, 1));
        clientPanel = new JPanel();
        clientPanel.setBackground(Color.lightGray);
        ordersPanel = new JPanel();
        ordersPanel.setBackground(Color.lightGray);
        productsPanel = new JPanel();
        productsPanel.setBackground(Color.lightGray);


        title = new JLabel("Order Management System");
        title.setFont(new Font("Garamond", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        clientButton = new JButton("Clients");
        clientButton.setFont(new Font("Garamond", Font.BOLD, 24));
        clientButton.setBackground(Color.white);
        clientButton.setPreferredSize(new Dimension(200, 50));
        productButton = new JButton("Products");
        productButton.setFont(new Font("Garamond", Font.BOLD, 24));
        productButton.setBackground(Color.white);
        productButton.setPreferredSize(new Dimension(200, 50));
        ordersButton = new JButton("Orders");
        ordersButton.setFont(new Font("Garamond", Font.BOLD, 24));
        ordersButton.setBackground(Color.white);
        ordersButton.setPreferredSize(new Dimension(200, 50));
        clientButton.addActionListener(this);
        productButton.addActionListener(this);
        ordersButton.addActionListener(this);

        panel.add(title);
        clientPanel.add(clientButton);
        productsPanel.add(productButton);
        ordersPanel.add(ordersButton);
        panel.add(clientPanel);
        panel.add(productsPanel);
        panel.add(ordersPanel);
        frame.add(panel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clientButton) {
            ClientView View = new ClientView();
        } else if (e.getSource() == productButton) {
            ProductView View = new ProductView();
        } else if (e.getSource() == ordersButton) {
            OrdersView View = new OrdersView();
        }
    }
}
