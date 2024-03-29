package com.oop.project;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

import java.io.Serializable;

public class Transaction implements Serializable {
    public double amount;
    public String walletTo;
    public String walletFrom;

    public Transaction(String walletFrom, String walletTo, String amount) {
        this.amount = Double.parseDouble(amount);
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
        return "FROM:" + this.walletFrom + "\n\tTO: " + this.walletTo + "\n\tAMOUNT: " + this.amount + " Near\n";
    }

    public ObservableValue<String> fromProperty() {
        return new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return walletFrom;
            }
        };
    }

    public ObservableValue<String> toProperty() {
        return new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return walletTo;
            }
        };
    }

    public ObservableValue<Double> amountProperty() {
        return new ObservableValueBase<Double>() {
            @Override
            public Double getValue() {
                return amount;
            }
        };
    }
}
