package com.tripsage.service;

import com.tripsage.model.Destination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DestinationServiceImpl implements DestinationService {
    private Map<Integer, Destination> destinationMap;

    public DestinationServiceImpl(Map<Integer, Destination> destinationMap) {
        this.destinationMap = destinationMap;
    }

    @Override
    public void addDestination(Destination destination) {
        destinationMap.put(destination.getId(), destination);
    }

    @Override
    public boolean deleteDestination(int id) {
        if (destinationMap.containsKey(id)) {
            destinationMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public void updateDestination(Destination updatedDestination) {
        for (Map.Entry<Integer, Destination> entry : destinationMap.entrySet()) {
            Destination destination = entry.getValue();
            int id = destination.getId();

            if (id == updatedDestination.getId()) {
                destinationMap.put(id, updatedDestination);
                break;
            }
        }
    }

    @Override
    public Destination getDestinationById(int id) {
        if (destinationMap.containsKey(id)) {
            return destinationMap.get(id);
        }
        return null;
    }

    @Override
    public List<Destination> getAllDestinations() {
        return new ArrayList<>(destinationMap.values());
    }

    @Override
    public List<Destination> getAllDestinationsByName(String name) {
        return new ArrayList<>(destinationMap.values()).stream().filter(destination -> destination.getName().equals(name)).toList();
    }
}
