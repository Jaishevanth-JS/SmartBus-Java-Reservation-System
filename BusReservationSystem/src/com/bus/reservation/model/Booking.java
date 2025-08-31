package com.bus.reservation.model;

public class Booking {
    private int id;
    private int userId;
    private int busId;
    private int seatsBooked;
    private String status;

    public Booking() {}

    public Booking(int id, int userId, int busId, int seatsBooked, String status) {
        this.id = id;
        this.userId = userId;
        this.busId = busId;
        this.seatsBooked = seatsBooked;
        this.status = status;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getBusId() { return busId; }
    public void setBusId(int busId) { this.busId = busId; }

    public int getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
