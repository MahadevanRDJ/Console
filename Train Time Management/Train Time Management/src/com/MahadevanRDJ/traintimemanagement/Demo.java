package com.MahadevanRDJ.traintimemanagement;

import java.util.Scanner;

public class Demo {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        choice = 0;
        while(!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Choice: ");
        } 
        choice = scanner.nextInt();
        System.out.println(choice);
    }
}