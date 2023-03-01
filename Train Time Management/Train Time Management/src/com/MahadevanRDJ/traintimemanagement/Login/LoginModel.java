package com.MahadevanRDJ.traintimemanagement.Login;

import com.MahadevanRDJ.traintimemanagement.DTOs.Admin;
import com.MahadevanRDJ.traintimemanagement.DTOs.User;
import com.MahadevanRDJ.traintimemanagement.TrainRepository.TrainRepository;

public class LoginModel implements LoginModelCallBack {
    private LoginModelControllerCallBack loginController;
    
    public LoginModel(LoginModelControllerCallBack loginController) {
        this.loginController = loginController;
    }

    @Override
    public void checkAdmin(String adminName, String adminPassword) {
        Admin admin = TrainRepository.getInstance().adminLogin(adminName, adminPassword);
        if(admin != null) {
            loginController.adminLoginSucceed();
        } else {
            loginController.adminLoginFailed();
        }
    }

    @Override
    public void addUser(String firstName, String lastName, String userName, String password) {
        TrainRepository.getInstance().addUser(firstName, lastName, userName, password);
        loginController.userAddedSuccessfully();
    }


    @Override
    public void checkUser(String userName, String password) {
        User user = TrainRepository.getInstance().userLogin(userName, password);
        if(user != null) {
            loginController.userLoginSucceed(user);
        } else {
            loginController.userLoginFailed();
        }
        
    }

    @Override
    public boolean toResetPassword(String userName) {
        return TrainRepository.getInstance().checkUser(userName);
    }

    @Override
    public void resetPassword(String userName, String password) {
        TrainRepository.getInstance().resetPassword(userName, password);
    }

    
}
interface LoginModelControllerCallBack {

    void adminLoginSucceed();

    void userAddedSuccessfully();

    void userLoginFailed();

    void userLoginSucceed(User user);

    void adminLoginFailed();

}
