package com.MahadevanRDJ.expensetracker.Wallet;

public interface WalletControllerCallBack {

    void initiate(String name, int amount);

    void removeWallet();

    void displayWallet();
    
}
