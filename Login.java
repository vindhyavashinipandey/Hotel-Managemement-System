package Hotel.Management.System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Login extends JFrame implements ActionListener {

    JTextField textField1;
JPasswordField passwordField1;
JButton b1,b2;
    Login(){
        setUndecorated(true);

        JLabel label4 = new JLabel(" GULAB RESIDENCY  ");
        label4.setBounds(478,30,560,150);
        label4.setFont(new Font("Tahoma",Font.BOLD,40));
        label4.setForeground(Color.WHITE);
        add(label4);


        JLabel label1 = new JLabel("Username :");
        label1.setBounds(550,160,150,40);
        label1.setFont(new Font("Tahoma", Font.BOLD,20));
        label1.setForeground(Color.BLACK);
        add(label1);

        JLabel label2 = new JLabel("Password :");
        label2.setBounds(550,210,150,40);
        label2.setFont(new Font("Tahoma", Font.BOLD,20));
        label2.setForeground(Color.BLACK);
        add(label2);

      textField1 = new JTextField();
      textField1.setBounds(680,160,250,30);
      textField1.setForeground(Color.WHITE);
textField1.setFont(new Font("Tahoma",Font.PLAIN,15));
    textField1.setBackground(new Color(26,104,110));
      add(textField1);

passwordField1 = new JPasswordField();
passwordField1.setBounds(680,210,250,30);
passwordField1.setForeground(Color.WHITE);
passwordField1.setBackground(new Color(15, 71, 76));
add(passwordField1);



b1 =  new JButton("Login");
b1.setBounds(610,300,120,30);
b1.setFont(new Font("serif",Font.PLAIN,15));
b1.setForeground(Color.WHITE);
b1.setBackground(Color.BLACK);
b1.addActionListener(this);
add(b1);

        b2 =  new JButton("Back");
        b2.setBounds(760,300,120,30);
 b2.setFont(new Font("serif",Font.PLAIN,15));
 b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.addActionListener(this);
        add(b2);
        ImageIcon imageIcon3 = new ImageIcon(ClassLoader.getSystemResource("icon/trail8.jpg"));
        Image image2 = imageIcon3.getImage().getScaledInstance(1490,1200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon13 = new ImageIcon(image2);
        JLabel label3 = new JLabel(imageIcon13);
        label3.setBounds(-380,10,2090,800);
        add(label3);
        getContentPane().setBackground(new Color(15, 71, 76));
        setLayout(null);
        setLocation(0,-20);
        setSize(1650,890);
        setVisible(true);
    }

    @Override
    public void  actionPerformed(ActionEvent e){
        if(e.getSource()==b1){
            try{
                conn c = new conn();

    String user = textField1.getText();
    String pass = passwordField1.getText();

    String q = "select * from login where username = '" + user + "'and password ='" + pass + "'";
    ResultSet resultset = c.statement.executeQuery(q);

    if (resultset.next()) {
        new Reception();
        setVisible(false);
    } else{
        JOptionPane.showMessageDialog(null, "Invalid User");
    }
    }catch(SQLException ex){
        ex.printStackTrace();
    }
}
         else if(e.getSource() == b2) {
            new Dashboard();
        }
    }
    public static void main(String[] args){
        new Login();
    }
}
