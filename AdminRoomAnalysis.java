package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminRoomAnalysis extends JFrame {
    private JLabel totalRoomsLabel, bookedRoomsLabel, availableRoomsLabel;
    private JButton backButton;
    private JCheckBox showBarGraphCheckBox; // Check Box to show/hide the Bar Graph
    private JPanel barGraphPanel; // Panel that holds the bar graph

    // Variables for room data
    private int totalRooms, bookedRooms, availableRooms;

    public AdminRoomAnalysis() {
        // Set up JFrame
        setTitle("Admin - Room Booking Analysis");
        setSize(1000, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        // Set background color
        getContentPane().setBackground(new Color(3, 45, 48)); // Set background for content pane
        setLayout(new BorderLayout());  // Use BorderLayout for more flexibility

        // Create a panel to hold the labels and set the layout
        JPanel dataPanel = new JPanel();
        dataPanel.setBackground(new Color(3, 45, 48)); // Set panel background color
        dataPanel.setLayout(new GridLayout(4, 1, 10, 10)); // GridLayout for arranging labels

        // Labels to display data
        totalRoomsLabel = new JLabel(" Total Rooms :    Loading...");
        bookedRoomsLabel = new JLabel(" Booked Rooms :    Loading...");
        availableRoomsLabel = new JLabel(" Available Rooms :    Loading...");

        // Set label font and color for better visibility
        totalRoomsLabel.setForeground(Color.WHITE);
        bookedRoomsLabel.setForeground(Color.WHITE);
        availableRoomsLabel.setForeground(Color.WHITE);

        // Set font for labels
        totalRoomsLabel.setFont(new Font("Serif", Font.BOLD, 40));
        bookedRoomsLabel.setFont(new Font("Serif", Font.BOLD, 40));
        availableRoomsLabel.setFont(new Font("Serif", Font.BOLD, 40));

        // Add labels to the data panel
        dataPanel.add(totalRoomsLabel);
        dataPanel.add(bookedRoomsLabel);
        dataPanel.add(availableRoomsLabel);

        // Create the CheckBox to show/hide the Bar Graph
        showBarGraphCheckBox = new JCheckBox("Show Bar Graph");
        showBarGraphCheckBox.setFont(new Font("Serif", Font.BOLD, 20));
        showBarGraphCheckBox.setBackground(new Color(3, 45, 48));
        showBarGraphCheckBox.setForeground(Color.WHITE);
        showBarGraphCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle the visibility of the bar graph
                toggleBarGraph();
            }
        });

        dataPanel.add(showBarGraphCheckBox);

        // Create the Back button and position it at the bottom of the frame
        backButton = new JButton("Back");
        backButton.setFont(new Font("Serif", Font.BOLD, 20));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        // Add action listener to the Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window and return to the previous window or main menu
                dispose();  // Dispose of the current window
                new admin(); // Assuming AdminMainMenu is the class for the admin's main menu
            }
        });

        // Add the data panel and back button to the main frame
        add(dataPanel, BorderLayout.WEST);

        // Add the back button to a panel at the bottom of the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(4, 68, 73)); // Set panel background color
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);  // Add the back button to this panel

        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize the bar graph panel but don't add it yet
        barGraphPanel = new RoomBarGraph();

        // Fetch data from the database
        fetchRoomData();

        setVisible(true);
    }

    private void fetchRoomData() {
        Connection con = null;
        try {
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/hotelMs";
            String user = "root";
            String password = "abhi@123@";
            con = DriverManager.getConnection(url, user, password);

            // SQL Queries
            Statement stmt = con.createStatement();

            // Total Rooms
            String totalRoomsQuery = "SELECT COUNT(*) AS total_rooms FROM room";
            ResultSet rs = stmt.executeQuery(totalRoomsQuery);
            if (rs.next()) totalRooms = rs.getInt("total_rooms");

            // Booked Rooms
            String bookedRoomsQuery = "SELECT COUNT(*) AS booked_rooms FROM room WHERE availability = 'Occupied'";
            rs = stmt.executeQuery(bookedRoomsQuery);
            if (rs.next()) bookedRooms = rs.getInt("booked_rooms");

            // Available Rooms
            availableRooms = totalRooms - bookedRooms;

            // Update labels
            totalRoomsLabel.setText("Total Rooms :  " + totalRooms);
            bookedRoomsLabel.setText("Booked Rooms :  " + bookedRooms);
            availableRoomsLabel.setText("Available Rooms :  " + availableRooms);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void toggleBarGraph() {
        // Remove the existing bar graph (if any) and add it again based on checkbox state
        if (showBarGraphCheckBox.isSelected()) {
            add(barGraphPanel, BorderLayout.CENTER);
        } else {
            remove(barGraphPanel);
        }
        revalidate(); // Revalidate the frame after adding/removing components
        repaint(); // Repaint the frame
    }

    // Custom JPanel to draw the bar graph
    class RoomBarGraph extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // If the CheckBox is selected, display the Bar Graph
            if (showBarGraphCheckBox.isSelected()) {
                // Get the width and height of the panel
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                setBackground(new Color(3, 45, 48)); // Match background color

                // Set the bar width and spacing
                int barWidth = 90;
                int spacing = 50;

                // Calculate the height ratio for each bar
                int maxRooms = totalRooms; // Max value for scaling the graph
                int bookedBarHeight = (int) ((double) bookedRooms / maxRooms * panelHeight);
                int availableBarHeight = (int) ((double) availableRooms / maxRooms * panelHeight);
                int totalBarHeight = (int) ((double) totalRooms / maxRooms * panelHeight);

                // Draw the bars
                g.setColor(Color.RED);  // Booked Rooms
                g.fillRect(200, panelHeight - bookedBarHeight - 20, barWidth, bookedBarHeight);
                g.setColor(Color.WHITE);  // Available Rooms
                g.fillRect(350, panelHeight - availableBarHeight - 20, barWidth, availableBarHeight);
                g.setColor(Color.YELLOW);  // Total Rooms
                g.fillRect(500, panelHeight - totalBarHeight - 20, barWidth, totalBarHeight);

                // Add labels for each bar
                g.setColor(Color.WHITE);
                g.setFont(new Font("Serif", Font.BOLD, 20)); // Set a font for the labels
                g.drawString("Booked", 220, panelHeight - 5);
                g.drawString("Available", 370, panelHeight - 5);
                g.drawString("Total", 520, panelHeight - 5);
            }
        }
    }

    public static void main(String[] args) {
        new AdminRoomAnalysis();
    }
}
