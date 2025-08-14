package Hotel.Management.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
public class FinancialReports extends JFrame implements ActionListener{

    JTextField textFieldDate,TextName,TextWork,TextAmount;
    JButton add,back;
    FinancialReports(){

        JPanel panel = new JPanel();
        panel.setBounds(0,0,1640,2540);
        panel.setLayout(null);
        panel.setBackground(new Color(3,45,48));
        add(panel);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/customer.png"));
        Image image = imageIcon.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imglabel = new JLabel(imageIcon1);
        imglabel.setBounds(550,150,200,200);
        panel.add(imglabel);

        setLayout(null);
        setLocation(300,150);
        setSize(850,550);
        setVisible(true);



        JLabel labelName = new JLabel("FINANCIAL REPORT");
        labelName.setBounds(118,11,260,53);
        labelName.setFont(new Font("Tahoma",Font.BOLD,20));
        labelName.setForeground(Color.WHITE);
        panel.add(labelName);

        JLabel labelDate = new JLabel("Date :");
        labelDate.setBounds(35,111,200,14);
        labelDate.setForeground(Color.WHITE);
        labelDate.setFont(new Font("Tahoma",Font.PLAIN,14));
        panel.add(labelDate);
        textFieldDate = new JTextField();
        textFieldDate.setBounds(271,111,150,20);
        panel.add(textFieldDate);

        JLabel labelname = new JLabel("Name :");
        labelname.setBounds(35,151,200,14);
        labelname.setForeground(Color.WHITE);
        labelname.setFont(new Font("Tahoma",Font.PLAIN,14));
        panel.add(labelname);
        TextName = new JTextField();
        TextName.setBounds(271,151,150,20);
        panel.add(TextName);


        JLabel labelWork = new JLabel("Work :");
        labelWork.setBounds(35,231,200,14);
        labelWork.setForeground(Color.WHITE);
        labelWork.setFont(new Font("Tahoma",Font.PLAIN,14));
        panel.add(labelWork);
        TextWork = new JTextField();
        TextWork.setBounds(271,231,150,20);
        panel.add(TextWork);

        JLabel labelAmount = new JLabel("Amount :");
        labelAmount.setBounds(35,274,200,14);
        labelAmount.setForeground(Color.WHITE);
        labelAmount.setFont(new Font("Tahoma",Font.PLAIN,14));
        panel.add(labelAmount);
        TextAmount = new JTextField();
        TextAmount.setBounds(271,271,150,20);
        panel.add(TextAmount);

        add = new JButton("ADD");
        add.setBounds(100, 430, 120, 30);
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.addActionListener(this); // Add action listener to the button
        panel.add(add);

        back = new JButton("BACK");
        back.setBounds(260, 430, 120, 30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this); // Add action listener to the button
        panel.add(back);
    }

    // Action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String date = textFieldDate.getText();
            String name = TextName.getText();
            String work = TextWork.getText();
            String amount = TextAmount.getText();

            try {
                conn c = new conn();  // Assuming you have a conn class for database connection
                String query = "INSERT INTO FinancialReports (date, name, work, amount) VALUES ('" + date + "', '" + name + "', '" + work + "', '" + amount + "')";
                c.statement.executeUpdate(query);  // Execute the query

                JOptionPane.showMessageDialog(null, "Added Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);  // Close the current window
        }
    }

    public static void main(String[] args) {
        new FinancialReports();
    }
}