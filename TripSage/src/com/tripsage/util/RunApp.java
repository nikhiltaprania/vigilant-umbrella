package com.tripsage.util;

import com.tripsage.model.*;
import com.tripsage.service.DestinationService;
import com.tripsage.service.FlightService;
import com.tripsage.service.TravelerService;
import com.tripsage.service.TravelerServiceImpl;

import java.util.List;
import java.util.Scanner;

public class RunApp {
    private final Scanner sc;
    private final DestinationService destinationService;
    private final FlightService flightService;

    public RunApp(Scanner sc, DestinationService destinationService, FlightService flightService) {
        this.sc = sc;
        this.destinationService = destinationService;
        this.flightService = flightService;
    }

    public void manageDestinations() {
        while (true) {
            System.out.println("\n1. Add Destination\n2. Delete Destination\n3. Update Destination");
            System.out.println("4. View All Destinations\n5. View All Destinations By Name");
            System.out.print("0. Back To Main Menu\nEnter: ");

            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.println("Enter Destination Details.");
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    destinationService.addDestination(enterDestinationDetails(id));
                    System.out.println("Destination added successfully");
                }
                case 2 -> {
                    System.out.print("Enter Destination ID: ");
                    int id = sc.nextInt();
                    if (destinationService.deleteDestination(id)) {
                        System.out.println("Destination deleted successfully");
                    } else {
                        System.out.println("No Destination Found");
                    }
                }
                case 3 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    Destination destination = destinationService.getDestinationById(id);
                    if (destination == null) {
                        System.out.println("No Destination found");
                    } else {
                        destinationService.updateDestination(enterDestinationDetails(id));
                        System.out.println("Destination updated successfully");
                    }
                }
                case 4 -> {
                    List<Destination> destinations = destinationService.getAllDestinations();
                    if (!destinations.isEmpty()) {
                        System.out.println("All Destinations: ");
                        destinations.forEach(Destination::displayDestinationInfo);
                    } else {
                        System.out.println("No Destination Found");
                    }
                }
                case 5 -> {
                    System.out.print("Enter Destination Name: ");
                    sc.nextLine();
                    List<Destination> destinations = destinationService.getAllDestinationsByName(sc.nextLine());

                    if (!destinations.isEmpty()) {
                        destinations.forEach(Destination::displayDestinationInfo);
                    } else {
                        System.out.println("No Destination Found");
                    }
                }
                case 0 -> {
                    return;
                }
            }
        }
    }

    private Destination enterDestinationDetails(int id) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.println("Description:");
        String description = sc.nextLine();
        System.out.print("Price\\Night: ");
        double price = sc.nextDouble();
        System.out.println("\nEnter Address: ");
        return new Destination(id, name, description, price, addressDetails(sc));
    }

    public Address addressDetails(Scanner sc) {
        System.out.print("City: ");
        sc.nextLine();
        String city = sc.nextLine();
        System.out.print("State: ");
        String state = sc.nextLine();
        System.out.print("PinCode: ");
        int pinCode = sc.nextInt();

        return new Address(city, state, pinCode);
    }

    public void manageFlights() {
        while (true) {
            System.out.println("\n1. Add Flight\n2. Delete Flight\n3. Update Flight\n4. View All Flight");
            System.out.print("0. Back To Previous\nEnter: ");

            switch (sc.nextInt()) {
                case 1 -> {
                    flightService.addFlight(enterFlightDetails());
                    System.out.println("Flight Added Successfully");
                }
                case 2 -> {
                    System.out.print("Enter Flight Number: ");
                    String number = sc.next();
                    if (flightService.deleteFlight(number)) {
                        System.out.println("Flight has been removed successfully");
                    } else {
                        System.out.println("No Flight Found");
                    }
                }
                case 3 -> {
                    if (flightService.updateFlight(enterFlightDetails())) {
                        System.out.println("Flight Details updated successfully");
                    } else {
                        System.out.println("No Flight Found");
                    }
                }
                case 4 -> flightService.getAllFlights().forEach(Flight::displayFlightInfo);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private Flight enterFlightDetails() {
        System.out.println("\nEnter Flight Details.");
        System.out.print("Flight Number: ");
        String flightNumber = sc.next();
        System.out.print("Airline Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Departure Airport: ");
        String departureAirport = sc.nextLine();
        System.out.print("Destination Airport: ");
        String destinationAirport = sc.nextLine();

        return new Flight(flightNumber, name, departureAirport, destinationAirport);
    }

    public void travellerManagement(Traveller traveller) {

        TravelerService travelerService = new TravelerServiceImpl(traveller);
        List<Booking> bookings = traveller.getBookings();

        while (traveller.isLoggedIn()) {
            System.out.println("\n1. Add Booking\n2. Cancel Booking");
            System.out.println("3. View All Bookings\n0. Logout");
            System.out.print("Enter: ");

            switch (sc.nextInt()) {
                case 1 -> {
                    List<Destination> destinations = destinationService.getAllDestinations();
                    if (destinations.isEmpty()) {
                        System.out.println("No Destination Available");
                    } else {
                        System.out.println("Choose A Destination");
                        destinations.forEach(Destination::displayDestinationInfo);

                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        Destination choosenDestination = destinations.stream().filter(destination -> destination.getId() == id).findFirst().orElse(null);

                        if (choosenDestination == null) {
                            System.out.println("You've entered invalid ID");
                        } else {
                            System.out.println("Choose A Flight");

                            List<Flight> flights = flightService.getAllFlights();

                            if (flights.isEmpty()) {
                                System.out.println("Sorry No Flight Available");
                            } else {
                                flights.forEach(Flight::displayFlightInfo);

                                System.out.print("\nEnter Flight Number: ");
                                String number = sc.next();

                                Flight flight = flights.stream().filter(f -> f.getFlightNumber().equals(number)).findFirst().orElse(null);

                                if (flight == null) {
                                    System.out.println("You've entered invalid flight number");
                                } else {
                                    String bookingID = traveller.getEmail();
                                    Booking booking = new Booking(bookingID, choosenDestination, flight);
                                    bookings.add(booking);

                                    System.out.println("Booking Done..");
                                }
                            }
                        }
                    }

                }
                case 2 -> {
                    System.out.print("Enter Booking ID: ");
                    String id = sc.next();
                    if (travelerService.cancelBooking(id)) {
                        System.out.println("Booking has been cancel");
                    } else {
                        System.out.println("No booking found with id " + id);
                    }

                }
                case 3 -> {
                    if (bookings.isEmpty()) {
                        System.out.println("No Bookings yet");
                    } else {
                        System.out.println("Your all bookings");
                        bookings.forEach(Booking::displayAllBookings);
                    }

                }
                case 0 -> {
                    traveller.setLoggedIn(false);
                    System.out.println("logged out successfully");
                }
                default -> System.out.println("Invalid input\nTry Again !\n");
            }
        }
    }
}