package com.oop.project;

public class Stats {
    private int countReview;
    private int countTasks;

    public Stats(int countReview, int countTasks) {
        this.countReview = countReview;
        this.countTasks = countTasks;
    }
    public Stats(String countReview, String countTasks) {
        this.countReview = Integer.parseInt(countReview);
        this.countTasks = Integer.parseInt(countTasks);
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
