package com.MahadevanRDJ.expensetracker.Transactions;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.MahadevanRDJ.expensetracker.DTOs.Transactions;
import com.MahadevanRDJ.expensetracker.util.Validate;

public class TransactionsView implements TransactionsViewCallBack {
    private TransactionsControllerCallBack transactionController;
    private boolean isCategorized = false;
    private Scanner scanner = new Scanner(System.in);

    public TransactionsView() {
        this.transactionController = new TransactionsController(this);
    }
    public static void main(String[] args) {
        TransactionsView tv = new TransactionsView();
        tv.init();
    }
    public void init() {
        if(!isCategorized) doCategorize();
        System.out.println("----------------------------------------------------------------\n");
        System.out.println("-------------------------Add Transaction------------------------\n");
        int choice;
        do{
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Expenses");
            System.out.println("2. Debts");
            System.out.println("3. Incomes");
            System.out.println("4. Display Wallet");
            System.out.println("5. Remove");
            System.out.println("6. Month Transaction");
            System.out.println("Choice : ");
            choice = scanner.nextInt();
            if(!Validate.check(choice)) init();
            switch(choice) {
                case 1: addExpense(); break;
                case 2: addDebt(); break;
                case 3: addIncome(); break;
                case 4: Validate.getWallet(); break;
                case 5: removeTransactions(); break;
                case 6: getMonthTransactions(); break;
                case 7: System.exit(0);
                default : System.out.println("Invalid");
            }
        }while(choice != 7);
    }

    private void removeTransactions() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-----------------------Remove Transaction-----------------------");
        System.out.println("Transaction ID : ");
        int transactionId = getInput();
        transactionController.removeTransaction(transactionId);
    }
    private void getMonthTransactions() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Month : ");
        int month = getInput();
        transactionController.getMonthTransactions(month);
    }
    private void doCategorize() {
        transactionController.doCategorize();
        isCategorized = true;
    }

    private void getExpenseList() { 
        transactionController.getExpenseList();
    }
    
    private void getDebtList() {
        transactionController.getDebtList();
    }
    private void getIncomeList() {
        transactionController.getIncomeList();
    }
    private void addExpense() {
        getExpenseList();
        System.out.println("Select Expense in above list");
        int expense = scanner.nextInt();
        if(!Validate.check(expense)) addExpense();
        transactionController.getExpense(expense);
    }
    private void addIncome() {
        getIncomeList();
        System.out.println("Select Income in above list.");
        int income = scanner.nextInt();
        if(!Validate.check(income)) addIncome();
        transactionController.getIncome(income);
    }
    private void addDebt() {
        getDebtList();
        System.out.println("Select Debt in above list.");
        int debt = scanner.nextInt();
        if(!Validate.check(debt)) addDebt();
        transactionController.getDebt(debt);
    }
    private int getInput() {
        int input = scanner.nextInt();
        if(!Validate.check(input)) getInput();
        return input;
    }
    private LocalDate getTransactionDate() {
        System.out.println("Date Format: -> DD:MM:YY");
        System.out.println("Date :");
        int date = getInput();
        System.out.println("Month :");
        int month = getInput();
        System.out.println("Year :");
        int year = getInput();
        LocalDate tDate = LocalDate.of(year, month, date);
        return tDate;
    }
    @Override
    public void showExpenseList(List<String> expenseList) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------EXPENSES----------------------------");
        for (int i = 0; i < expenseList.size(); i++) {
            System.out.println((i + 1) + "\t" + expenseList.get(i));
        }
    }
    @Override
    public void showIncomeList(List<String> incomeList) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------INCOMES----------------------------");
        for (int i = 0; i < incomeList.size(); i++) {
            System.out.println((i + 1) + "\t" + incomeList.get(i));
        }
    }
    @Override
    public void showDebtList(List<String> debtList) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------DEBTS-----------------------------");
        for (int i = 0; i < debtList.size(); i++) {
            System.out.println((i + 1) + "\t" + debtList.get(i));
        }
    }
    @Override
    public void debtNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Debt not found");
    }
    @Override
    public void showDebt(String debtName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Debt : " + debtName);
        System.out.println("Amount :");
        int amount = getInput();
        LocalDate transactionDate = getTransactionDate();
        transactionController.addTransaction(1, debtName, amount, transactionDate);
    }
    @Override
    public void incomeNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Income not in the list try others"); 
    }
    @Override
    public void showIncome(String incomeName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Income : " + incomeName);
        System.out.println("Amount :");
        int amount = getInput();
        LocalDate transactionDate = getTransactionDate();
        transactionController.addTransaction(0, incomeName, amount, transactionDate);
    }
    @Override
    public void expenseNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Expense not in the list try others");
    }
    @Override
    public void showExpense(String expenseName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Income : " + expenseName);
        System.out.println("Amount :");
        int amount = getInput();
        LocalDate transactionDate = getTransactionDate();
        transactionController.addTransaction(1, expenseName, amount, transactionDate);
        
    }
    @Override
    public void monthNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Month you've choosen is not available in the database.");
    }
    @Override
    public void showMonthTransactions(int month, Map<Integer, List<Transactions>> monthTransactions) {
        System.out.println("----------------------------------------------------------------");
        String transactionMonth = getMonth(month);
        System.out.println("-------------------- " + transactionMonth + " ------------------------");
        for (Map.Entry<Integer, List<Transactions>> entry : monthTransactions.entrySet()) { 
            if(entry.getValue().size() > 0) {
                for (Transactions transactions : entry.getValue()) {
                    transactions.display();
                }
                return;
            }
        }
    }
    private String getMonth(int month) {
        LocalDate current = LocalDate.now();
        if (current.getMonthValue() == month) { 
            return "THIS MONTH";
        } else if(current.getMonthValue() > month) {
            return "PREVIOUS MONTH";
        } else {
            return "FUTURE MONTH";
        }
    }
    @Override
    public void failedToRemoveTransaction() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("You've been trying to remove the invalid transaction");
    }
    @Override
    public void removedSuccessfully() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Your transaction has been successfully removed");
    }
}
