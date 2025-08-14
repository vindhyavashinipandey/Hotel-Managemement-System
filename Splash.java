package Hotel.Management.System;
import javax.swing.*;
import java.awt.*;
public class Splash extends JFrame {
    Splash(){
        setUndecorated(true);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/splash3.gif"));
        Image gifImage = imageIcon.getImage().getScaledInstance(1400, 780, Image.SCALE_DEFAULT);
        ImageIcon resizedIcon = new ImageIcon(gifImage);
        JLabel label = new JLabel(resizedIcon);
        label.setBounds(0, 0, 1400, 780);
        add(label);
        getContentPane().setBackground(new Color(15, 71, 76));
        setLayout(null);
        setLocation(0, 0);
        setSize(1400, 780);
        setVisible(true);
        getContentPane().setBackground(new Color(15, 71, 76));
        try{
            Thread.sleep(7000);
            new Dashboard();
            setVisible(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Splash();
    }
}
