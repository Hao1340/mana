package com.example.spendmanager.Model;

public class Expense {
    private int id;
    private String date;
    private double amount;
    private String description;

    // Constructor, getters, and setters
    public Expense(int id, String date, double amount, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

}
