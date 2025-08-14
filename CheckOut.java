package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class CheckOut extends JFrame {

    CheckOut() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 1790, 1390);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check-Out");
        label.setBounds(100, 20, 100, 30);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel UserId = new JLabel("Customer Id ");
        UserId.setBounds(30, 80, 150, 30);
        UserId.setFont(new Font("Tahoma", Font.BOLD, 14));
        UserId.setForeground(Color.WHITE);
        panel.add(UserId);

        Choice Customer = new Choice();
        Customer.setBounds(200, 80, 150, 25);
        panel.add(Customer);

        JLabel roomNum = new JLabel("Room Number");
        roomNum.setBounds(30, 130, 150, 30);
        roomNum.setFont(new Font("Tahoma", Font.BOLD, 14));
        roomNum.setForeground(Color.WHITE);
        panel.add(roomNum);

        JLabel RoomNumber = new JLabel();
        RoomNumber.setBounds(200, 130, 150, 30);
        RoomNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        RoomNumber.setForeground(Color.WHITE);
        panel.add(RoomNumber);

        JLabel checkintime = new JLabel("Check-In Time");
        checkintime.setBounds(30, 180, 150, 30);
        checkintime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkintime.setForeground(Color.WHITE);
        panel.add(checkintime);

        JLabel labelcheckintime = new JLabel();
        labelcheckintime.setBounds(200, 180, 200, 30);
        labelcheckintime.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelcheckintime.setForeground(Color.WHITE);
        panel.add(labelcheckintime);

        JLabel checkouttime = new JLabel("Check-Out Time");
        checkouttime.setBounds(30, 230, 150, 30);
        checkouttime.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkouttime.setForeground(Color.WHITE);
        panel.add(checkouttime);

        Date date = new Date();

        JLabel jLabelcheckouttime = new JLabel("" + date);
        jLabelcheckouttime.setBounds(200, 230, 200, 30);
        jLabelcheckouttime.setFont(new Font("Tahoma", Font.BOLD, 14));
        jLabelcheckouttime.setForeground(Color.WHITE);
        panel.add(jLabelcheckouttime);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                Customer.add(resultSet.getString("number"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        JButton checkOut = new JButton("Check-Out");
        checkOut.setBounds(30, 300, 120, 30);
        checkOut.setForeground(Color.WHITE);
        checkOut.setBackground(Color.BLACK);
        panel.add(checkOut);
        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn cv = new conn();
                    cv.statement.executeUpdate("delete from customer where number ='" + Customer.getSelectedItem() + "'");
                    cv.statement.executeUpdate("update room set availability = 'Available' where roomnumber = '" + RoomNumber.getText() + "'");
                    JOptionPane.showMessageDialog(null, "Done");
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JButton check = new JButton("Check");
        check.setBounds(300, 300, 120, 30);
        check.setForeground(Color.WHITE);
        check.setBackground(Color.BLACK);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try {
                    ResultSet resultSet = c.statement.executeQuery("select * from customer where number ='" + Customer.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        RoomNumber.setText(resultSet.getString("room"));
                        labelcheckintime.setText(resultSet.getString("checkintime"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });
        setUndecorated(true);
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image image = imageIcon.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imglabel = new JLabel(imageIcon1);
        imglabel.setBounds(550,150,200,200);
        panel.add(imglabel);


        // Back button implementation
        JButton back = new JButton("Back");
        back.setBounds(170, 300, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                setVisible(false);
                dispose(); // Completely close the frame
            }
        });

        setLayout(null);
        setSize(800, 400);
        setLocation(500, 210);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}
