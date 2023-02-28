package com.MahadevanRDJ.carparkingmanagement.Customer;


public interface CusotmerModelCallBack {

    void addCustomer(String customerName, String gender, long contact, String city);

    void addCar(String carName, String carNumber);
    
}
