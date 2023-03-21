public class Stats {
    private Task task;
    private int countReview;
    private int countTasks;

    public Stats(Task task, int countReview, int countTasks) {
        this.task = task;
        this.countReview = countReview;
        this.countTasks = countTasks;
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
