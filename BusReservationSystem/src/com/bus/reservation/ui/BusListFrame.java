package com.bus.reservation.ui;

import com.bus.reservation.model.Bus;
import com.bus.reservation.model.User;
import com.bus.reservation.service.BusService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BusListFrame extends JFrame {
    private User user;
    private BusService busService = new BusService();
    private JTable table;

    public BusListFrame(User user) {
        this.user = user;

        setTitle("Available Buses");
        setSize(700, 400);
        setLocationRelativeTo(null);

        String[] columns = {"ID", "Bus No", "Source", "Destination", "Date", "Seats", "Available", "Fare"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        List<Bus> buses = busService.getAllBuses();
        for (Bus b : buses) {
            model.addRow(new Object[]{b.getId(), b.getBusNumber(), b.getSource(), b.getDestination(),
                    b.getDate(), b.getTotalSeats(), b.getAvailableSeats(), b.getFare()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        JButton bookButton = new JButton("Book Selected Bus");

        add(scrollPane, BorderLayout.CENTER);
        add(bookButton, BorderLayout.SOUTH);

        bookButton.addActionListener(e -> bookBus());
    }

    private void bookBus() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a bus to book.");
            return;
        }
        int busId = (int) table.getValueAt(row, 0);
        new BookingFrame(user, busId).setVisible(true);
    }
}
