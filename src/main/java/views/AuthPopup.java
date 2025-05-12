package main.java.views;

import main.java.model.User;
import main.java.service.UserService;
import main.java.service.SendOTPService;
import main.java.service.GenerateOTP;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AuthPopup extends JFrame {
    private JTextField signInEmail;
    private JTextField signUpName;
    private JTextField signUpEmail;

   
    private Map<String, String> otpMap = new HashMap<>();

    public AuthPopup() {
        setTitle("Login / Register");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

       
        JPanel signInPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        signInEmail = new JTextField();
        JButton loginBtn = new JButton("Send OTP");
        signInPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        signInPanel.add(new JLabel("Email:"));
        signInPanel.add(signInEmail);
        signInPanel.add(loginBtn);
        tabbedPane.addTab("Sign In", signInPanel);

        
        JPanel signUpPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        signUpName = new JTextField();
        signUpEmail = new JTextField();
        JButton registerBtn = new JButton("Register");
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        signUpPanel.add(new JLabel("Name:"));
        signUpPanel.add(signUpName);
        signUpPanel.add(new JLabel("Email:"));
        signUpPanel.add(signUpEmail);
        signUpPanel.add(registerBtn);
        tabbedPane.addTab("Sign Up", signUpPanel);

       
        loginBtn.addActionListener(e -> {
            String email = signInEmail.getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter email");
                return;
            }

            String otp = GenerateOTP.getOTP();
            otpMap.put(email, otp);
            SendOTPService.sendOTP(email, otp);

            String enteredOtp = JOptionPane.showInputDialog(this, "Enter the OTP sent to " + email);
            if (enteredOtp != null && enteredOtp.equals(otpMap.get(email))) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose();
                new UserViews(email).home();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid OTP.");
            }
        });

        
        registerBtn.addActionListener(e -> {
            String name = signUpName.getText().trim();
            String email = signUpEmail.getText().trim();
            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both name and email");
                return;
            }

            User user = new User(name, email);
            Integer result = UserService.saveUser(user);
            if (result == null) {
                JOptionPane.showMessageDialog(this, "Something went wrong.");
            } else if (result == 0) {
                JOptionPane.showMessageDialog(this, "User already exists. Try logging in.");
            } else {
                JOptionPane.showMessageDialog(this, "Registration successful!");
            }
        });

        add(tabbedPane);
        setVisible(true);
    }
}
