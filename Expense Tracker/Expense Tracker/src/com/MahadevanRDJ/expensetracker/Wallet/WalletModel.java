package com.MahadevanRDJ.expensetracker.Wallet;

import com.MahadevanRDJ.expensetracker.DTOs.Wallet;
import com.MahadevanRDJ.expensetracker.ExpenseTrackerRepository.ExpenseTrackerRepository;

public class WalletModel implements WalletModelCallBack {
    private WalletModelControllerCallBack walletController;
    public WalletModel(WalletModelControllerCallBack walletController) {
        this.walletController = walletController;
    }

    @Override
    public void initiate(String name, int amount) {
       ExpenseTrackerRepository.getInstance().initiateWallet(name, amount);
       returnWallet();
    }

    private void returnWallet() {
        Wallet wallet = ExpenseTrackerRepository.getInstance().getWallet();
        walletController.displayWallet(wallet);
    }

    @Override
    public void removeWallet() {
       ExpenseTrackerRepository.getInstance().removeWallet();
    }

    @Override
    public void displayWallet() {
        returnWallet();
    }
    

    
}
interface WalletModelControllerCallBack {

    void displayWallet(Wallet wallet);

}