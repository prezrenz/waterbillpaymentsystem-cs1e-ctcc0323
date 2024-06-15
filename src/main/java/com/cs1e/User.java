package com.cs1e;

import com.cs1e.Database.DatabaseError;

public class User {
    String name;
    String email;
    String password;
    boolean isAdmin;

    String address;
    int previousReading;
    int currentReading;
    int consumption;
    int totalDue;
    String dueDate;
    String status;

    String creditCardNumber;
    int balance;

    User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = true;

        address = "";
        previousReading = 0;
        currentReading = 0;
        consumption = 0;
        totalDue = 0;
        dueDate = "";
        status = "admin";

        creditCardNumber = "";
        balance = 0;
    }

    User(String name, String email, String password, String address, 
            String creditCardNumber, int balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = false;

        this.address = address;
        this.previousReading = 0;
        this.currentReading = 0;
        this.consumption = 0;
        this.totalDue = 0;
        this.dueDate = "";
        this.status = "UNVERIFIED";

        this.creditCardNumber = creditCardNumber;
        this.balance = 0;
    }

    User(String name, String email, String password, boolean isAdmin, String address,
            int previousReading, int currentReading, int consumption, int totalDue,
            String dueDate, String status, String creditCardNumber, int balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = false;

        this.address = address;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.consumption = consumption;
        this.totalDue = totalDue;
        this.dueDate = dueDate;
        this.status = status;

        this.creditCardNumber = creditCardNumber;
        this.balance = balance;
    }

    Object[] tabularize() {
        Object[] data = {this.name, this.email, this.address,
                            this. previousReading, this.currentReading, this.consumption,
                            this.totalDue, this.dueDate, this.status};

        return data;
    }

    String stringify() {
        return String.format("%s:%s:%s:%b:%s:%d:%d:%d:%d:%s:%s:%s:%d",
                            name, email, password, isAdmin,
                            address, previousReading, currentReading, consumption,
                            totalDue, dueDate, status, creditCardNumber, balance);
    }

    void setNewReading(int newReading, String newDueDate, final int RATE) {
        previousReading = currentReading;
        currentReading = newReading;
        consumption = currentReading - previousReading;
        totalDue = consumption * RATE;

        dueDate = newDueDate;
        status = "UNPAID";
    }

    void pay() {
        if(balance < totalDue) {
            throw new DatabaseError("Not enough balance!");
        }

        balance = balance - totalDue;
        status = "PAID";
    }
}
