package com.bus.reservation.dao;

import com.bus.reservation.model.Booking;
import com.bus.reservation.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings(user_id, bus_id, seats_booked, status) VALUES (?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getBusId());
            ps.setInt(3, booking.getSeatsBooked());
            ps.setString(4, booking.getStatus());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("bus_id"),
                        rs.getInt("seats_booked"),
                        rs.getString("status")
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET status='Cancelled' WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
