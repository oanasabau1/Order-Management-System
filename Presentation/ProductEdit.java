package Presentation;

import BusinessLogic.ProductBLL;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for editing an existing product.
 */
public class ProductEdit implements ActionListener {
    private JFrame frame;
    private JPanel panel, buttonPanel;
    private JTextField textSearch, textName, textPrice, textStock;
    private JButton search, edit, back;
    private JLabel labelName, labelSearch, labelPrice, labelStock;

    public ProductEdit() {
        frame = new JFrame("Edit Product");
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel=new JPanel();
        textSearch = new JTextField();
        textName = new JTextField();
        textPrice = new JTextField();
        textStock = new JTextField();
        search = new JButton("Search");
        search.setFont(new Font("Garamond", Font.BOLD, 16));
        search.setBackground(Color.white);
        edit = new JButton("Edit");
        edit.setFont(new Font("Garamond", Font.BOLD, 16));
        edit.setBackground(Color.white);
        back=new JButton("Back");
        back.setFont(new Font("Garamond", Font.BOLD, 16));
        back.setBackground(Color.white);
        labelSearch = new JLabel("Enter the Id:");
        labelSearch.setFont(new Font("Garamond", Font.BOLD, 16));
        labelName = new JLabel("Name:");
        labelName.setFont(new Font("Garamond", Font.BOLD, 16));
        labelPrice = new JLabel("Price:");
        labelPrice.setFont(new Font("Garamond", Font.BOLD, 16));
        labelStock = new JLabel("Stock:");
        labelStock.setFont(new Font("Garamond", Font.BOLD, 16));

        panel.add(labelSearch);
        panel.add(textSearch);
        panel.add(labelStock);
        panel.add(textStock);
        panel.add(labelName);
        panel.add(textName);
        panel.add(labelPrice);
        panel.add(textPrice);
        buttonPanel.add(search);
        buttonPanel.add(edit);
        buttonPanel.add(back);
        panel.add(buttonPanel);
        edit.addActionListener(this);
        search.addActionListener(this);
        back.addActionListener(this);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            if (textSearch.getText().equals("")) {
                JOptionPane.showMessageDialog(search, "Please enter the Product's Id");
            } else {
                ProductBLL productBLL = new ProductBLL();
                Product p = productBLL.findProductById(Integer.parseInt(textSearch.getText()));
                textName.setText(p.getProductName());
                textPrice.setText(String.valueOf(p.getPrice()));
                textStock.setText(String.valueOf(p.getStock()));
            }
        }
        else if (e.getSource() == edit) {
            if (textSearch.getText().equals("") || textName.getText().equals("") || textPrice.getText().equals("") || textStock.getText().equals("")) {
                JOptionPane.showMessageDialog(edit, "All the fields must be filled!");
            } else if (Integer.parseInt(textPrice.getText()) <= 0) {
                JOptionPane.showMessageDialog(edit, "Please enter a positive price");
            } else if (Integer.parseInt(textStock.getText()) <= 0) {
                JOptionPane.showMessageDialog(edit, "Please enter a positive stock");
            } else {
                JOptionPane.showMessageDialog(edit, "Product's Info have been modified!");
                ProductBLL productBLL = new ProductBLL();
                Product p = productBLL.findProductById(Integer.parseInt(textSearch.getText()));
                p.setProductName(textName.getText());
                p.setPrice(Integer.parseInt(textPrice.getText()));
                p.setStock(Integer.parseInt(textStock.getText()));
                productBLL.updateProduct(p);
                frame.dispose();
            }
        }
        else if (e.getSource()==back) {
            frame.dispose();
        }
    }
}
