package Presentation;

import BusinessLogic.ClientBLL;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for editing an existing client.
 */
public class ClientEdit implements ActionListener {
    private JFrame frame;
    private JPanel panel, buttonPanel;
    private JTextField textSearch, textName, textAddress, textEmail;
    private JButton search, edit, back;
    private JLabel labelName, labelAddress, labelEmail, labelSearch;

    public ClientEdit() {
        frame = new JFrame("Edit Client");
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
        textAddress = new JTextField();
        textEmail = new JTextField();
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
        labelAddress = new JLabel("Address:");
        labelAddress.setFont(new Font("Garamond", Font.BOLD, 16));
        labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Garamond", Font.BOLD, 16));

        panel.add(labelSearch);
        panel.add(textSearch);
        panel.add(labelName);
        panel.add(textName);
        panel.add(labelAddress);
        panel.add(textAddress);
        panel.add(labelEmail);
        panel.add(textEmail);
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
                JOptionPane.showMessageDialog(search, "Enter a Client's Id");
            } else {
                ClientBLL clientBLL = new ClientBLL();
                Client c = clientBLL.findClientById(Integer.parseInt(textSearch.getText()));
                textName.setText(c.getClientName());
                textAddress.setText(c.getClientAddress());
                textEmail.setText(c.getClientEmail());
            }
        }
        else if (e.getSource() == edit) {
            if (textSearch.getText().equals("") || textName.getText().equals("") || textAddress.getText().equals("") || textEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(edit, "All fields must be filled!");
            } else if (textAddress.getText().equals("") || textName.getText().equals("") || textEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(edit, "All fields must be filled!");
            } else if (!textEmail.getText().contains("@") || !textEmail.getText().contains(".")) {
                JOptionPane.showMessageDialog(edit, "Invalid Email");
            } else {
                JOptionPane.showMessageDialog(edit, "Client's Info have been modified");
                ClientBLL clientBLL = new ClientBLL();
                Client c = clientBLL.findClientById(Integer.parseInt(textSearch.getText()));
                c.setClientName(textName.getText());
                c.setClientAddress(textAddress.getText());
                c.setClientEmail(textEmail.getText());
                clientBLL.updateClient(c);
                frame.dispose();
            }
        }
        else if (e.getSource()==back) {
            frame.dispose();
        }
    }
}
