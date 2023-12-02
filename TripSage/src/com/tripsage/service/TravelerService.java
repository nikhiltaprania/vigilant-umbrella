package com.tripsage.service;

import com.tripsage.model.Booking;
import com.tripsage.model.Traveller;

import java.util.List;

public interface TravelerService {


    boolean cancelBooking(String bookingId);
}