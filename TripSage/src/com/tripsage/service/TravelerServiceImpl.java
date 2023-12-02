package com.tripsage.service;

import com.tripsage.model.Booking;
import com.tripsage.model.Traveller;

import java.util.List;

public class TravelerServiceImpl implements TravelerService {
    private final Traveller traveller;

    public TravelerServiceImpl(Traveller traveller) {
        this.traveller = traveller;
    }

    @Override
    public boolean cancelBooking(String bookingId) {
        return traveller.getBookings().removeIf(booking -> booking.getBookingId().equals(bookingId));
    }
}
