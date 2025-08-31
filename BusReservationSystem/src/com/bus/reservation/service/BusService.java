package com.bus.reservation.service;

import com.bus.reservation.dao.BusDAO;
import com.bus.reservation.model.Bus;

import java.util.List;

public class BusService {
    private BusDAO busDAO = new BusDAO();

    public List<Bus> getAllBuses() {
        return busDAO.getAllBuses();
    }

    public Bus getBusById(int id) {
        return busDAO.getBusById(id);
    }

    public boolean updateAvailableSeats(int busId, int seats) {
        return busDAO.updateAvailableSeats(busId, seats);
    }
}
