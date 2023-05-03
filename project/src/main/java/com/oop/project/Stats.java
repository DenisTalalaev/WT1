package com.oop.project;

import java.io.Serializable;

public class Stats implements Serializable {
    public int countReview = 0;
    public int countTasks = 0;

    public Stats(){    }



    public Stats(int countReview, int countTasks) {
        this.countReview = countReview;
        this.countTasks = countTasks;
    }
    public Stats(String countReview, String countTasks) {
        this.countReview = countReview.equalsIgnoreCase("")?0:Integer.parseInt(countReview);
        this.countTasks = countTasks.equalsIgnoreCase("")?0:Integer.parseInt(countTasks);
    }

    @Override
    public String toString() {
        return "Stat: \n\t Review count: " + this.countReview + "\n\t Task count: " + this.countTasks;
    }

    public int getCountReview() {
        return countReview;
    }

    public void setCountReview(int countReview) {
        this.countReview = countReview;
    }

    public int getCountTasks() {
        return countTasks;
    }

    public void setCountTasks(int countTasks) {
        this.countTasks = countTasks;
    }
}
