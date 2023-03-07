package com.MahadevanRDJ.bankmanagement.Login;

import com.MahadevanRDJ.bankmanagement.BankRepository.BankRepository;
import com.MahadevanRDJ.bankmanagement.DTOs.Admin;
import com.MahadevanRDJ.bankmanagement.DTOs.Manager;
import com.MahadevanRDJ.bankmanagement.DTOs.User;

public class LoginModel implements LoginModelCallBack {
    private LoginModelControllerCallBack loginController;
    
    public LoginModel(LoginModelControllerCallBack loginController) {
        this.loginController = loginController;
    }

    @Override
    public void checkAdmin(String adminName, String adminPassword) {
        Admin admin = BankRepository.getInstance().getAdmin(adminName, adminPassword);
        if(admin != null) {
            loginController.adminLoginSucceed();
        } else {
            loginController.adminLoginFailed();
        }
    }

    @Override
    public void addUser(String firstName, String lastName, String userName, String password) {
        BankRepository.getInstance().addUser(firstName, lastName, userName, password);
        loginController.userAddedSuccessfully();
    }


    @Override
    public void checkUser(String userName, String password) {
        User user = BankRepository.getInstance().getUser(userName, password);
        if(user != null) {
            loginController.userLoginSucceed(user);
        } else {
            loginController.userLoginFailed();
        }
        
    }

    @Override
    public boolean toResetPassword(String userName) {
        return BankRepository.getInstance().checkUserName(userName);
    }

    @Override
    public void resetPassword(String userName, String password) {
        BankRepository.getInstance().resetPassword(userName, password);
    }

    @Override
    public void managerLogin(String name, String password) {
        Manager manager = BankRepository.getInstance().checkManager(name, password);
        if(manager != null) { 
            loginController.welcomeManager(manager);
        } else {
            loginController.managerNotFound();
        }
    }

    
}
interface LoginModelControllerCallBack {

    void adminLoginSucceed();

    void managerNotFound();

    void welcomeManager(Manager manager);


    void userAddedSuccessfully();

    void userLoginFailed();

    void userLoginSucceed(User user);

    void adminLoginFailed();

}
