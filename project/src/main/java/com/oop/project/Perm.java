package com.oop.project;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

public class Perm {
    public String name = "";

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
