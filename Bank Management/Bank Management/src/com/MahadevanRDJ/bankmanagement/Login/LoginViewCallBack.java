package com.MahadevanRDJ.bankmanagement.Login;

import com.MahadevanRDJ.bankmanagement.DTOs.Manager;
import com.MahadevanRDJ.bankmanagement.DTOs.User;

public interface LoginViewCallBack {

    void adminLoginSucceed();

    void adminLoginFailed();

    void userLoginFailed();

    void userLoginSucceed(User user);

    void userAddedSuccessfully();

    void welcomeManager(Manager manager);

    void managerNotFound();
    
}
