package com.MahadevanRDJ.bankmanagement.Manager;

import java.util.List;

import com.MahadevanRDJ.bankmanagement.BankRepository.BankRepository;
import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public class ManagerModel implements ManagerModelCallBack {
    private ManagerModelControllerCallBack managerController;
    public ManagerModel(ManagerModelControllerCallBack managerController) {
        this.managerController = managerController;
    }
    @Override
    public void displayBranchAccounts(String branch) {
        List<Account> branchedAccounts = BankRepository.getInstance().returnBranchAccounts(branch);
        if(branchedAccounts != null) { 
            managerController.showBranchAccounts(branchedAccounts);
        } else {
            managerController.branchNotFound();
        }
    }
    @Override
    public void displayAllAccounts() {
        List<Account> accounts = BankRepository.getInstance().returnAccounts();
        managerController.showAccounts(accounts);
        
    }
    @Override
    public void displayCustomers() {
        List<Customer> customers = BankRepository.getInstance().returnCustomers();
        managerController.showCustomers(customers);
    }
    @Override
    public void generateAccount(Customer customer) {
        BankRepository.getInstance().addAccount(customer);
        Account account = BankRepository.getInstance().returnAccount(customer.getCustomerID());
        if(account != null) {
            managerController.accountApproved(account);
        }
    }
}
interface ManagerModelControllerCallBack {

    void showBranchAccounts(List<Account> accounts);

    void accountApproved(Account account);

    void showCustomers(List<Customer> customers);

    void showAccounts(List<Account> accounts);

    void branchNotFound();

}