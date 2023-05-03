package com.oop.project;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

import java.io.Serializable;

public class Perm  implements Serializable {
    public String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Perm(String permission) {
        name = permission;
    }
    public Perm(){}

    public ObservableValue<String> permissionProperty() {
        return new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return name;
            }
        };
    }
}
