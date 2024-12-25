import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JButton Login,SignUp;
    JTextField tfName;

    Login() {
        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);

        
   

        JLabel heading=new JLabel("MOODBITES");
        heading.setBounds(396, 350, 300, 45);
        heading.setFont(new Font("Mongolian Baiti", Font.BOLD,48));
        heading.setForeground(Color.BLACK);
        add(heading);

        JLabel name=new JLabel("Enter Your Name");
        name.setBounds(400, 400, 300, 20);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD,18));
        name.setForeground(Color.BLACK);
        add(name);

        tfName=new JTextField();
        tfName.setBounds(396, 430, 300, 25);
        tfName.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(tfName);

        // ### Buttons ###
        Login=new JButton("Login");
        Login.setBounds(420, 460, 100, 25);
        // Login.setSignUpground(new Color(GRAY));
        Login.setBackground(Color.WHITE);        
        Login.setForeground(Color.BLACK);
        Login.addActionListener(this);
        add(Login);

        SignUp=new JButton("SignUp");
        SignUp.setBounds(560, 460, 100, 25);
        SignUp.setBackground(Color.WHITE);        
        SignUp.setForeground(Color.BLACK);
        SignUp.addActionListener(this);
        add(SignUp);

        setSize(800, 700);
        setLocation(200, 200);
        setVisible(true);
    }
    // to override abstract methods 
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==Login){
            String name= tfName.getText();
            setVisible(false);
           // new Login(name);  // this will open Login window
        }
         else if(ae.getSource()==SignUp){
            setVisible(false);
        } 
    }

    public static void main(String[] args) {
        Login l = new Login();

    }
}

