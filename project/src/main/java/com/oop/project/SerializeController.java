package com.oop.project;

import java.util.ArrayList;

public abstract class SerializeController {
    public abstract String getExt();
    public abstract String getExtention();
    public abstract String getInfo();
    public abstract byte[] saveDataToByteArray(ArrayList<User> users);
    public abstract ArrayList<User> loadDataFromByteArray(byte[] data);
}
