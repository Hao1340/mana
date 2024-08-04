package com.example.spendmanager.Model;

public class expensemodel {
    private String amount;
    private String description;
    private int userId;

    public expensemodel(String amount, String description, int userId) {
        this.amount = amount;
        this.description = description;
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
