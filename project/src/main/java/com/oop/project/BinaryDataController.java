package com.oop.project;

import java.io.*;
import java.util.ArrayList;

public class BinaryDataController extends SerializeController {

    private String ext;

    private String extention = "*.bin";
    private String info = "Бинарные файлы";

    public String getExtention() {
        return extention;
    }

    public String getInfo() {
        return info;
    }

    public BinaryDataController(){
        ext = ".bin";
    }

    @Override
    public String getExt() {
        return ext;
    }

    @Override
    public byte[] saveDataToByteArray(ArrayList<User> users) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(users);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> loadDataFromByteArray(byte[] data) {
        ArrayList<User> users = new ArrayList<>();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            users = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }




}
