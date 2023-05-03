package com.oop.project;

import java.io.Serializable;
import java.util.ArrayList;

public class TechnicalAdmin extends Admin implements Serializable {
    public ArrayList<Perm> getPermissions() {
        return permissoins;
    }

    public ArrayList<Perm> permissoins = new ArrayList<>();

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

    public ArrayList<Perm> getPermissoins() {
        return permissoins;
    }

    public void setPermissoins(ArrayList<Perm> permissoins) {
        this.permissoins = permissoins;
    }

    TechnicalAdmin() {this.userType = UserType.TECHNICAL_ADMIN;}

    TechnicalAdmin(boolean isRoot) {
        super.setRoot(isRoot);
        userType = UserType.TECHNICAL_ADMIN;
    }

    TechnicalAdmin(User user) {
        super.setBirth(user.getBirth());
        super.setName(user.getName());
        userType = UserType.TECHNICAL_ADMIN;
    }

    TechnicalAdmin(User user, boolean isRoot) {
        super.setBirth(user.getBirth());
        super.setName(user.getName());
        super.setRoot(isRoot);
        userType = UserType.TECHNICAL_ADMIN;
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
