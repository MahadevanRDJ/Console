package com.MahadevanRDJ.expensetracker.Wallet;


import com.MahadevanRDJ.expensetracker.DTOs.Wallet;
import com.MahadevanRDJ.expensetracker.Transactions.TransactionsView;
import com.MahadevanRDJ.expensetracker.util.Validate;

public class WalletView implements WalletViewCallBack {
    private WalletControllerCallBack walletController;
    private boolean isWalletInitialized = false;
    public WalletView() {
        this.walletController = new WalletController(this);
    }
    public static void main(String[] args) {
        WalletView wView = new WalletView();
        wView.init();
    }
    public void init() {
        int choice;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Initialize wallet");
            System.out.println("2. Display Wallet");
            System.out.println("3. Remove Wallet");
            System.out.println("4. Add Transaction");
            choice = getInt();
            switch(choice) {
                case 1: initiateWallet(); break;
                case 2: if(isWalletInitialized) displayWallet(); break;
                case 3: if(isWalletInitialized) removeWallet(); break;
                case 4: if(isWalletInitialized) transactions(); break;
                case 5: System.exit(0);
                default : System.out.println("Invalid choice");
            }
        }while(choice != 4);

    }

    private void initiateWallet() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-----------------------WALLET INITIALIZATION--------------------");
        System.out.println("Wallet Name: " );
        String name = getString();
        System.out.println("Initial Amount: ");
        int amount = getInt();
        if(!Validate.check(amount)) initiateWallet();
        walletController.initiate(name, amount);
        isWalletInitialized = true;
    }
    public void displayWallet() {
        walletController.displayWallet();
    }
    private void removeWallet() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-------------------------Remove Wallet--------------------------");
        walletController.removeWallet();
    }
    private String getString () {
        return Validate.getString();
    }
    private int getInt () {
        return Validate.getInt();
    }

    @Override
    public void displayWallet(Wallet wallet) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("------------------------Wallet Details--------------------------");
        System.out.println("Wallet Name: " + wallet.getWalletName());
        System.out.println("Wallet Amount: " + Wallet.getAmount());
    }
    private void transactions() {
        TransactionsView tView = new TransactionsView();
        tView.init();
    }
}
