package com.tripsage.service;

import com.tripsage.model.*;

import java.util.List;

public interface FlightService {
    void addFlight(Flight flight);
    boolean deleteFlight(String flightNumber);
    boolean updateFlight(Flight updatedFlight);
    List<Flight> getAllFlights();
}