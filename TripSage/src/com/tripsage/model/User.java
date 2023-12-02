package com.tripsage.model;

import java.io.Serializable;

public class User implements Serializable {
    private String fullName;
    private String email;
    private String password;
    private Address address;

    public User(String fullName, String email, String password, Address address) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
