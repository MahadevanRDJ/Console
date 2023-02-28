package com.MahadevanRDJ.bankmanagement.Manager;

import java.util.List;

import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public interface ManagerViewCallBack {

    void showBranchAccounts(List<Account> accounts);

    void branchNotFound();

    void showAccounts(List<Account> accounts);

    void showCustomers(List<Customer> customers);

    void accountApproved(Account account);
    
}
