package com.oop.project;

import java.util.ArrayList;

public class Developer extends User {

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    private ArrayList<Transaction> transactions;
    private String wallet;
    private Stats stats;



    public Stats getStats() {
        return stats;
    }
    Developer(User user) {
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new Stats(0, 0);
        this.wallet = "near.near";
    }

    Developer() {
        User user = new User();
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new Stats(0, 0);
        this.wallet = "near.near";
    }

    Developer(User user, String wallet) {
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new Stats(0, 0);
        this.wallet = wallet;
    }

    Developer(String wallet) {
        User user = new User();
        this.setBirth(user.getBirth());
        this.setName(user.getName());
        this.transactions = new ArrayList<>();
        this.stats = new Stats(0, 0);
        this.wallet = wallet;
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

    @Override
    public String toString() {
        String string;
        string = super.toString();
        string += "Wallet: " + this.wallet + "\n";
        string += "Stats: " + this.stats.toString() + "\n";
        string += "Transacions:\n";
        for (Transaction transaction : this.transactions) {
            string += "\t" + transaction.toString() + "\n";
        }
        return string;
    }
}
