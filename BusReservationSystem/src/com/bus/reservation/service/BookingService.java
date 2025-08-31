package com.bus.reservation.service;

import com.bus.reservation.dao.BookingDAO;
import com.bus.reservation.dao.BusDAO;
import com.bus.reservation.model.Booking;
import com.bus.reservation.model.Bus;
import com.bus.reservation.exception.SeatNotAvailableException;

import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO = new BookingDAO();
    private BusDAO busDAO = new BusDAO();

    public boolean bookTicket(int userId, int busId, int seats) throws SeatNotAvailableException {
        Bus bus = busDAO.getBusById(busId);
        if (bus == null || bus.getAvailableSeats() < seats) {
            throw new SeatNotAvailableException("Not enough seats available!");
        }
        int newSeats = bus.getAvailableSeats() - seats;
        busDAO.updateAvailableSeats(busId, newSeats);

        Booking booking = new Booking(0, userId, busId, seats, "Confirmed");
        return bookingDAO.createBooking(booking);
    }

    public List<Booking> getBookingsByUser(int userId) {
        return bookingDAO.getBookingsByUser(userId);
    }

    public boolean cancelBooking(int bookingId, int busId, int seats) {
        boolean success = bookingDAO.cancelBooking(bookingId);
        if (success) {
            Bus bus = busDAO.getBusById(busId);
            if (bus != null) {
                int newSeats = bus.getAvailableSeats() + seats;
                busDAO.updateAvailableSeats(busId, newSeats);
            }
        }
        return success;
    }
}
