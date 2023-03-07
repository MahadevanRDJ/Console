package com.MahadevanRDJ.carparkingmanagement.Customer;

import com.MahadevanRDJ.carparkingmanagement.CarParkingRepository.CarParkingRepository;
import com.MahadevanRDJ.carparkingmanagement.DTOs.Car;
import com.MahadevanRDJ.carparkingmanagement.DTOs.Customer;

public class CustomerModel implements CusotmerModelCallBack {
    private CustomerModelControllerCallBack customerController;
    public CustomerModel(CustomerModelControllerCallBack customerController) {
        this.customerController = customerController;
    }
    @Override
    public void addCustomer(String customerName, String gender, long contact, String city) {
        CarParkingRepository.getInstance().addCustomerDetails(customerName, gender, contact, city);
        customerController.displayCustomerDetails(CarParkingRepository.getInstance().getCustomer());
    }
    @Override
    public void addCar(String carName, String carNumber) {
        CarParkingRepository.getInstance().addCar(carName, carNumber);
        customerController.displayCarDetails(CarParkingRepository.getInstance().getCar());
    }

    
    
}
interface CustomerModelControllerCallBack {

    void displayCustomerDetails(Customer customer);

    void displayCarDetails(Car car);

}
