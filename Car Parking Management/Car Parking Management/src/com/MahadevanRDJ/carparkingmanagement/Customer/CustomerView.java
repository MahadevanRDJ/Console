package com.MahadevanRDJ.carparkingmanagement.Customer;

import java.util.Scanner;

import com.MahadevanRDJ.carparkingmanagement.DTOs.Car;
import com.MahadevanRDJ.carparkingmanagement.DTOs.Customer;
import com.MahadevanRDJ.carparkingmanagement.Payment.PaymentView;
import com.MahadevanRDJ.carparkingmanagement.Payment.PaymentViewCallBack;

public class CustomerView implements CustomerViewCallBack {
    private Scanner scanner = new Scanner(System.in);
    private CustomerControllerCallBack customerController;
    private boolean isCustomerDetailed = false;
    private boolean isCarDetailed = true;
    public CustomerView() {
        this.customerController = new CustomerController(this);
    }
    public void init() {
        int choice;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Customer Details");
            System.out.println("2. Car Details");
            System.out.println("3. Pay");
            System.out.println("4. EXIT");
            System.out.println("Choice : ");
            choice = scanner.nextInt();
            switch(choice) {
                case 1:  customerDetails(); break;
                case 2:  carDetails(); break;
                case 3 : if(isCustomerDetailed && isCarDetailed) payDetails(); break;
                case 4: System.exit(0);
                default : System.out.println("Invalid choice");
            }
        } while(choice != 4);
    }

    private void customerDetails() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Customer Details : ");
        System.out.println("Customer Name : ");
        String customerName = scanner.next();
        System.out.println("Gender : ");
        String gender = scanner.next();
        System.out.println("Contact : ");
        long contact = scanner.nextLong();
        System.out.println("City : ");
        String city = scanner.next();

        customerController.addCustomer(customerName, gender, contact, city);
        isCustomerDetailed = true;

    }
    private void carDetails() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Car Details : ");
        System.out.println("Car Name");
        String carName = scanner.next(); 
        System.out.println("Car Number");
        String carNumber = scanner.next();
        customerController.addCar(carName, carNumber);
        isCarDetailed = true;
    }

    private void payDetails() {
        System.out.println("----------------------------------------------------------------");
        PaymentViewCallBack pView = new PaymentView();
        pView.init();
    }
    @Override
    public void displayCustomerDetails(Customer customer) {
        System.out.println("-----------Customer Details-----------");
        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Customer Name: " + customer.getCustomerName());
        System.out.println("Gender: " + customer.getGender());
        System.out.println("Contact: " + customer.getContact());
        System.out.println("City: " + customer.getCity());
    }
    @Override
    public void displayCarDetails(Car car) {
       System.out.println("----------------Car Details----------------");
       System.out.println("Car Number: " + car.getCarNumber().toUpperCase());
       System.out.println("Car Name: " + car.getCarName().toUpperCase());

    }

}
