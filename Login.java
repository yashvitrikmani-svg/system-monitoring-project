import javax.swing.*;
import java.awt.event.*;

public class Login {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Admin Login");
        frame.setSize(350, 250);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 100, 30);
        frame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(130, 30, 150, 30);
        frame.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 80, 100, 30);
        frame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(130, 80, 150, 30);
        frame.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 140, 100, 30);
        frame.add(loginBtn);

        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String user = userField.getText();
                String pass = new String(passField.getPassword());

                // simple check (demo)
                if(user.equals("admin") && pass.equals("1234")) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Login!");
                }
            }
        });

        frame.setVisible(true);
    }
}