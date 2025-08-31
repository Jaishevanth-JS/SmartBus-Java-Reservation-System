package com.bus.reservation.ui;

import com.bus.reservation.service.UserService;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private UserService userService = new UserService();

    public RegisterFrame() {
        setTitle("Bus Reservation - Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");
        panel.add(registerButton);
        panel.add(backButton);

        add(panel);

        registerButton.addActionListener(e -> register());
        backButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void register() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (userService.register(name, email, password)) {
            JOptionPane.showMessageDialog(this, "Registration successful!");
            dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed.");
        }
    }
}
