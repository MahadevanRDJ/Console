package com.MahadevanRDJ.bankmanagement.Registration;

import com.MahadevanRDJ.bankmanagement.BankRepository.BankRepository;
import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public class RegistrationModel implements RegistrationModelCallBack {
    private RegistrationModelControllerCallBack registrationController;
    public RegistrationModel(RegistrationModelControllerCallBack registrationController) {
        this.registrationController = registrationController;
    }
    @Override
    public void addCustomer(String firstName, String lastName, int age, String gender, String city, String district, long contact) {
        int customerID = BankRepository.getInstance().addCustomer(firstName, lastName, age, gender, city, district, contact);
        registrationController.resgistrationSuccess(customerID);
    }
    @Override
    public void displayCustomer(int customerID) {
        Customer customer = BankRepository.getInstance().getCustomer(customerID);
        if(customer != null) {
            registrationController.showCustomer(customer);
        } else {
            registrationController.customerNotFound();
        }
    }
    
}
interface RegistrationModelControllerCallBack {

    void resgistrationSuccess(int customerID);

    void customerNotFound();

    void showCustomer(Customer customer);

}