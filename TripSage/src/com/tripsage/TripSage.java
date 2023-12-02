package com.tripsage;

import com.tripsage.model.Administrator;
import com.tripsage.model.Destination;
import com.tripsage.model.Flight;
import com.tripsage.model.Traveller;
import com.tripsage.service.*;
import com.tripsage.util.RunApp;

import java.util.Map;
import java.util.Scanner;

import static com.tripsage.util.FileManager.deserializeData;
import static com.tripsage.util.FileManager.serializeData;

public class TripSage {

    public static void main(String[] args) {
        // Deserialize data from files
        Map<?, ?> loadedDestinationMap = deserializeData("destination_data.ser");
        Map<?, ?> loadedFlightMap = deserializeData("flight_data.ser");
        Map<?, ?> loadedTravellerMap = deserializeData("traveller_data.ser");

        // Use instance of to check the type during deserialization
        Map<Integer, Destination> destinationMap = null;
        if (loadedDestinationMap instanceof Map<?, ?>) {
            destinationMap = (Map<Integer, Destination>) loadedDestinationMap;
            // Now you can use castedDestinationMap as needed
        }

        Map<String, Flight> flightMap = null;
        if (loadedFlightMap instanceof Map<?, ?>) {
            flightMap = (Map<String, Flight>) loadedFlightMap;
            // Now you can use castedFlightMap as needed
        }

        Map<String, Traveller> travellerMap = null;
        if (loadedTravellerMap instanceof Map<?, ?>) {
            travellerMap = (Map<String, Traveller>) loadedTravellerMap;
            // Now you can use castedTravellerMap as needed
        }

        DestinationService destinationService = new DestinationServiceImpl(destinationMap);
        FlightService flightService = new FlightServiceImpl(flightMap);
        UserService userService = new UserServiceImpl(travellerMap);
        Scanner sc = new Scanner(System.in);
        RunApp runApp = new RunApp(sc, destinationService, flightService);


        while (true) {
            System.out.println("\nLogin As");
            System.out.println("1. Admin\n2. Traveler\n0. Exit");
            System.out.print("Enter: ");
            switch (sc.nextInt()) {
                case 1 -> {
                    Administrator administrator = new Administrator();
                    System.out.print("\nEnter Email: ");
                    String email = sc.next();
                    System.out.print("Enter Password: ");
                    String pass = sc.next();

                    if (administrator.getEmail().equals(email)) {
                        if (administrator.getPassword().equals(pass)) {
                            administrator.setLoggedIn(true);

                            System.out.println("\nWelcome Admin");
                            while (administrator.isLoggedIn()) {
                                System.out.println("\n1. Manage Destination");
                                System.out.println("2. Manage Flights");
                                System.out.println("3. Manage Travellers");
                                System.out.print("0. Logout\nEnter: ");
                                switch (sc.nextInt()) {
                                    case 1 -> runApp.manageDestinations();
                                    case 2 -> runApp.manageFlights();
                                    case 3 -> {
                                        if (travellerMap.isEmpty()) {
                                            System.out.println("No traveller registered");
                                        } else {
                                            System.out.println("All Registered Travelers");
                                            for (Map.Entry<String, Traveller> entry : travellerMap.entrySet()) {
                                                String id = entry.getKey();
                                                Traveller traveller = entry.getValue();
                                                System.out.println("Traveller ID: " + id);
                                                try {
                                                    traveller.displayTravelerInfo();
                                                } catch (NullPointerException e) {
                                                    System.out.println("No Bookings\n");
                                                }
                                            }
                                            boolean flag = true;
                                            while (flag) {
                                                System.out.println("1. Remove Traveler\n0. Back To Previous Menu");
                                                System.out.print("Enter: ");
                                                switch (sc.nextInt()) {
                                                    case 1 -> {
                                                        System.out.print("Traveler ID: ");
                                                        String id = sc.next();

                                                        if (travellerMap.containsKey(id)) {
                                                            travellerMap.remove(id);
                                                            System.out.println("Traveler has been removed");
                                                        } else {
                                                            System.out.println("No Traveler found");
                                                        }
                                                    }
                                                    case 0 -> {
                                                        System.out.println("Logged out successfully");
                                                        flag = false;
                                                    }
                                                    default -> System.out.println("Invalid input\nTry Again !");
                                                }
                                            }
                                        }
                                    }
                                    case 0 -> {
                                        System.out.println("Logged out successfully");
                                        administrator.setLoggedIn(false);
                                    }
                                }
                            }
                        } else {
                            System.out.println("Password is wrong");
                        }
                    } else {
                        System.out.println("Invalid email");
                    }
                }
                case 2 -> {
                    boolean flag = true;
                    while (flag) {
                        System.out.println("\n1. Login\n2. SignUp\n0. Back To Main Menu");
                        System.out.print("Enter: ");


                        switch (sc.nextInt()) {
                            case 1 -> {
                                System.out.print("\nEnter Email: ");
                                String email = sc.next();
                                System.out.print("Enter Password: ");
                                String pass = sc.next();
                                Traveller traveller = userService.loginAsTraveller(email, pass);

                                if (traveller != null) {
                                    traveller.setLoggedIn(true);
                                    System.out.println("\nSuccessfully Login\nWelcome " + traveller.getFullName());
                                    runApp.travellerManagement(traveller);
                                }
                            }
                            case 2 -> {
                                System.out.print("\nEnter FullName: ");
                                sc.nextLine();
                                String name = sc.nextLine();
                                System.out.print("Enter Email: ");
                                String email = sc.next();
                                System.out.print("Enter Password: ");
                                String pass = sc.next();
                                System.out.println("\nEnter address details");
                                Traveller travel = new Traveller(name, email, pass, runApp.addressDetails(sc));
                                userService.applyAsTraveller(travel);
                                System.out.println("Signed up successfully");
                            }
                            case 0 -> flag = false;

                            default -> System.out.println("Invalid input");
                        }
                    }
                }
                case 0 -> {
                    // Serialize data to files
                    serializeData(destinationMap, "destination_data.ser");
                    serializeData(flightMap, "flight_data.ser");
                    serializeData(travellerMap, "traveller_data.ser");
                    System.out.println("Thank You for choosing application");
                    return;
                }
            }
        }
    }
}