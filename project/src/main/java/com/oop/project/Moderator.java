package com.oop.project;

import java.io.Serializable;

public class Moderator extends Admin implements Serializable {
    public int rang = 0;

    public Moderator(){this.userType = UserType.MODERATOR;}

    public int getRang() {
        return rang;
    }


    public void setRang(int rang) {
        this.rang = rang;
    }
    public void setRang(String rang) {
        this.rang = Integer.parseInt(rang);
    }

    @Override
    public String toString() {
        String string = "";
        string += super.toString();
        string += "Rang: " + rang + "\n";
        return string;
    }
}
