package com.example.spendmanager.Model;

public class Revenue {
    private int id;
    private int imageView;
    private String date;
    private double amount;
    private String description;

    // Constructor, getters, and setters
    public Revenue(int id, String date, double amount, String description) {
        this.id = id;
        this.imageView = imageView;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getImageView() {
        return imageView;
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
