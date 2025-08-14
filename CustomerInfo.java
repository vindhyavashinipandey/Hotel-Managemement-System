package Hotel.Management.System;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerInfo extends JFrame {

    CustomerInfo(){
        JPanel panel = new JPanel();
        panel.setBounds(0,5,1690,690);
        panel.setBackground(new Color(3,45,48));
        panel.setLayout(null);
        add(panel);


        JTable table = new JTable();
        table.setBounds(30,40,800,450);
        table.setBackground(new Color(3,45,48));
        table.setForeground(Color.WHITE);
        panel.add(table);


        try {
            conn c = new conn();
            String q = "select * from customer";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel id = new JLabel("ID");
        id.setBounds(35,11,46,14);
        id.setForeground(Color.WHITE);
        id.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(id);

        JLabel number = new JLabel("Number");
        number.setBounds(150,11,100,14);
        number.setForeground(Color.WHITE);
        number.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(number);

        JLabel name = new JLabel("Name");
        name.setBounds(270,11,100,14);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(name);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(360,11,100,14);
        gender.setForeground(Color.WHITE);
        gender.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(gender);

        JLabel country = new JLabel("Country");
        country.setBounds(450,11,100,14);
        country.setForeground(Color.WHITE);
        country.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(country);

        JLabel room = new JLabel("Room");
        room.setBounds(550,11,100,14);
        room.setForeground(Color.WHITE);
        room.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(room);

        JLabel Time = new JLabel("CI Time");
        Time.setBounds(650,11,100,14);
        Time.setForeground(Color.WHITE);
        Time.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(Time);

        JLabel Deposite = new JLabel("Deposite");
        Deposite.setBounds(750,11,100,14);
        Deposite.setForeground(Color.WHITE);
        Deposite.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add( Deposite);

JButton back = new JButton("back");
back.setBounds(450,510,120,30);
back.setBackground(Color.BLACK);
back.setForeground(Color.WHITE);
panel.add(back);
back.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
});
setUndecorated(false);
        setLayout(null);
        setSize(900,600);
        setLocation(500,100);
        setVisible(true);

    }
    public static  void main(String[] args){
        new CustomerInfo();
    }
}
