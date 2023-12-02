package com.tripsage.model;

import java.io.Serializable;

public class Booking implements Serializable {
    private String bookingId;
    private Destination destination;
    private Flight flight;

    public Booking(String bookingId, Destination destination, Flight flight) {
        this.bookingId = bookingId;
        this.destination = destination;
        this.flight = flight;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public void displayAllBookings() {
        System.out.println("Booking ID: " + bookingId + "\n");
        getDestination().displayDestinationInfo();
        getFlight().displayFlightInfo();
    }
}
