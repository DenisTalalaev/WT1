package com.oop.project;

public class UserFactory {
    public User createUser(UserType type){
        User user = null;
        switch (type){
            case DEVELOPER -> {
                user = new Developer();
                break;
            }
            case MODERATOR -> {
                user = new Moderator();
                break;
            }
            case TECHNICAL_ADMIN -> {
                user = new TechnicalAdmin();
                break;
            }
            default ->
                    System.err.println("No such user type");
        }
        return user;
    }
}
