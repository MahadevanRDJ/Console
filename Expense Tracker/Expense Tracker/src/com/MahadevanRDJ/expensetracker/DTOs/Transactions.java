package com.MahadevanRDJ.expensetracker.DTOs;

import java.time.LocalDate;
import java.util.Random;

public class Transactions {
    private int transactionID;
    private String transactionName;
    private int transactionCategory;
    private LocalDate transactionDate;
    private int amount;
    
    public Transactions(int transactionCategory, String transactionName, LocalDate transactionDate, int amount) {
        this.transactionID = new Random().nextInt(1000, 2000);
        this.transactionCategory = transactionCategory;
        this.transactionName = transactionName;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }
    public String getTransactionName() {
        return transactionName;
    }
    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getTransactionCategory() {
        return transactionCategory;
    }
    public void setTransactionCategory(int transactionCategory) {
        this.transactionCategory = transactionCategory;
    }
    
    public void display() {
        System.out.println("--------------------------------");
        System.out.println("Transaction ID : " + transactionID);
        System.out.println("Transaction Date: " + transactionDate);
        System.out.println("Transaction Name: " + transactionName);
        System.out.println("Transaction Amount : " + amount);
    }
    
}
