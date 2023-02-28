package com.MahadevanRDJ.bankmanagement.Account;

import java.util.List;

import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Transactions;

public class AccountController implements AccountControllerCallBack, AccountModelControllerCallBack {
    private AccountViewCallBack accountView;
    private AccountModelCallBack accountModel;

    public AccountController(AccountViewCallBack accountView) {
        this.accountView = accountView;
        this.accountModel = new AccountModel(this);
    }

    @Override
    public void getAccount(int accountNumber) {
        accountModel.getAccount(accountNumber);
    }

    @Override
    public void showAccountsDetails(Account account) {
        accountView.onAccount(account);
    }

    @Override
    public void accountNotFound() {
        accountView.accountNotFound();
    }

    @Override
    public boolean checkAccount(int accountNumber) {
        if(accountModel.checkAccount(accountNumber)) return true;
        return false; 
        
    }

    @Override
    public void transferMoney(int sourceAccountNumber, int targetAccountNumber, int amount) {
        accountModel.transferMoney(sourceAccountNumber,targetAccountNumber, amount);
    }

    @Override
    public void setPin(int accountNumber, int pin) {
        accountModel.setPin(accountNumber, pin);
    }

    @Override
    public void depositMoney(int accountNumber, int amount) {
        if(amount > 0 && amount < 30000) {
            accountModel.depositMoney(accountNumber, amount);
        } else {
            accountView.depositMoneyLimitExceeded();
        }
    }

    @Override
    public void withDrawMoney(int accountNumber, int amount) {
        accountModel.withDrawMoney(accountNumber, amount);
    }

    @Override
    public void transactionHistory(int accountNumber) {
        accountModel.transactionHistory(accountNumber);
    }

    @Override
    public void showTransactionHistory(List<Transactions> transactions) {
        accountView.showTransactionHistory(transactions);
    }

    @Override
    public void withDrawMoney(int withdrawn) {
        accountView.withDrawMoney(withdrawn);
    }

    @Override
    public void inSufficientMoney() {
        accountView.inSufficientMoney();
    }

    @Override
    public void depositedMoney(int amount) {
        accountView.depositedMoney(amount);
    }

    @Override
    public void transferFailed() {
        accountView.transferFailed();
    }

    @Override
    public void transferSuccessful(int transferred) {
        accountView.transferSuccessful(transferred);
    }

    
}
