package Presentation;

import BusinessLogic.ClientBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for deleting an existing client.
 */
public class ClientRemove implements ActionListener {
    private JPanel panel, buttonPanel;
    private JFrame frame;
    private JTextField text;
    private JLabel label;
    private JButton delete, back;

    public ClientRemove() {
        frame = new JFrame("Delete a Client");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        panel = new JPanel(new GridLayout(2, 1));
        buttonPanel=new JPanel();
        text=new JTextField();
        label=new JLabel("Enter an Id");
        label.setFont(new Font("Garamond", Font.BOLD, 20));
        delete = new JButton("Delete Client");
        delete.setFont(new Font("Garamond", Font.BOLD, 16));
        delete.setBackground(Color.white);
        back= new JButton("Back");
        back.setFont(new Font("Garamond", Font.BOLD, 16));
        back.setBackground(Color.white);
        delete.addActionListener(this);
        back.addActionListener(this);


        panel.setLayout(new GridLayout(3, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(label);
        panel.add(text);
        buttonPanel.add(delete);
        buttonPanel.add(back);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            if (text.getText().equals("")) {
                JOptionPane.showMessageDialog(delete, "Invalid Id");
            } else {
                ClientBLL clientBLL = new ClientBLL();
                clientBLL.deleteClient(Integer.parseInt(text.getText()));
                JOptionPane.showMessageDialog(delete, "The client has been deleted successfully!");
                frame.dispose();
            }
        }
        else if (e.getSource()==back) {
            frame.dispose();
        }
    }
}
