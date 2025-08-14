package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin extends JFrame implements ActionListener {
    JButton AdminRoomAnalysis, add_Employee, add_Room, add_Driver, FinancialRecords, logout, back, EmployeeInfo;

    admin() {

        JLabel label4 = new JLabel(" GULAB RESIDENCY ");
        label4.setBounds(430,0,560,150);
        label4.setFont(new Font("Tahoma",Font.BOLD,40));
        label4.setForeground(Color.WHITE);
        add(label4);
        // Buttons
        add_Employee = new JButton("ADD EMPLOYEE");
        add_Employee.setBounds(1080, 380, 200, 30);
        add_Employee.setBackground(Color.WHITE);
        add_Employee.setForeground(Color.BLACK);
        add_Employee.setFont(new Font("Tahoma", Font.BOLD, 15));
        add_Employee.addActionListener(this);
        add(add_Employee);

        EmployeeInfo = new JButton("EMPLOYEE INFO");
        EmployeeInfo.setBounds(1080, 540, 200, 30);
        EmployeeInfo.setBackground(Color.WHITE);
        EmployeeInfo.setForeground(Color.BLACK);
        EmployeeInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
        EmployeeInfo.addActionListener(this);
        add(EmployeeInfo);

        add_Room = new JButton("ADD ROOM");
        add_Room.setBounds(250, 380, 200, 30);
        add_Room.setBackground(Color.WHITE);
        add_Room.setForeground(Color.BLACK);
        add_Room.setFont(new Font("Tahoma", Font.BOLD, 15));
        add_Room.addActionListener(this);
        add(add_Room);

        add_Driver = new JButton("ADD DRIVER");
        add_Driver.setBounds(250, 540, 200, 30);
        add_Driver.setBackground(Color.WHITE);
        add_Driver.setForeground(Color.BLACK);
        add_Driver.setFont(new Font("Tahoma", Font.BOLD, 15));
        add_Driver.addActionListener(this);
        add(add_Driver);

        FinancialRecords = new JButton("FINANCIAL RECORDS");
        FinancialRecords.setBounds(250, 200, 200, 30);
        FinancialRecords.setBackground(Color.WHITE);
        FinancialRecords.setForeground(Color.BLACK);
        FinancialRecords.setFont(new Font("Tahoma", Font.BOLD, 15));
        FinancialRecords.addActionListener(this);
        add(FinancialRecords);

        AdminRoomAnalysis = new JButton("Room Analysis ");
        AdminRoomAnalysis.setBounds(1080, 200, 200, 30);
        AdminRoomAnalysis.setBackground(Color.WHITE);
        AdminRoomAnalysis.setForeground(Color.BLACK);
        AdminRoomAnalysis.setFont(new Font("Tahoma", Font.BOLD, 15));
        AdminRoomAnalysis.addActionListener(this);
        add(AdminRoomAnalysis);


        logout = new JButton("Logout");
        logout.setBounds(600, 600, 95, 30);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.setFont(new Font("Tahoma", Font.BOLD, 15));
        logout.addActionListener(this);
        add(logout);

        back = new JButton("Back");
        back.setBounds(700, 600, 95, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.addActionListener(this);
        add(back);

        setUndecorated(true);

        // Icons
        addIcon("/icon/cash1.jpg", 70, 160, 100, 100);
        addIcon("/icon/employees.png", 900, 500, 100, 100);
        addIcon("/icon/addemp.png", 900, 330, 120, 120);
        addIcon("/icon/room.png", 70, 500, 100, 100);
        addIcon("/icon/room.png", 900, 160, 100, 100);
        addIcon("/icon/driver.png", 70, 330, 100, 100);
        addBackgroundImage("/icon/trail6.jpg", -330, 0, 1800, 780);

        getContentPane().setBackground(new Color(3, 45, 48));
        setLayout(null);
        setSize(1950, 1090);
        setVisible(true);
    }

    // Utility method to add icons
    private void addIcon(String path, int x, int y, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(path));
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            JLabel label = new JLabel(new ImageIcon(img));
            label.setBounds(x, y, width, height);
            add(label);
        } catch (Exception e) {
            System.out.println("Error loading image: " + path);
        }
    }

    // Utility method to add background image
    private void addBackgroundImage(String path, int x, int y, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(path));
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            JLabel label = new JLabel(new ImageIcon(img));
            label.setBounds(x, y, width, height);
            add(label);
        } catch (Exception e) {
            System.out.println("Error loading background image: " + path);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == FinancialRecords) {
            new FinancialRecords();
        } else if (e.getSource() == EmployeeInfo) {
            new Employee();
        }


        else if (e.getSource() == add_Employee) {
            new AddEmployee();
        } else if (e.getSource() == add_Room) {
            new AddRoom();
        } else if (e.getSource() == add_Driver) {
            new addDriver();
        }else if (e.getSource() == AdminRoomAnalysis) {
            new AdminRoomAnalysis();
        }
        else if (e.getSource() == logout) {
            System.exit(102);
        } else if (e.getSource() == back) {
            new Dashboard();
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new admin();
    }
}
