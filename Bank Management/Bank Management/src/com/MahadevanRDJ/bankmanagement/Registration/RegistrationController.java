package com.MahadevanRDJ.bankmanagement.Registration;

import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public class RegistrationController implements RegistrationControllerCallBack, RegistrationModelControllerCallBack {
    private RegistrationModelCallBack registrationModel;
    private RegistrationViewCallBack registrationView;

    public RegistrationController(RegistrationViewCallBack registrationView) {
        this.registrationView = registrationView;
        this.registrationModel = new RegistrationModel(this);
    }

    @Override
    public void addCustomer(String firstName, String lastName, int age, String gender, String city, String district,
            long contact) {
        registrationModel.addCustomer(firstName, lastName, age, gender, city, district, contact);
    }

    @Override
    public void resgistrationSuccess(int customerID) {
        registrationView.resgistrationSuccess(customerID);
    }

    @Override
    public void displayCustomer(int customerID) {
        registrationModel.displayCustomer(customerID);
    }

    @Override
    public void customerNotFound() {
       registrationView.customerNotFound();
    }

    @Override
    public void showCustomer(Customer customer) {
        registrationView.showCustomer(customer);
    }

    
}
