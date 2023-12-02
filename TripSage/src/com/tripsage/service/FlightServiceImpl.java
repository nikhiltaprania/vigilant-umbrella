package com.tripsage.service;

import com.tripsage.model.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlightServiceImpl implements FlightService {
    private final Map<String, Flight> flightMap;

    public FlightServiceImpl(Map<String, Flight> flightMap) {
        this.flightMap = flightMap;
    }

    @Override
    public void addFlight(Flight flight) {
        flightMap.put(flight.getFlightNumber(), flight);
    }

    @Override
    public boolean deleteFlight(String flightNumber) {
        if (flightMap.containsKey(flightNumber)) {
            flightMap.remove(flightNumber);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFlight(Flight updatedFlight) {
        for (Map.Entry<String, Flight> entry : flightMap.entrySet()) {
            Flight flight = entry.getValue();
            String number = flight.getFlightNumber();

            if (number.equals(updatedFlight.getFlightNumber())) {
                flightMap.put(number, updatedFlight);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Flight> getAllFlights() {
        return new ArrayList<>(flightMap.values());
    }
}
