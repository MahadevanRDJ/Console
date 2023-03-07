package com.MahadevanRDJ.expensetracker.Login;

import com.MahadevanRDJ.expensetracker.DTOs.Admin;
import com.MahadevanRDJ.expensetracker.DTOs.User;
import com.MahadevanRDJ.expensetracker.ExpenseTrackerRepository.ExpenseTrackerRepository;

public class LoginModel implements LoginModelCallBack {
    private LoginModelControllerCallBack loginController;
    
    public LoginModel(LoginModelControllerCallBack loginController) {
        this.loginController = loginController;
    }

    @Override
    public void checkAdmin(String adminName, String adminPassword) {
        Admin admin = ExpenseTrackerRepository.getInstance().getAdmin(adminName, adminPassword);
        if(admin != null) {
            loginController.adminLoginSucceed();
        } else {
            loginController.adminLoginFailed();
        }
    }

    @Override
    public void addUser(String firstName, String lastName, String userName, String password) {
        ExpenseTrackerRepository.getInstance().addUser(firstName, lastName, userName, password);
        loginController.userAddedSuccessfully();
    }


    @Override
    public void checkUser(String userName, String password) {
        User user = ExpenseTrackerRepository.getInstance().getUser(userName, password);
        if(user != null) {
            loginController.userLoginSucceed(user);
        } else {
            loginController.userLoginFailed();
        }
        
    }

    @Override
    public boolean toResetPassword(String userName) {
        return ExpenseTrackerRepository.getInstance().checkUserName(userName);
    }

    @Override
    public void resetPassword(String userName, String password) {
        ExpenseTrackerRepository.getInstance().resetPassword(userName, password);
    }

    
}
interface LoginModelControllerCallBack {

    void adminLoginSucceed();

    void userAddedSuccessfully();

    void userLoginFailed();

    void userLoginSucceed(User user);

    void adminLoginFailed();

}
