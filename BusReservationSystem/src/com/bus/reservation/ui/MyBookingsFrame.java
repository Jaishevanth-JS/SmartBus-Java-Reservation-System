package com.bus.reservation.ui;

import com.bus.reservation.model.Booking;
import com.bus.reservation.model.User;
import com.bus.reservation.service.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MyBookingsFrame extends JFrame {
    private User user;
    private BookingService bookingService = new BookingService();
    private JTable table;

    public MyBookingsFrame(User user) {
        this.user = user;

        setTitle("My Bookings");
        setSize(600, 300);
        setLocationRelativeTo(null);

        String[] columns = {"Booking ID", "Bus ID", "Seats", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        List<Booking> bookings = bookingService.getBookingsByUser(user.getId());
        for (Booking b : bookings) {
            model.addRow(new Object[]{b.getId(), b.getBusId(), b.getSeatsBooked(), b.getStatus()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        JButton cancelButton = new JButton("Cancel Booking");

        add(scrollPane, BorderLayout.CENTER);
        add(cancelButton, BorderLayout.SOUTH);

        cancelButton.addActionListener(e -> cancelBooking());
    }

    private void cancelBooking() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a booking to cancel.");
            return;
        }
        int bookingId = (int) table.getValueAt(row, 0);
        int busId = (int) table.getValueAt(row, 1);
        int seats = (int) table.getValueAt(row, 2);

        if (bookingService.cancelBooking(bookingId, busId, seats)) {
            JOptionPane.showMessageDialog(this, "Booking cancelled!");
            dispose();
            new MyBookingsFrame(user).setVisible(true);
        }
    }
}
