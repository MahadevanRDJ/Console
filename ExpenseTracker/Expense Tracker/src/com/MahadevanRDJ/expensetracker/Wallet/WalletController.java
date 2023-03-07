package com.MahadevanRDJ.expensetracker.Wallet;

import com.MahadevanRDJ.expensetracker.DTOs.Wallet;

public class WalletController implements WalletControllerCallBack, WalletModelControllerCallBack {
    private WalletViewCallBack walletView;
    private WalletModelCallBack walletModel;
    public WalletController(WalletViewCallBack walletView) {
        this.walletView = walletView;
        this.walletModel = new WalletModel(this);
    }
    @Override
    public void initiate(String name, int amount) {
        walletModel.initiate(name, amount);
    }
    @Override
    public void displayWallet(Wallet wallet) {
        walletView.displayWallet(wallet);
    }
    @Override
    public void removeWallet() {
       walletModel.removeWallet();
    }
    @Override
    public void displayWallet() {
        walletModel.displayWallet();
    }
    
    
}
