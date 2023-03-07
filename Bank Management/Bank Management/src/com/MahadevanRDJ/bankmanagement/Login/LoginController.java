package com.MahadevanRDJ.bankmanagement.Login;

import com.MahadevanRDJ.bankmanagement.DTOs.Manager;
import com.MahadevanRDJ.bankmanagement.DTOs.User;

public class LoginController implements LoginControllerCallBack, LoginModelControllerCallBack {
    private LoginModelCallBack loginModel;
    private LoginViewCallBack loginView;

    public LoginController(LoginViewCallBack loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModel(this);
    }

    @Override
    public void checkAdmin(String adminName, String adminPassword) {
        loginModel.checkAdmin(adminName, adminPassword);
    }

    @Override
    public void adminLoginSucceed() {
        loginView.adminLoginSucceed();
    }

    @Override
    public void adminLoginFailed() {
        loginView.adminLoginFailed();
    }

    @Override
    public void addUser(String firstName, String lastName, String userName, String password) {
        loginModel.addUser(firstName, lastName, userName, password);
    }

    @Override
    public void checkUser(String userName, String password) {
       loginModel.checkUser(userName, password);
    }

    @Override
    public void userLoginFailed() {
       loginView.userLoginFailed();
       
    }

    @Override
    public void userLoginSucceed(User user) {
        loginView.userLoginSucceed(user);
    }

    @Override
    public boolean toResetPassword(String userName) {
       return loginModel.toResetPassword(userName);
    
    }

    @Override
    public void resetPassword(String userName, String password) {
        loginModel.resetPassword(userName, password);
    }

    @Override
    public void userAddedSuccessfully() {
        loginView.userAddedSuccessfully();
    }

    @Override
    public void managerLogin(String name, String password) {
        loginModel.managerLogin(name, password);
    }

    @Override
    public void welcomeManager(Manager manager) {
        loginView.welcomeManager(manager);
    }

    @Override
    public void managerNotFound() {
        loginView.managerNotFound();
    }

    
}