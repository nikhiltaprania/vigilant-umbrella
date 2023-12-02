package com.tripsage.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    // Method for serializing data to a file
    public static void serializeData(Map<?, ?> dataMap, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\nikhi\\IdeaProjects\\TripSage\\src\\resources\\" + fileName))) {
            oos.writeObject(dataMap);
            System.out.println("Data serialized successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method for deserializing data from a file
    public static Map<?, ?> deserializeData(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\nikhi\\IdeaProjects\\TripSage\\src\\resources\\" + fileName))) {
            Object data = ois.readObject();
            if (data instanceof Map<?, ?>) {
                System.out.println("Data deserialized successfully.");
                return (Map<?, ?>) data;
            } else {
                System.err.println("Error: Invalid data format in the file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return new HashMap<>(); // Return an empty map in case of an error
    }
}
