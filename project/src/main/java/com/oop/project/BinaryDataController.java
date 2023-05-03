package com.oop.project;

import java.io.*;
import java.util.ArrayList;

public class BinaryDataController {

    public static void saveDataToFile(ArrayList<User> users, File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> loadDataFromFile(File file){
        ArrayList<User> users = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            users = (ArrayList<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }



}
