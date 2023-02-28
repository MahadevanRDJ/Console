package com.MahadevanRDJ.bankmanagement.Account;

import java.util.List;

import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Transactions;

public interface AccountViewCallBack {

    void onAccount(Account account);

    void accountNotFound();

    void depositMoneyLimitExceeded();

    void showTransactionHistory(List<Transactions> transactions);

    void withDrawMoney(int withdrawn);

    void inSufficientMoney();

    void depositedMoney(int amount);

    void transferFailed();

    void transferSuccessful(int transferred);
    
}
