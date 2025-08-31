package com.bus.reservation.model;

public class Bus {
    private int id;
    private String busNumber;
    private String source;
    private String destination;
    private String date;
    private int totalSeats;
    private int availableSeats;
    private double fare;

    public Bus() {}

    public Bus(int id, String busNumber, String source, String destination,
               String date, int totalSeats, int availableSeats, double fare) {
        this.id = id;
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fare = fare;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }
}
