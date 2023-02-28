package com.MahadevanRDJ.expensetracker.Wallet;

public interface WalletModelCallBack {

    void initiate(String name, int amount);

    void removeWallet();

    void displayWallet();
    
}
