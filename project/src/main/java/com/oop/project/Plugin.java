package com.oop.project;

public interface Plugin {
    String getExt();
    String getDescr();
    byte[] encrypt(byte data[]);
    byte[] decrypt(byte data[]);

}
