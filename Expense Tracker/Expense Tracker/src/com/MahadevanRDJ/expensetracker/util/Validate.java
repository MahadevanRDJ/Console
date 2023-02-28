package com.MahadevanRDJ.expensetracker.util;

import com.MahadevanRDJ.expensetracker.Wallet.WalletView;
import java.util.Scanner;

public class Validate {
    private static Scanner scanner = new Scanner(System.in);

    public static boolean check(Integer value) {
        if (value.getClass().getName().equals("I"))
            return false;
        return true;
    }

    public static boolean verifyPassWord(String passWord) {
        if (passWord.length() < 8)
            return false;
        return true;
    }

    public static String getString() {
        String string = scanner.next();
        return string;
    }

    public static void getWallet() {
        WalletView wView = new WalletView();
        wView.displayWallet();
    }
    public static int getInt() {
        return Integer.parseInt(getString());
    }
    public static void main(String[] args) {
        String value = "hello";
        value = value.toUpperCase();
        System.out.println(value);
    }
}
