package com.MahadevanRDJ.expensetracker.Login;

import com.MahadevanRDJ.expensetracker.DTOs.User;

public interface LoginViewCallBack {

    void adminLoginSucceed();

    void adminLoginFailed();

    void userLoginFailed();

    void userLoginSucceed(User user);

    void userAddedSuccessfully();
    
}
