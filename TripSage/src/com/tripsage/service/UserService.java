package com.tripsage.service;

import com.tripsage.model.Administrator;
import com.tripsage.model.Traveller;

public interface UserService {
    Traveller loginAsTraveller(String email, String password);
    Administrator loginAsAdmin(String email, String password);

    void applyAsTraveller(Traveller traveller);

}