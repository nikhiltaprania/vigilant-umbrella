package com.tripsage.model;

import java.io.Serializable;

public class Flight implements Serializable {
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String destinationAirport;

    public Flight(String flightNumber, String airline, String departureAirport, String destinationAirport) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }
    public void displayFlightInfo() {
        System.out.format("Flight Number: %s\nAirline Name: %s\nDeparture Airport: %s\nDestination Airport: %s\n", flightNumber, airline, departureAirport, destinationAirport);
    }
}