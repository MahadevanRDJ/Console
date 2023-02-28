package com.MahadevanRDJ.carparkingmanagement.Customer;

import com.MahadevanRDJ.carparkingmanagement.DTOs.Car;
import com.MahadevanRDJ.carparkingmanagement.DTOs.Customer;

public interface CustomerViewCallBack {

    void init();

    void displayCustomerDetails(Customer customer);

    void displayCarDetails(Car car);

}
