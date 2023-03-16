package com.example.moneykeeper;

import java.util.Date;

public class Category{

    private String categoryName;
    private double categoryBalance;
    private String date;
    public Category( String categoryName, double categoryBalance) {
        this.categoryName = categoryName;
        this.categoryBalance = categoryBalance;


    }

    public Category() {

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getCategoryBalance() {
        return categoryBalance;
    }

    public void setCategoryBalance(double categoryBalance) {
        this.categoryBalance = categoryBalance;
    }




}
