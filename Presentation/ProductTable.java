package Presentation;

import BusinessLogic.ProductBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for displaying a table of products.
 */
public class ProductTable implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JTable table = new JTable();
    private JPanel buttonPanel;
    private JButton back;

    public ProductTable(boolean showFrame) {
        ProductBLL productBLL = new ProductBLL();
        DefaultTableModel defaultTableModel = new DefaultTableModel(productBLL.getListOfProducts(), productBLL.getFieldNames()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(defaultTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        if (showFrame) {
            show();
        }
    }

    private void show() {
        frame = new JFrame("Products");
        panel = new JPanel();
        frame.setContentPane(panel);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        back = new JButton("Back");
        back.addActionListener(this);
        back.setFont(new Font("Garamond", Font.BOLD, 14));
        back.setBackground(Color.WHITE);
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(back);
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(3).setPreferredWidth(300);

        frame.setVisible(true);
    }

    public JTable getTable() {
        return table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==back){
            frame.dispose();
        }
    }
}
