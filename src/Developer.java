import java.util.ArrayList;

public class Developer extends User {

    private ArrayList<Transaction> transactions;
    private String wallet;
    private ArrayList<Stats> stats;

    Developer(User user) {
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new ArrayList<>();
        this.wallet = "near.near";
    }

    Developer() {
        User user = new User();
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new ArrayList<>();
        this.wallet = "near.near";
    }

    Developer(User user, String wallet) {
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new ArrayList<>();
        this.wallet = wallet;
    }

    Developer(String wallet) {
        User user = new User();
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new ArrayList<>();
        this.wallet = wallet;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
}
