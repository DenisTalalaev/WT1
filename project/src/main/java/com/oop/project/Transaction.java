package com.oop.project;

public class Transaction {
    private double amount;
    private String walletTo;
    private String walletFrom;

    public Transaction(double amount, String walletTo, String walletFrom) {
        this.amount = amount;
        this.walletTo = walletTo;
        this.walletFrom = walletFrom;
    }

    public Transaction() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWalletTo() {
        return walletTo;
    }

    public void setWalletTo(String walletTo) {
        this.walletTo = walletTo;
    }

    public String getWalletFrom() {
        return walletFrom;
    }

    public void setWalletFrom(String walletFrom) {
        this.walletFrom = walletFrom;
    }

    @Override
    public String toString() {
        return "FROM: " + this.walletFrom + "\tTO: " + this.walletTo + "\t AMOUNT: " + this.amount + " Near";
    }
}
