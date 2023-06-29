package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The view class for managing products.
 */
public class ProductView implements ActionListener {
    private JFrame frame;
    private JPanel panel, addPanel, editPanel, deletePanel, showPanel, backPanel;
    private JLabel title;
    private JButton add, edit, delete, show, back;
    public ProductView() {
        frame=new JFrame("Product");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel=new JPanel(new GridLayout(6, 1));
        panel.setBackground(Color.lightGray);
        addPanel=new JPanel();
        addPanel.setBackground(Color.lightGray);
        editPanel=new JPanel();
        editPanel.setBackground(Color.lightGray);
        deletePanel=new JPanel();
        deletePanel.setBackground(Color.lightGray);
        showPanel=new JPanel();
        showPanel.setBackground(Color.lightGray);
        backPanel=new JPanel();
        backPanel.setBackground(Color.lightGray);
        title=new JLabel("Product");
        title.setFont(new Font("Garamond", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        add = new JButton("Add");
        add.setFont(new Font("Garamond", Font.BOLD, 24));
        add.setBackground(Color.white);
        add.setPreferredSize(new Dimension(200, 50));
        edit = new JButton("Edit");
        edit.setFont(new Font("Garamond", Font.BOLD, 24));
        edit.setBackground(Color.white);
        edit.setPreferredSize(new Dimension(200, 50));
        delete = new JButton("Delete");
        delete.setFont(new Font("Garamond", Font.BOLD, 24));
        delete.setBackground(Color.white);
        delete.setPreferredSize(new Dimension(200, 50));
        show = new JButton("Show");
        show.setFont(new Font("Garamond", Font.BOLD, 24));
        show.setBackground(Color.white);
        show.setPreferredSize(new Dimension(200, 50));
        back = new JButton("Back");
        back.setFont(new Font("Garamond", Font.BOLD, 24));
        back.setBackground(Color.white);
        back.setPreferredSize(new Dimension(100, 40));
        addPanel.add(add);
        editPanel.add(edit);
        deletePanel.add(delete);
        showPanel.add(show);
        backPanel.add(back);
        add.addActionListener(this);
        edit.addActionListener(this);
        delete.addActionListener(this);
        show.addActionListener(this);
        back.addActionListener(this);

        panel.add(title);
        panel.add(addPanel);
        panel.add(editPanel);
        panel.add(deletePanel);
        panel.add(showPanel);
        panel.add(backPanel);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            ProductAdd addProducts = new ProductAdd();
        }
        else if (e.getSource() == edit) {
            ProductEdit editProducts = new ProductEdit();
        }
        else if (e.getSource() == delete) {
            ProductRemove removeProduct = new ProductRemove();
        }
        else if (e.getSource() == show) {
            ProductTable showProducts = new ProductTable(true);
        }
        else if (e.getSource() == back) {
            frame.dispose();
        }
    }
}
