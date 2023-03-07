package com.MahadevanRDJ.bankmanagement.Account;

public interface AccountControllerCallBack {

    void getAccount(int accountNumber);

    boolean checkAccount(int accountNumber);

    void transferMoney(int sourceAccountNumber, int targetAccountNumber, int amount);

    void setPin(int accountNumber, int pin);

    void depositMoney(int accountNumber, int amount);

    void withDrawMoney(int pin, int accountNumber);

    void transactionHistory(int accountNumber);
    
}
