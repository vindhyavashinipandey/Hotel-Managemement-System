package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateCheck extends JFrame {

    UpdateCheck(){

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 1940, 1490);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Check-In Details");
        label1.setBounds(124, 11, 222, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel("ID:");
        label2.setBounds(25, 88, 46, 14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice c = new Choice();
        c.setBounds(248, 85, 140, 20);
        panel.add(c);

        try {
            conn C = new conn();
            ResultSet resultSet = C.statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                c.add(resultSet.getString("number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number:");
        label3.setBounds(25, 129, 107, 14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField textField3 = new JTextField();
        textField3.setBounds(248, 129, 140, 20);
        panel.add(textField3);

        JLabel label4 = new JLabel("Name:");
        label4.setBounds(25, 174, 97, 14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField textField4 = new JTextField();
        textField4.setBounds(248, 174, 140, 20);
        panel.add(textField4);

        JLabel label5 = new JLabel("Check-in:");
        label5.setBounds(25, 216, 97, 14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField textField5 = new JTextField();
        textField5.setBounds(248, 216, 140, 20);
        panel.add(textField5);

        JLabel label6 = new JLabel("Amount Paid (Rs):");
        label6.setBounds(25, 261, 140, 14);
        label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label6.setForeground(Color.WHITE);
        panel.add(label6);

        JTextField textField6 = new JTextField();
        textField6.setBounds(248, 261, 140, 20);
        panel.add(textField6);

        JLabel label7 = new JLabel("Pending Amount (Rs):");
        label7.setBounds(25, 302, 140, 14);
        label7.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label7.setForeground(Color.WHITE);
        panel.add(label7);

        JTextField textField7 = new JTextField();
        textField7.setBounds(248, 302, 140, 20);
        textField7.setEditable(false);  // Pending amount should be read-only
        panel.add(textField7);

        JButton update = new JButton("Update");
        update.setBounds(56, 378, 89, 23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn C = new conn();
                    String id = c.getSelectedItem();
                    String room = textField3.getText();
                    String name = textField4.getText();
                    String checkInTime = textField5.getText();
                    String amountPaid = textField6.getText();

                    // Calculate pending amount before updating
                    ResultSet resultSet1 = C.statement.executeQuery("select * from room where roomnumber = '"+room+"'");
                    if (resultSet1.next()) {
                        String price = resultSet1.getString("price");
                        int pendingAmount = Integer.parseInt(price) - Integer.parseInt(amountPaid);
                        textField7.setText("" + pendingAmount);
                    }

                    // Update customer details
                    C.statement.executeUpdate("update customer set room = '"+room+"', name = '"+name+"', checkintime = '"+checkInTime+"', deposit = '"+amountPaid+"' where number = '"+id+"'");

                    // Insert or update billing information
                    C.statement.executeUpdate("insert into billing (customer_id, room_number, check_in, total_amount, amount_paid, pending_amount) values ('"+id+"', '"+room+"', '"+checkInTime+"', (select price from room where roomnumber = '"+room+"'), '"+amountPaid+"', '"+textField7.getText()+"') on duplicate key update amount_paid='"+amountPaid+"', pending_amount='"+textField7.getText()+"'");

                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    setVisible(false);
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(168, 378, 89, 23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(281, 378, 89, 23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = c.getSelectedItem();
                String query = "select * from customer where number = '"+id+"'";
                try {
                    conn conn = new conn();
                    ResultSet resultSet = conn.statement.executeQuery(query);
                    while (resultSet.next()) {
                        textField3.setText(resultSet.getString("room"));
                        textField4.setText(resultSet.getString("name"));
                        textField5.setText(resultSet.getString("checkintime"));
                        textField6.setText(resultSet.getString("deposit"));
                    }

                    ResultSet resultSet1 = conn.statement.executeQuery("select * from room where roomnumber = '"+textField3.getText()+"'");
                    if (resultSet1.next()) {
                        String price = resultSet1.getString("price");
                        int pendingAmount = Integer.parseInt(price) - Integer.parseInt(textField6.getText());
                        textField7.setText("" + pendingAmount);
                    }

                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        setLayout(null);
        setSize(950, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
