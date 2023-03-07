package com.MahadevanRDJ.carparkingmanagement.Login;



import com.MahadevanRDJ.carparkingmanagement.DTOs.User;
import com.MahadevanRDJ.carparkingmanagement.SlotFill.SlotFillView;
import com.MahadevanRDJ.carparkingmanagement.SlotFill.SlotFillViewCallBack;
import com.MahadevanRDJ.carparkingmanagement.util.Validate;

public class LoginView implements LoginViewCallBack {
    private LoginControllerCallBack loginController;

    public LoginView() {
        this.loginController = new LoginController(this);
    }
    
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.init();
    }

    private void init() {
        int choice;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Welcome to Car Parking Management System");
            System.out.println("1. Admin Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Sign In");
            System.out.println("4. Forgot Password");
            System.out.println("5. EXIT");
            System.out.println("Choice : ");
            choice = getInteger();

            switch(choice) {
                case 1: adminLogin(); break;
                case 2: signUp(); break;
                case 3: signIn(); break;
                case 4: forgotPassword();break;
                case 5: System.exit(0);
                default: System.out.println("Invalid choice");
            }

        } while(choice != 5);
    }

    private int getInteger() {
        return Validate.getInt();
    }

    private void adminLogin() {
        System.out.println("Admin Name: ");
        String adminName = getString();
        System.out.println("Password: ");
        String adminPassword = getString();
        if(!Validate.verifyPassWord(adminPassword)) adminLogin();
        loginController.checkAdmin(adminName, adminPassword);
    }

    private void signUp() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Sign Up: ");
        System.out.println("First Name: ");
        String firstName = getString();
        System.out.println("Last Name: ");
        String lastName = getString();
        System.out.println("Display Name: ");
        String userName = getString();
        System.out.println("Password: ");
        String password = getString();
        if(!Validate.verifyPassWord(password)) signUp();
        loginController.addUser(firstName, lastName, userName, password);

    }

    private void signIn() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Sign In :");
        System.out.println("User name : " );
        String userName = getString();
        System.out.println("Password : ");
        String password = getString();
        loginController.checkUser(userName, password);
    }

    private void forgotPassword() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Reset Password : ");
        System.out.println("----------------------------------------------------------------");
        System.out.println("To reset your password, we require your USERNAME to check your credentials.");

        System.out.println("User Name : ");
        String userName = getString();
        if(loginController.toResetPassword(userName)) {
            System.out.println("---------Reset Password--------- ");
            System.out.println("New Password : ");
            String password = getString();
            loginController.resetPassword(userName, password);
        } else {
            System.out.println("User not found");
            forgotPassword();
        }
    }
    private String getString() {
        return Validate.getString();
    }
    @Override
    public void adminLoginSucceed() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome Admin!");
        SlotFillViewCallBack sView = new SlotFillView();
        sView.init();
    }

    @Override
    public void adminLoginFailed() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("You're not a admin!");
        init();
    }

    @Override
    public void userLoginFailed() {
        System.out.println("Sign in failed!");
        System.out.println("Invalid username or password!");
        System.out.println("Reset Password? [Yes/NO]");
        System.out.println("Choice: ");
        if(getString().equalsIgnoreCase("yes")) forgotPassword();
        else init();
    }

    @Override
    public void userLoginSucceed(User user) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome " + user.getUserFirstName() + "!.");
        SlotFillViewCallBack sView = new SlotFillView();
        sView.init();

    }


}

