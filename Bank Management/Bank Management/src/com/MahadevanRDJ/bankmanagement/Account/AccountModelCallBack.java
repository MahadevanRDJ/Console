package com.MahadevanRDJ.bankmanagement.Account;

public interface AccountModelCallBack {

    void getAccount(int accountNumber);

    boolean checkAccount(int accountNumber);

    void transferMoney(int accountNumber, int amount, int amount2);

    void setPin(int accountNumber, int pin);

    void depositMoney(int accountNumber, int amount);

    void withDrawMoney(int accountNumber, int amount);

    void transactionHistory(int accountNumber);
    
}
