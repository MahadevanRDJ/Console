package com.MahadevanRDJ.expensetracker.DTOs;

public class Wallet {
    private String walletName;
    private static int amount;
     
    public Wallet(String walletName, int amount) {
        this.walletName = walletName;
        Wallet.amount = amount;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public static int getAmount() {
        return amount;
    }
    
    public static void setAmount(int amount) {
        Wallet.amount = amount;
    }
}
