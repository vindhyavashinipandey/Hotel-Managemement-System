package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecords extends JFrame implements ActionListener {

    // Define the text fields and buttons
    JTextField textFieldDate, textName, textWork, textAmount;
    JButton add, back;

    // JComboBox to list financial records
    JComboBox<String> comboBoxRecords;

    // Constructor
    FinancialRecords() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 1840, 1540);
        panel.setLayout(null);
        panel.setBackground(new Color(3, 45, 48));
        add(panel);

        setLayout(null);
        setLocation(500, 150);
        setSize(850, 550);
        setVisible(true);

        JLabel labelTitle = new JLabel("FINANCIAL RECORDS");
        labelTitle.setBounds(118, 11, 260, 53);
        labelTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelTitle.setForeground(Color.WHITE);
        panel.add(labelTitle);

        // Setting up labels and text fields
        JLabel labelDate = new JLabel("Date");
        labelDate.setBounds(118, 70, 186, 31);
        labelDate.setForeground(Color.WHITE);
        labelDate.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(labelDate);


        JLabel labelName = new JLabel("Name");
        labelName.setBounds(118, 110, 150, 20);
        labelName.setForeground(Color.WHITE);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelName);
        textName = new JTextField();
        textName.setBounds(270, 110, 150, 20);
        panel.add(textName);

        JLabel labelWork = new JLabel("Work");
        labelWork.setBounds(118, 152, 150, 20);
        labelWork.setForeground(Color.WHITE);
        labelWork.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelWork);
        textWork = new JTextField();
        textWork.setBounds(270, 152, 150, 20);
        panel.add(textWork);

        JLabel labelAmount = new JLabel("Amount");
        labelAmount.setBounds(118, 192, 150, 20);
        labelAmount.setForeground(Color.WHITE);
        labelAmount.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(labelAmount);
        textAmount = new JTextField();
        textAmount.setBounds(270, 192, 150, 20);
        panel.add(textAmount);

        // Combo box to list financial records
        comboBoxRecords = new JComboBox<>();
        comboBoxRecords.setBounds(271, 73, 150, 20);
        panel.add(comboBoxRecords);

        // Load financial records into the combo box
        loadFinancialRecords(comboBoxRecords);

        // Add buttons
        JButton checkOut = new JButton("Delete");
        checkOut.setBounds(30, 300, 120, 30);
        checkOut.setForeground(Color.WHITE);
        checkOut.setBackground(Color.BLACK);
        panel.add(checkOut);
        checkOut.addActionListener(this);

        JButton check = new JButton("Check");
        check.setBounds(300, 300, 120, 30);
        check.setForeground(Color.WHITE);
        check.setBackground(Color.BLACK);
        panel.add(check);
        check.addActionListener(this);

        JButton back = new JButton("Back");
        back.setBounds(170, 300, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        panel.add(back);
        back.addActionListener(this);



        ImageIcon imageIcon4 = new ImageIcon(ClassLoader.getSystemResource("icon/cash1.jpg"));
        Image image3 = imageIcon4.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon imageIcon14 = new ImageIcon(image3);
        JLabel label4 = new JLabel(imageIcon14);
        label4.setBounds(570,50,100,100);
        add(label4);


        setLayout(null);
        setSize(800, 400);
        setLocation(500, 210);
        setVisible(true);
    }

    // Load financial records using generics and reflection
    private <T> void loadFinancialRecords(JComboBox<T> comboBox) {
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM FinancialReports");

            List<FinancialReport> reports = mapResultSetToList(resultSet, FinancialReport.class);
            for (FinancialReport report : reports) {
                comboBox.addItem((T) report.date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Map ResultSet to a List of objects using reflection
    private <T> List<T> mapResultSetToList(ResultSet rs, Class<T> clazz) throws SQLException {
        List<T> results = new ArrayList<>();
        try {
            while (rs.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true); // To access private fields
                    field.set(obj, rs.getObject(field.getName()));
                }
                results.add(obj);
            }
        } catch (Exception e) {
            throw new SQLException("Failed to map ResultSet to Object", e);
        }
        return results;
    }

    // Action listener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.getText().equals("Delete")) {
            updateFinancialReport();
        } else if (source.getText().equals("Check")) {
            checkFinancialReport();
        } else if (source.getText().equals("Back")) {
            // Close the window
            setVisible(false);
        }
    }

    // Update Financial Report method
    private void updateFinancialReport() {
        try {
            conn c = new conn();
            String selectedDate = comboBoxRecords.getSelectedItem().toString();
            c.statement.executeUpdate("DELETE FROM FinancialReports WHERE date = '" + selectedDate + "'");
            JOptionPane.showMessageDialog(null, "Record Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check Financial Report method
    private void checkFinancialReport() {
        try {
            conn c = new conn();
            String selectedDate = comboBoxRecords.getSelectedItem().toString();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM FinancialReports WHERE date = '" + selectedDate + "'");
            if (resultSet.next()) {
                textName.setText(resultSet.getString("name"));
                textWork.setText(resultSet.getString("work"));
                textAmount.setText(resultSet.getString("amount"));
            } else {
                JOptionPane.showMessageDialog(null, "No Record Found for the selected date.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new FinancialRecords();
    }
}


class FinancialReport {
    public String date;
    public String name;
    public String work;
    public String amount;
}
