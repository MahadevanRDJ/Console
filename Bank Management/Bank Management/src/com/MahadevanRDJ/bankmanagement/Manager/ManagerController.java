package com.MahadevanRDJ.bankmanagement.Manager;

import java.util.List;

import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public class ManagerController implements ManagerControllerCallBack, ManagerModelControllerCallBack {
    private ManagerModelCallBack  managerModel;
    private ManagerViewCallBack managerView;

    public ManagerController(ManagerViewCallBack managerView) {
        this.managerModel = new ManagerModel(this);
        this.managerView = managerView;
    }

    @Override
    public void showBranchAccounts(String branch) {
        managerModel.displayBranchAccounts(branch);
    }

    @Override
    public void showBranchAccounts(List<Account> accounts) {
        managerView.showBranchAccounts(accounts); 
    }

    @Override
    public void branchNotFound() {
        managerView.branchNotFound();
    }

    @Override
    public void displayAllAccounts() {
        managerModel.displayAllAccounts();
    }

    @Override
    public void showAccounts(List<Account> accounts) {
        managerView.showAccounts(accounts);
    }

    @Override
    public void displayCustomers() {
        managerModel.displayCustomers();
    }

    @Override
    public void showCustomers(List<Customer> customers) {
        managerView.showCustomers(customers);
    }

    @Override
    public void generateAccount(Customer customer) {
        managerModel.generateAccount(customer);
    }

    @Override
    public void accountApproved(Account account) {
        managerView.accountApproved(account);
    }
    
}
