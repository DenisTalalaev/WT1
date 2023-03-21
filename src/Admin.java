import java.util.ArrayList;
import java.util.Date;

public class Admin extends User{
    private boolean isRoot;
    private int salary = 0;
    private ArrayList<Integer> taskIDs;

    Admin(){
        isRoot = false;
        User user = new User();
        taskIDs = new ArrayList<>();
        super.setName(user.getName());
        super.setBirth(user.getBirth());
    }

    public Admin(User user, boolean isRoot) {
        super.setBirth(user.getBirth());
        super.setName(user.getName());
        this.isRoot = isRoot;
        taskIDs = new ArrayList<>();
    }

    public Admin(String userName, Date birthday, boolean isRoot) {
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public ArrayList<Integer> getTaskIDs() {
        return taskIDs;
    }

    public void setTaskIDs(ArrayList<Integer> taskIDs) {
        this.taskIDs = taskIDs;
    }
}
