package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    JButton adminButton, receptionButton, backButton;

    Dashboard() {
        super("GANPATI HOTEL VARANASI");
        setLayout(null);
        setUndecorated(true);
        setSize(1950, 1090);

        // Background Image (Add first to keep it at the back)
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Dashboard.gif"));
        Image bgImage = bgIcon.getImage().getScaledInstance(1950, 1090, Image.SCALE_DEFAULT);
        JLabel backgroundLabel = new JLabel(new ImageIcon(bgImage));
        backgroundLabel.setBounds(0, 0, 1950, 1090);
        backgroundLabel.setLayout(null); // Allow absolute positioning
        add(backgroundLabel); // Add background first

        // Hotel Title Label
        JLabel titleLabel = new JLabel("GULAB RESIDENCY", JLabel.CENTER);
        titleLabel.setBounds(200, 50, 950, 100); // Adjusted position for visibility
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        backgroundLabel.add(titleLabel); // Add to backgroundLabel for proper layering

        // Reception Image
        ImageIcon receptionIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Reception.png"));
        Image receptionImage = receptionIcon.getImage().getScaledInstance(200, 195, Image.SCALE_DEFAULT);
        JLabel receptionLabel = new JLabel(new ImageIcon(receptionImage));
        receptionLabel.setBounds(400, 300, 200, 195);
        backgroundLabel.add(receptionLabel);

        // Admin/Boss Image
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boss.png"));
        Image adminImage = adminIcon.getImage().getScaledInstance(200, 195, Image.SCALE_DEFAULT);
        JLabel adminLabel = new JLabel(new ImageIcon(adminImage));
        adminLabel.setBounds(850, 300, 200, 195);
        backgroundLabel.add(adminLabel);

        // Reception Button
        receptionButton = new JButton("RECEPTION");
        receptionButton.setBounds(425, 510, 140, 30);
        receptionButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        receptionButton.setBackground(new Color(255, 98, 0));
        receptionButton.setForeground(Color.WHITE);
        receptionButton.addActionListener(this);
        backgroundLabel.add(receptionButton); // Add to background

        // Admin Button
        adminButton = new JButton("ADMIN");
        adminButton.setBounds(880, 510, 140, 30);
        adminButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        adminButton.setBackground(new Color(255, 98, 0));
        adminButton.setForeground(Color.WHITE);
        adminButton.addActionListener(this);
        backgroundLabel.add(adminButton); // Add to background

        // Back Button (Newly Added)
        backButton = new JButton("BACK");
        backButton.setBounds(640, 510, 150, 30); // Top-left corner
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.setBackground(new Color(182, 103, 13)); // Red color
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton); // Add to background

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == receptionButton) {
            new Login();
            setVisible(false);
        } else if (e.getSource() == adminButton) {
            new Login2();
            setVisible(false);
        } else if (e.getSource() == backButton) {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                System.exit(0); // Close the application
            }
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
