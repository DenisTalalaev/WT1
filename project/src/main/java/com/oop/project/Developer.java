package com.oop.project;

import java.io.Serializable;
import java.util.ArrayList;

public class Developer extends User implements Serializable {

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Transaction> transactions = new ArrayList<>();
    public String wallet = "";
    public Stats stats = new Stats();


    public Stats getStats() {
        return stats;
    }
    Developer(User user) {
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new Stats(0, 0);
        this.wallet = "near.near";
        userType = UserType.DEVELOPER;
    }

    Developer() {this.userType = UserType.DEVELOPER;}

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    Developer(User user, String wallet) {
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new Stats(0, 0);
        this.wallet = wallet;
        userType = UserType.DEVELOPER;
    }

    Developer(String wallet) {
        User user = new User();
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new Stats(0, 0);
        this.wallet = wallet;
        userType = UserType.DEVELOPER;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void setStat(Stats stat) {
        stats = stat;
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

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(super.toString());
        string.append("Wallet: " + this.wallet + "\n");
        string.append("Stats: " + this.stats.toString() + "\n");
        string.append("Transactions:\n");
        for (Transaction transaction : this.transactions) {
            string.append("\t" + transaction.toString() + "\n");
        }
        return string.toString();
    }
}
