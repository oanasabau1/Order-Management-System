package Presentation;

import BusinessLogic.ProductBLL;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for adding a new product.
 */
public class ProductAdd implements ActionListener {

    private JPanel panel, buttonPanel;
    private JFrame frame;
    private JTextField textName, textPrice, textStock;
    private JLabel labelName, labelPrice, labelStock;
    private JButton add, back;

    public ProductAdd() {
        frame = new JFrame("Add a Product");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        buttonPanel=new JPanel();
        textPrice = new JTextField();
        textName = new JTextField();
        textStock = new JTextField();
        labelName = new JLabel("Name:");
        labelName.setFont(new Font("Garamond", Font.BOLD, 16));
        labelPrice = new JLabel("Price:");
        labelPrice.setFont(new Font("Garamond", Font.BOLD, 16));
        labelStock = new JLabel("Stock:");
        labelStock.setFont(new Font("Garamond", Font.BOLD, 16));
        add = new JButton("Add Product");
        add.setFont(new Font("Garamond", Font.BOLD, 16));
        add.setBackground(Color.white);
        back= new JButton("Back");
        back.setFont(new Font("Garamond", Font.BOLD, 16));
        back.setBackground(Color.white);
        add.addActionListener(this);
        back.addActionListener(this);

        panel.setLayout(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(labelName);
        panel.add(textName);
        panel.add(labelPrice);
        panel.add(textPrice);
        panel.add(labelStock);
        panel.add(textStock);
        buttonPanel.add(add);
        buttonPanel.add(back);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            if (textPrice.getText().equals("") || textName.getText().equals("") || textStock.getText().equals("")) {
                JOptionPane.showMessageDialog(add, "All the fields must be filled!");
            } else if (Integer.parseInt(textPrice.getText()) <= 0) {
                JOptionPane.showMessageDialog(add, "Please enter a positive amount");
            } else if (Integer.parseInt(textStock.getText()) <= 0) {
                JOptionPane.showMessageDialog(add, "Please enter a negative amount");
            } else {
                JOptionPane.showMessageDialog(add, "The Product has been added successfully!");
                Product product = new Product(textName.getText(), Integer.parseInt(textPrice.getText()), Integer.parseInt(textStock.getText()));
                ProductBLL productBLL = new ProductBLL();
                productBLL.insertProduct(product);
                frame.dispose();
            }
        }
        else if (e.getSource()==back) {
            frame.dispose();
        }
    }
}
