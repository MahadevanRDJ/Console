package com.MahadevanRDJ.bankmanagement.Manager;

import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public interface ManagerModelCallBack {

    void displayBranchAccounts(String branch);

    void displayAllAccounts();

    void displayCustomers();

    void generateAccount(Customer customer);
    
}
