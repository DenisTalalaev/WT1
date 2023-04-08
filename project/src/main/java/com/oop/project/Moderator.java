package com.oop.project;

public class Moderator extends Admin {
    private int rang;

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    @Override
    public String toString() {
        String string = "";
        string += super.toString();
        string += "Rang: " + rang + "\n";
        return string;
    }
}
