package com.groupc.android.illuminati.Objects;

public class Bank {
    private int balance;

    public Bank() {
        balance = 1156; //added all physical MB together
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
