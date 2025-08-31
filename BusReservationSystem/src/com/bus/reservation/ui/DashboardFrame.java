package com.bus.reservation.ui;

import com.bus.reservation.model.User;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private User user;

    public DashboardFrame(User user) {
        this.user = user;

        setTitle("Dashboard - Welcome " + user.getName());
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton viewBusesButton = new JButton("View Buses & Book");
        JButton myBookingsButton = new JButton("My Bookings");
        JButton logoutButton = new JButton("Logout");

        panel.add(viewBusesButton);
        panel.add(myBookingsButton);
        panel.add(logoutButton);

        add(panel);

        viewBusesButton.addActionListener(e -> new BusListFrame(user).setVisible(true));
        myBookingsButton.addActionListener(e -> new MyBookingsFrame(user).setVisible(true));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
