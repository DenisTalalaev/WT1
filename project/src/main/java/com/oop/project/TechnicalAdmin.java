package com.oop.project;

import java.util.ArrayList;

public class TechnicalAdmin extends Admin {
    public ArrayList<Perm> getPermissoins() {
        return permissoins;
    }

    private ArrayList<Perm> permissoins = new ArrayList<>();

    @Override
    public String toString() {
        String string = super.toString();
        string += "Permissions:\n";
        for (Perm permission: permissoins
             ) {
            string += "\t" + permission.name + "\n";
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

    public void addPermission(Perm permission) {
        try {
            this.permissoins.add(permission);
        } catch (Exception e) {
        }
    }

    public void addPermission(String permission) {
        try {
            this.permissoins.add(new Perm(permission));
        } catch (Exception e) {
        }
    }
}
