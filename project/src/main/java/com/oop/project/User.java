package com.oop.project;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class User implements Serializable {
    public String name;
    public LocalDate birth;
    public UserType userType;

    @Override
    public String toString() {
        String  string = "Name: " + this.getName() + "\n";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = formatter.format(this.birth);
        string += "Birth date: " + formattedDate + "\n";
        return string;
    }

    User(String userName, LocalDate birthday){
        this.name = userName;
        this.birth = birthday;
    }
    User(){}

    public LocalDate getBirth() {
        return birth;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        if(this instanceof Developer){
            return "Dev: " + name;
        }
        if(this instanceof Moderator){
            return "Mod: " + name;
        }

        if(this instanceof TechnicalAdmin){
            return "TAdm: " + name;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }



    public static LocalDate generateRandomDate() {
        Calendar start = new GregorianCalendar(1990, Calendar.JANUARY, 1);
        long startTime = start.getTimeInMillis();
        Calendar end = new GregorianCalendar(2010, Calendar.JANUARY, 1);
        long endTime = end.getTimeInMillis();

        long randomTime = startTime + (long) (new Random().nextDouble() * (endTime - startTime));

        Calendar randomDate = new GregorianCalendar();
        randomDate.setTimeInMillis(randomTime);
        int year = randomDate.get(Calendar.YEAR);
        int month = randomDate.get(Calendar.MONTH);
        int day = randomDate.get(Calendar.DAY_OF_MONTH);

        Date resultDate = new GregorianCalendar(year, month, day).getTime();
        return resultDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public ObservableValue<String> nameProperty() {
        return new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return getName();
            }
        };
    }

}
