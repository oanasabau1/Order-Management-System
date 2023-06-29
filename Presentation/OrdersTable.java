package Presentation;

import BusinessLogic.OrdersBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for displaying a table of orders.
 */
public class OrdersTable implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JTable table = new JTable();
    private JPanel buttonPanel;
    private JButton back;

    public OrdersTable() {
        frame = new JFrame("Orders");
        panel = new JPanel();
        frame.setResizable(false);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        OrdersBLL ordersBLL = new OrdersBLL();
        DefaultTableModel defaultTableModel = new DefaultTableModel(ordersBLL.getListOfOrders(), ordersBLL.getFieldNames()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(defaultTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(400);

        back = new JButton("Back");
        back.setFont(new Font("Garamond", Font.BOLD, 16));
        back.setBackground(Color.WHITE);
        back.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.add(back);

        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==back) {
            frame.dispose();
        }
    }

    public JTable getTable() {
        return table;
    }

}
