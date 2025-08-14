package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.awt.print.*;
import java.util.Properties;

public class HotelBillingSystem extends JFrame implements ActionListener, Printable {
    private JTextField tfName, tfContact, tfAddress, tfRate, tfDuration, tfTotal;
    private JComboBox<String> cbRoomType;
    private JButton btnCalculate, btnSave, btnPrint, btnCancel;
    private JTextArea taInvoice;
    private Point initialClick;

    public HotelBillingSystem() {
        setTitle("Hotel Management System - Billing");
        setUndecorated(true); // Removes default JFrame borders
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Custom positioning
        getContentPane().setBackground(new Color(15, 71, 76));

        // Enable dragging for the undecorated frame
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                setLocation(thisX + xMoved, thisY + yMoved);
            }
        });

        // Header Label
        JLabel lblHeader = new JLabel("GULAB RESIDENCY");
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setBounds(250, 10, 300, 30);
        lblHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lblHeader);

        // Labels and Inputs
        createLabel("Customer Name:", 50, 50);
        tfName = createTextField(550, 50);

        createLabel("Contact Number:", 50, 100);
        tfContact = createTextField(550, 100);

        createLabel("Address:", 50, 150);
        tfAddress = createTextField(550, 150);

        createLabel("Room Type:", 50, 200);
        String[] roomTypes = {"Single", "Double", "Suite"};
        cbRoomType = new JComboBox<>(roomTypes);
        cbRoomType.setBounds(550, 200, 200, 30);
        add(cbRoomType);

        createLabel("Rate per Night:", 50, 250);
        tfRate = createTextField(550, 250);

        createLabel("Duration of Stay (Nights):", 50, 300);
        tfDuration = createTextField(550, 300);

        createLabel("Total Amount:", 50, 350);
        tfTotal = createTextField(550, 350);
        tfTotal.setEditable(false);

        // Buttons
        btnCalculate = createButton("Calculate", 50, 400);
        btnSave = createButton("Save", 250, 400);
        btnPrint = createButton("Print", 450, 400);
        btnCancel = createButton("Clear", 630, 400);

        // Invoice Area
        taInvoice = new JTextArea();
        taInvoice.setEditable(false);
        taInvoice.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(taInvoice);
        scrollPane.setBounds(50, 450, 700, 200);
        add(scrollPane);

        // Add Action Listeners
        btnCalculate.addActionListener(this);
        btnSave.addActionListener(this);
        btnPrint.addActionListener(this);
        btnCancel.addActionListener(this);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 300, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(label);
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 200, 30);
        add(textField);
        return textField;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 120, 30);
        add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            calculateTotal();
        } else if (e.getSource() == btnSave) {
            saveInvoice();
        } else if (e.getSource() == btnPrint) {
            printInvoice();
        } else if (e.getSource() == btnCancel) {
            clearFields();
        }
    }

    private void calculateTotal() {
        try {
            double rate = Double.parseDouble(tfRate.getText());
            int duration = Integer.parseInt(tfDuration.getText());
            double total = rate * duration;
            tfTotal.setText(String.format("%.2f", total));

            taInvoice.setText("GULAB RESIDENCY\n");
            taInvoice.append("----------------------------\n");
            taInvoice.append("Name: " + tfName.getText() + "\n");
            taInvoice.append("Contact: " + tfContact.getText() + "\n");
            taInvoice.append("Address: " + tfAddress.getText() + "\n");
            taInvoice.append("Room Type: " + cbRoomType.getSelectedItem() + "\n");
            taInvoice.append("Rate: " + tfRate.getText() + "\n");
            taInvoice.append("Duration: " + tfDuration.getText() + " Nights\n");
            taInvoice.append("Total Amount: ₹" + tfTotal.getText() + "\n\n");
            taInvoice.append("...Thank You visit Again...\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Rate and Duration.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveInvoice() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            props.load(input);

            String dbUrl = props.getProperty("db.url");
            String dbUser = props.getProperty("db.user");
            String dbPassword = props.getProperty("db.password");

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
                String sql = "INSERT INTO invoices (name, contact, address, room_type, rate, duration, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, tfName.getText());
                stmt.setString(2, tfContact.getText());
                stmt.setString(3, tfAddress.getText());
                stmt.setString(4, cbRoomType.getSelectedItem().toString());
                stmt.setDouble(5, Double.parseDouble(tfRate.getText()));
                stmt.setInt(6, Integer.parseInt(tfDuration.getText()));
                stmt.setDouble(7, Double.parseDouble(tfTotal.getText()));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Invoice saved successfully.");
            }
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving invoice to database.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void printInvoice() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void clearFields() {
        tfName.setText("");
        tfContact.setText("");
        tfAddress.setText("");
        cbRoomType.setSelectedIndex(0);
        tfRate.setText("");
        tfDuration.setText("");
        tfTotal.setText("");
        taInvoice.setText("");
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        taInvoice.printAll(g);
        return PAGE_EXISTS;
    }

    public static void main(String[] args) {
        new HotelBillingSystem();
    }
}
