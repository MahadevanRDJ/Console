package com.MahadevanRDJ.traintimemanagement.Login;

import com.MahadevanRDJ.traintimemanagement.DTOs.User;

public interface LoginViewCallBack {

    void adminLoginSucceed();

    void adminLoginFailed();

    void userLoginFailed();

    void userLoginSucceed(User user);

    void userAddedSuccessfully();
    
}
