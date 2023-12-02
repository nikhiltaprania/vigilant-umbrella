package com.tripsage.service;

import com.tripsage.model.Destination;

import java.util.List;

public interface DestinationService {
    void addDestination(Destination destination);

    boolean deleteDestination(int id);

    void updateDestination(Destination updatedDestination);
    Destination getDestinationById(int id);

    List<Destination> getAllDestinations();

    List<Destination> getAllDestinationsByName(String name);
}