package com.MahadevanRDJ.bankmanagement.Login;

public interface LoginControllerCallBack {

    void checkAdmin(String adminName, String adminPassword);

    void addUser(String firstName, String lastName, String displayName, String password);

    void checkUser(String userName, String password);

    boolean toResetPassword(String userName);

    void resetPassword(String username, String password);

    void managerLogin(String name, String password);
    
}
