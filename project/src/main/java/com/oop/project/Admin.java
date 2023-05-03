package com.oop.project;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public class Admin extends User implements Serializable {
    public boolean isRoot = false;
    public double salary = 0;
    public ArrayList<Integer> taskIDs = new ArrayList<>();



    Admin() {}

    public Admin(User user, boolean isRoot) {
        super.setBirth(user.getBirth());
        super.setName(user.getName());
        this.isRoot = isRoot;
        taskIDs = new ArrayList<>();
    }

    public Admin(String userName, LocalDate birthday, boolean isRoot) {
        super(userName, birthday);
        this.isRoot = isRoot;
        taskIDs = new ArrayList<>();
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        this.isRoot = root;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        try {
            this.salary = Double.parseDouble(salary);
        } catch (NumberFormatException e){
            this.salary = 0;
        }
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<Integer> getTaskIDs() {
        return taskIDs;
    }

    public void setTaskIDs(ArrayList<Integer> taskIDs) {
        this.taskIDs = taskIDs;
    }

    @Override
    public String toString() {
        String string = "";
        string += super.toString();
        string += this.isRoot ? "Root: true\n" : "Root: false\n";
        string += "Salary: " + this.salary + " Near/month\n";
        string += "Accessed tasks: " + Task.taskNamesByList(this.taskIDs) + "\n";
        return string;
    }
}
