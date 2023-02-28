package com.MahadevanRDJ.bankmanagement.Registration;

import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public interface RegistrationViewCallBack {

    void resgistrationSuccess(int customerID);

    void customerNotFound();

    void showCustomer(Customer customer);
    
}
