package com.MahadevanRDJ.carparkingmanagement.Login;

import com.MahadevanRDJ.carparkingmanagement.CarParkingRepository.CarParkingRepository;
import com.MahadevanRDJ.carparkingmanagement.DTOs.Admin;
import com.MahadevanRDJ.carparkingmanagement.DTOs.User;

public class LoginModel implements LoginModelCallBack {
    private LoginModelControllerCallBack loginController;
    
    public LoginModel(LoginModelControllerCallBack loginController) {
        this.loginController = loginController;
    }

    @Override
    public void checkAdmin(String adminName, String adminPassword) {
        Admin admin = CarParkingRepository.getInstance().getAdmin(adminName, adminPassword);
        if(admin != null) {
            loginController.adminLoginSucceed();
        } else {
            loginController.adminLoginFailed();
        }
    }

    @Override
    public void addUser(String firstName, String lastName, String userName, String password) {
        CarParkingRepository.getInstance().addUser(firstName, lastName, userName, password);
        loginController.userAddedSuccessfully();
    }

    @Override
    public void checkUser(String userName, String password) {
        User user = CarParkingRepository.getInstance().getUser(userName, password);
        if(user != null) {
            loginController.userLoginSucceed(user);
        } else {
            loginController.userLoginFailed();
        }
        
    }

    @Override
    public boolean toResetPassword(String userName) {
        return CarParkingRepository.getInstance().checkUserName(userName);
    }

    @Override
    public void resetPassword(String userName, String password) {
        CarParkingRepository.getInstance().resetPassword(userName, password);
    }

    @Override
    public void userAddedSuccessfully() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Welcome!. Registration successful.");
    }

    
}
interface LoginModelControllerCallBack {

    void adminLoginSucceed();

    void userAddedSuccessfully();

    void userLoginFailed();

    void userLoginSucceed(User user);

    void adminLoginFailed();

}
