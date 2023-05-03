package com.oop.project;

public class UserFactory {
    public static User createUser(UserType type) {
        User user = null;
        switch (type) {
            case DEVELOPER -> {
                user = new Developer();
                user.userType = UserType.DEVELOPER;
                break;
            }
            case MODERATOR -> {
                user = new Moderator();
                user.userType = UserType.MODERATOR;
                break;
            }
            case TECHNICAL_ADMIN -> {
                user = new TechnicalAdmin();
                user.userType = UserType.TECHNICAL_ADMIN;
                break;
            }
            default -> System.err.println("No such user type");
        }
        return user;
    }

    public static User createUser(String value) {
        return switch (value) {
            case "Developer", "DEVELOPER" -> createUser(UserType.DEVELOPER);
            case "Moderator", "MODERATOR" -> createUser(UserType.MODERATOR);
            case "TechnicalAdmin", "TECHNICAL_ADMIN" -> createUser(UserType.TECHNICAL_ADMIN);
            default -> null;
        };

    }
}
