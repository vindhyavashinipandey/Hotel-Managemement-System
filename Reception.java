package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Reception extends JFrame {

    Reception() {
        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(280, 5, 1238, 820);
        panel.setBackground(new Color(3, 45, 48));
        add(panel);

        // Button Panel
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5, 5, 270, 820);
        panel1.setBackground(new Color(3, 45, 48));
        add(panel1);
        setUndecorated(true);

        // Background Image with Overlay Text
        ImageIcon i111 = new ImageIcon(ClassLoader.getSystemResource("icon/trail7.jpg"));
        Image i22 = i111.getImage().getScaledInstance(1020, 800, Image.SCALE_DEFAULT);
        ImageIcon imageIcon111 = new ImageIcon(i22);
        JLabel label11 = new JLabel(imageIcon111);
        label11.setBounds(40, 20, 1020, 800);
        panel.add(label11);

        // Adding "GANPATI HOTEL" Text Over the Image
        JLabel titleLabel = new JLabel("GULAB RESIDENCY", JLabel.CENTER);
        titleLabel.setBounds(-20, 50, 1000, 100);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        label11.add(titleLabel);
        label11.setLayout(null); // Set layout manager to null for proper positioning

        // Logout Button
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(400, 570, 130, 40);
        btnLogout.setForeground(Color.BLACK);
        btnLogout.setBackground(Color.WHITE);
        label11.add(btnLogout);

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(550, 570, 130, 40);
        btnBack.setForeground(Color.BLACK);
        btnBack.setBackground(Color.WHITE);
        label11.add(btnBack);

        // Hyperlink-style Button to Open Website
        JButton btnclick = new JButton("<HTML><U>Booking Link</U></HTML>"); // Underlined hyperlink text
        btnclick.setBounds(700, 570, 200, 40);
        btnclick.setForeground(Color.BLUE); // Blue text like a hyperlink
        btnclick.setBackground(Color.WHITE);
        btnclick.setBorderPainted(false); // No border for hyperlink effect
        btnclick.setFocusPainted(false); // No focus border
        btnclick.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        label11.add(btnclick);

        // Action to open the website
        btnclick.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new java.net.URI("https://www.booking.com/hotel/in/gulab-residency-home-stay.html"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Failed to open the link. Please try again.");
            }
        });

        // Logout Button Action
        btnLogout.addActionListener(e -> {
            setVisible(false); // Close the Reception window
            System.exit(0);    // Exit the application
        });

        // Back Button Action
        btnBack.addActionListener(e -> {
            setVisible(false); // Close current window
            new Dashboard();   // Open the Dashboard window
        });

        // Image on Button Panel
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icon/login.jpg"));
        Image i2 = i11.getImage().getScaledInstance(260, 370, Image.SCALE_DEFAULT);
        ImageIcon imageIcon11 = new ImageIcon(i2);
        JLabel label1 = new JLabel(imageIcon11);
        label1.setBounds(5, 380, 260, 370);
        panel1.add(label1);

        // Navigation Buttons
        addButton(panel1, "NEW CUSTOMER FORM", 10, e -> new NewCustomer());
        addButton(panel1, "Room", 50, e -> new Room());
        addButton(panel1, "Customer Info", 90, e -> new CustomerInfo());
        addButton(panel1, "Billing System", 130, e -> new HotelBillingSystem());
        addButton(panel1, "CHECK Out", 170, e -> new CheckOut());
        addButton(panel1, "Update Financial Reports", 210, e -> new FinancialReports());
        addButton(panel1, "Update Room Status", 250, e -> new UpdateRoom());
        addButton(panel1, "Pick up Service", 290, e -> new PickUp());
        addButton(panel1, "SearchRoom", 330, e -> new SearchRoom());

        // Window Properties
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1950, 1090);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    /**
     * Helper method to add buttons to the button panel.
     *
     * @param panel  The panel where the button will be added.
     * @param text   The text of the button.
     * @param y      The vertical position of the button.
     * @param action The action to perform when the button is clicked.
     */
    private void addButton(JPanel panel, String text, int y, ActionListener action) {
        JButton button = new JButton(text);
        button.setBounds(30, y, 200, 30);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        panel.add(button);
        button.addActionListener(action);
    }

    public static void main(String[] args) {
        new Reception();
    }
}
