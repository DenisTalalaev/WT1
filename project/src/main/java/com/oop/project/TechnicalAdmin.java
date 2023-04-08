package com.oop.project;

import java.util.ArrayList;

public class TechnicalAdmin extends Admin {
    private ArrayList<String> permissoins = new ArrayList<>();

    @Override
    public String toString() {
        String string = super.toString();
        string += "Permissions:\n";
        for (String permission: permissoins
             ) {
            string += "\t" + permission + "\n";
        }
        return string;
    }

    TechnicalAdmin() {
    }

    TechnicalAdmin(boolean isRoot) {
        super.setRoot(isRoot);
    }

    TechnicalAdmin(User user) {
        super.setBirth(user.getBirth());
        super.setName(user.getName());
    }

    TechnicalAdmin(User user, boolean isRoot) {
        super.setBirth(user.getBirth());
        super.setName(user.getName());
        super.setRoot(isRoot);
    }

    public int addPermission(String permission) {
        try {
            this.permissoins.add(permission);
        } catch (Exception e) {
            return 1;
        }
        return 0;
    }
}
