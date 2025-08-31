package com.bus.reservation.ui;

import com.bus.reservation.exception.SeatNotAvailableException;
import com.bus.reservation.model.User;
import com.bus.reservation.service.BookingService;

import javax.swing.*;
import java.awt.*;

public class BookingFrame extends JFrame {
    private User user;
    private int busId;
    private JTextField seatsField;
    private BookingService bookingService = new BookingService();

    public BookingFrame(User user, int busId) {
        this.user = user;
        this.busId = busId;

        setTitle("Book Bus ID: " + busId);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Seats to Book:"));
        seatsField = new JTextField();
        panel.add(seatsField);

        JButton confirmButton = new JButton("Confirm Booking");
        panel.add(confirmButton);

        add(panel);

        confirmButton.addActionListener(e -> bookSeats());
    }

    private void bookSeats() {
        try {
            int seats = Integer.parseInt(seatsField.getText());
            if (bookingService.bookTicket(user.getId(), busId, seats)) {
                JOptionPane.showMessageDialog(this, "Booking successful!");
                dispose();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number of seats.");
        } catch (SeatNotAvailableException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
