package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateRoom extends JFrame {

    JTextField textField3, availabilityField, cleanStatusField;
    Choice c;
JComboBox comboBox;
    UpdateRoom() {
        setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(3, 45, 48));
        panel.setLayout(null);
        add(panel);
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Room Status");
        label1.setBounds(124, 11, 222, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel("ID:");
        label2.setBounds(25, 88, 46, 14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        c = new Choice();
        c.setBounds(248, 85, 140, 20);
        panel.add(c);

        populateCustomerChoices();

        JLabel label3 = new JLabel("Room Number:");
        label3.setBounds(25, 129, 107, 14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        textField3 = new JTextField();
        textField3.setBounds(248, 129, 107, 20);
        panel.add(textField3);

        JLabel label4 = new JLabel("Availability:");
        label4.setBounds(25, 174, 97, 14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        //comboBox  = new JComboBox(new String[] {"Availability","Occupied "});
        //comboBox.setBounds(248,174,140,20);
        //comboBox.setBackground(new Color(3,45,48));
        //comboBox.setForeground(Color.WHITE);
        //comboBox.setFont(new Font("Tahoma",Font.PLAIN,14));
      //  panel.add(comboBox);

        availabilityField = new JTextField();
        availabilityField.setBounds(248, 174, 140, 20);
        panel.add(availabilityField);

        JLabel label5 = new JLabel("Clean Status:");
        label5.setBounds(25, 216, 97, 14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

       // comboBox  = new JComboBox(new String[] {"Clean","Dirty "});
        //comboBox.setBounds(248,216,140,20);
        //comboBox.setBackground(new Color(3,45,48));
        //comboBox.setForeground(Color.WHITE);
        //comboBox.setFont(new Font("Tahoma",Font.PLAIN,14));
       // panel.add(comboBox);


        cleanStatusField = new JTextField();
        cleanStatusField.setBounds(248, 216, 140, 20);
        panel.add(cleanStatusField);

        createUpdateButton(panel);
        createBackButton(panel);
        createCheckButton(panel);

        setLayout(null);
        setSize(950, 450);
        setLocation(400, 200);
        setVisible(true);
    }

    private void populateCustomerChoices() {
        try {
            conn C = new conn();
            ResultSet resultSet = C.statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                c.add(resultSet.getString("number"));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createUpdateButton(JPanel panel) {
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
                    String q = c.getSelectedItem();
                    String room = textField3.getText();
                    String availability = availabilityField.getText();
                    String cleanStatus = cleanStatusField.getText();

                    String query = "UPDATE room SET availability = '" + availability + "', cleaning_status = '" + cleanStatus + "' WHERE roomnumber = '" + room + "'";
                    C.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    setVisible(false);
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });
    }

    private void createBackButton(JPanel panel) {
        JButton back = new JButton("Back");
        back.setBounds(168, 378, 89, 23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    private void createCheckButton(JPanel panel) {
        JButton check = new JButton("Check");
        check.setBounds(281, 378, 89, 23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = c.getSelectedItem();
                try {
                    conn C = new conn();
                    ResultSet resultSet = C.statement.executeQuery("SELECT * FROM customer WHERE number = '" + id + "'");
                    if (resultSet.next()) {
                        textField3.setText(resultSet.getString("room"));
                        ResultSet resultSet1 = C.statement.executeQuery("SELECT * FROM room WHERE roomnumber = '" + textField3.getText() + "'");
                        if (resultSet1.next()) {
                            availabilityField.setText(resultSet1.getString("availability"));
                            cleanStatusField.setText(resultSet1.getString("cleanstatus"));
                        }
                        resultSet1.close();
                    }
                    resultSet.close();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UpdateRoom();
            }
        });
    }
}
