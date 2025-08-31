package com.bus.reservation.ui;

import com.bus.reservation.model.User;
import com.bus.reservation.service.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private UserService userService = new UserService();

    public LoginFrame() {
        setTitle("Bus Reservation - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterFrame().setVisible(true);
        });
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        User user = userService.login(email, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new DashboardFrame(user).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.");
        }
    }
}
