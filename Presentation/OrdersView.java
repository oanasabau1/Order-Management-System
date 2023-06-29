package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for managing orderss.
 */
public class OrdersView implements ActionListener {
    private JFrame frame;
    private JPanel panel, newOrderPanel, showPanel, backPanel;
    private JLabel title;
    private JButton newOrder, show, back;
    public OrdersView() {
        frame=new JFrame("Order");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel=new JPanel(new GridLayout(4, 1));
        panel.setBackground(Color.lightGray);
        newOrderPanel=new JPanel();
        newOrderPanel.setBackground(Color.lightGray);
        showPanel=new JPanel();
        showPanel.setBackground(Color.lightGray);
        backPanel=new JPanel();
        backPanel.setBackground(Color.lightGray);
        title=new JLabel("Order");
        title.setFont(new Font("Garamond", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        newOrder = new JButton("New Order");
        newOrder.setFont(new Font("Garamond", Font.BOLD, 24));
        newOrder.setBackground(Color.white);
        newOrder.setPreferredSize(new Dimension(200, 50));
        show = new JButton("Show");
        show.setFont(new Font("Garamond", Font.BOLD, 24));
        show.setBackground(Color.white);
        show.setPreferredSize(new Dimension(200, 50));
        back = new JButton("Back");
        back.setFont(new Font("Garamond", Font.BOLD, 24));
        back.setBackground(Color.white);
        back.setPreferredSize(new Dimension(100, 40));
        newOrderPanel.add(newOrder);
        showPanel.add(show);
        backPanel.add(back);
        newOrder.addActionListener(this);
        show.addActionListener(this);
        back.addActionListener(this);

        panel.add(title);
        panel.add(newOrderPanel);
        panel.add(showPanel);
        panel.add(backPanel);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newOrder) {
            OrderCreate newOrder = new OrderCreate();
        }
        if (e.getSource() == show) {
            OrdersTable showOrders = new OrdersTable();
        }
        else if (e.getSource() == back) {
            frame.dispose();
        }
    }
}
