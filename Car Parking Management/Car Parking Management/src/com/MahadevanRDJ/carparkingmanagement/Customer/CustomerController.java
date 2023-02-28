package com.MahadevanRDJ.carparkingmanagement.Customer;

import com.MahadevanRDJ.carparkingmanagement.DTOs.Car;
import com.MahadevanRDJ.carparkingmanagement.DTOs.Customer;

public class CustomerController implements CustomerControllerCallBack, CustomerModelControllerCallBack {
    private CustomerViewCallBack customerView;
    private CusotmerModelCallBack customerModel;


    public CustomerController(CustomerViewCallBack customerView) {
        this.customerView = customerView;
        this.customerModel = new CustomerModel(this);
    }

    @Override
    public void addCustomer(String customerName, String gender, long contact, String city) {
       customerModel.addCustomer(customerName, gender, contact, city);
    }

    @Override
    public void displayCustomerDetails(Customer customer) {
        customerView.displayCustomerDetails(customer);
    }

    @Override
    public void addCar(String carName, String carNumber) {
       customerModel.addCar(carName, carNumber);
    }

    @Override
    public void displayCarDetails(Car car) {
       customerView.displayCarDetails(car);
    }



    
   
    
}
