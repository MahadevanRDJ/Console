package com.MahadevanRDJ.bankmanagement.Registration;

public interface RegistrationControllerCallBack {

    void addCustomer(String firstName, String lastName, int age, String gender, String city, String district,
            long contact);

    void displayCustomer(int customerID);    
}
