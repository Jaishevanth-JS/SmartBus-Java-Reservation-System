package com.bus.reservation.dao;

import com.bus.reservation.model.Bus;
import com.bus.reservation.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT * FROM buses";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Bus bus = new Bus(
                        rs.getInt("id"),
                        rs.getString("bus_number"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("date"),
                        rs.getInt("total_seats"),
                        rs.getInt("available_seats"),
                        rs.getDouble("fare")
                );
                buses.add(bus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

    public Bus getBusById(int id) {
        String sql = "SELECT * FROM buses WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bus(
                        rs.getInt("id"),
                        rs.getString("bus_number"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("date"),
                        rs.getInt("total_seats"),
                        rs.getInt("available_seats"),
                        rs.getDouble("fare")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAvailableSeats(int busId, int seats) {
        String sql = "UPDATE buses SET available_seats=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, seats);
            ps.setInt(2, busId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
