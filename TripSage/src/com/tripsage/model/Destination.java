package com.tripsage.model;

import java.io.Serializable;

public class Destination implements Serializable {
    private int id;
    private String name;
    private String description;
    private double pricePerNight;
    private Address address;

    public Destination(int id, String name, String description, double pricePerNight, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void displayDestinationInfo() {
        System.out.format("ID: %d\nName: %s\nDescription: %s\nPrice Per Night: %.2f\nCity: %s\nState: %s\nPin Code: %d\n\n", id, name, description, pricePerNight, address.getCity(), address.getState(), address.getPinCode());
    }
}