package com.tripsage.service;

import com.tripsage.model.Administrator;
import com.tripsage.model.Traveller;

import java.util.Map;

public class UserServiceImpl implements UserService {
    private final Map<String, Traveller> travellerMap;

    public UserServiceImpl(Map<String, Traveller> travellerMap) {
        this.travellerMap = travellerMap;
    }

    @Override
    public Traveller loginAsTraveller(String email, String password) {
        if (travellerMap.containsKey(email)) {
            Traveller traveller = travellerMap.get(email);
            if (traveller.getPassword().equals(password)) {
                traveller.setLoggedIn(true);
                return traveller;
            } else {
                System.out.println("Password is incorrect");
            }
        } else {
            System.out.println("Account doesn't exist");
        }
        return null;
    }

    @Override
    public Administrator loginAsAdmin(String email, String password) {
        Administrator admin = new Administrator();
        if (admin.getEmail().equals(email)) {
            if (admin.getPassword().equals(password)) {
                admin.setLoggedIn(true);
                return admin;
            } else {
                System.out.println("Password is incorrect");
                return null;
            }
        }

        System.out.println("Email is incorrect");
        return null;
    }

    @Override
    public void applyAsTraveller(Traveller traveller) {
        travellerMap.put(traveller.getEmail(), traveller);
    }
}