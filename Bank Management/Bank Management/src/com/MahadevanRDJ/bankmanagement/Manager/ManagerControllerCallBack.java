package com.MahadevanRDJ.bankmanagement.Manager;

import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public interface ManagerControllerCallBack {

    void showBranchAccounts(String branch);

    void displayAllAccounts();

    void displayCustomers();

    void generateAccount(Customer customer);
    
}
