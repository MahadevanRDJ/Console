package com.MahadevanRDJ.bankmanagement.Account;

import java.util.List;

import com.MahadevanRDJ.bankmanagement.BankRepository.BankRepository;
import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Transactions;

public class AccountModel implements AccountModelCallBack {
    private AccountModelControllerCallBack accountController;

    public AccountModel(AccountModelControllerCallBack accountController) {
        this.accountController = accountController;
    }

    @Override
    public void getAccount(int accountNumber) {
        Account account = BankRepository.getInstance().getAccount(accountNumber);
        if (account != null) {
            accountController.showAccountsDetails(account);
        } else {
            accountController.accountNotFound();
        }
    }

    @Override
    public boolean checkAccount(int accountNumber) {
        Account account = BankRepository.getInstance().getAccount(accountNumber);
        if (account != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void transferMoney(int sourceAccountNumber, int targetAccountNumber, int amount) {
        int transferred = BankRepository.getInstance().transferMoney(sourceAccountNumber, targetAccountNumber, amount);
        if(transferred != -1) {
            accountController.transferSuccessful(transferred);
        } else {
            accountController.transferFailed();
        }
    }

    @Override
    public void setPin(int accountNumber, int pin) {
        BankRepository.getInstance().setPin(accountNumber, pin);
        accountController.pinSetSuccess();
    }

    @Override
    public void depositMoney(int accountNumber, int amount) {
        BankRepository.getInstance().depositMoney(accountNumber, amount);
        accountController.depositedMoney(amount);
    }

    @Override
    public void withDrawMoney(int accountNumber, int amount) {
        int withdrawn = BankRepository.getInstance().withDrawMoney(accountNumber, amount);
        if(withdrawn != -1) {
            accountController.withDrawMoney(withdrawn);
        } else {
            accountController.inSufficientMoney();
        }
    }

    @Override
    public void transactionHistory(int accountNumber) {
        List<Transactions> transactions = BankRepository.getInstance().getTransactions(accountNumber);
        accountController.showTransactionHistory(transactions);
        
    }
    
}
interface AccountModelControllerCallBack {

    void showAccountsDetails(Account account);

    void pinSetSuccess();

    void transferFailed();

    void transferSuccessful(int transferred);

    void depositedMoney(int amount);

    void withDrawMoney(int withdrawn);

    void inSufficientMoney();

    void showTransactionHistory(List<Transactions> transactions);

    void accountNotFound();

}