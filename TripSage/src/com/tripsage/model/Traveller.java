package com.tripsage.model;

import java.util.ArrayList;
import java.util.List;

public class Traveller extends User{
    private Role role;
    private boolean isLoggedIn;
    private List<Booking> bookings;

    public Traveller(String fullName, String email, String password, Address address) {
        super(fullName, email, password, address);
        this.role = Role.TRAVELLER;
        this.bookings = new ArrayList<>();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public List<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public void displayTravelerInfo() {
        System.out.println("===============================");
        System.out.format("Name: %s\nEmail: %s\nPassword: %s\nRole: %s\nCurrently logged in: %s\n", getFullName(), getEmail(), getPassword(), role, isLoggedIn);
        System.out.format("City: %s\nState: %s\nPinCode: %s\n", getAddress().getCity(), getAddress().getState(), getAddress().getPinCode());
        System.out.println("Bookings");
        bookings.forEach(Booking::displayAllBookings);
        System.out.println("=================================");
    }
}
