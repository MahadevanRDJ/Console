package com.MahadevanRDJ.carparkingmanagement.Login;

import com.MahadevanRDJ.carparkingmanagement.DTOs.User;

public interface LoginViewCallBack {

    void adminLoginSucceed();

    void adminLoginFailed();

    void userLoginFailed();

    void userLoginSucceed(User user);
    
}
