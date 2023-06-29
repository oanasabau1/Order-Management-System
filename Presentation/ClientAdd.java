package Presentation;

import BusinessLogic.ClientBLL;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for adding a new client.
 */
public class ClientAdd implements ActionListener {
    private JPanel panel, buttonPanel;
    private JFrame frame;
    private JTextField textName, textAddress, textEmail;
    private JLabel labelName, labelAddress, labelEmail;
    private JButton add, back;

    public ClientAdd() {
        frame = new JFrame("Client's Info");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        buttonPanel=new JPanel();
        textAddress = new JTextField();
        textName = new JTextField();
        textEmail = new JTextField();
        labelName = new JLabel("Name:");
        labelName.setFont(new Font("Garamond", Font.BOLD, 16));
        labelAddress = new JLabel("Address:");
        labelAddress.setFont(new Font("Garamond", Font.BOLD, 16));
        labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Garamond", Font.BOLD, 16));
        add = new JButton("Add Client");
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
        panel.add(labelAddress);
        panel.add(textAddress);
        panel.add(labelEmail);
        panel.add(textEmail);
        buttonPanel.add(add);
        buttonPanel.add(back);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            if (textAddress.getText().isEmpty() || textName.getText().isEmpty() || textEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(add, "Some fields are empty");
            } else if (!textEmail.getText().contains("@") || !textEmail.getText().contains(".")) {
                JOptionPane.showMessageDialog(add, "Invalid Email!");
            } else {
                JOptionPane.showMessageDialog(add, "Client added successfully!");
                Client client = new Client(textName.getText(), textAddress.getText(), textEmail.getText());
                ClientBLL clientBLL = new ClientBLL();
                clientBLL.insertClient(client);
                frame.dispose();
            }
        }
        else if (e.getSource()==back) {
            frame.dispose();
        }
    }
}
