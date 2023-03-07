package com.MahadevanRDJ.expensetracker.ExpenseTrackerRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

import com.MahadevanRDJ.expensetracker.DTOs.Admin;
import com.MahadevanRDJ.expensetracker.DTOs.Transactions;
import com.MahadevanRDJ.expensetracker.DTOs.User;
import com.MahadevanRDJ.expensetracker.DTOs.Wallet;

public class ExpenseTrackerRepository {
    private static ExpenseTrackerRepository expenseInstance;

    private List<Admin> admin = new ArrayList<Admin>();
    private List<User> users = new ArrayList<User>();
    private List<String> expenses = new ArrayList<String>();
    private List<String> debts = new ArrayList<String>();
    private List<String> incomes = new ArrayList<String>();
    private List<Map<Integer, List<Transactions>>> transactions = new ArrayList<>();
    private List<Transactions> currentDayTransactions = new ArrayList<>();
    private Wallet wallet;
    private Transactions currenTransactions;

    private ExpenseTrackerRepository() {
    }

    static {
        ExpenseTrackerRepository.getInstance().defaultAdmins();
        ExpenseTrackerRepository.getInstance().expenseList();
        ExpenseTrackerRepository.getInstance().debtList();
        ExpenseTrackerRepository.getInstance().incomeList();
    }

    public static ExpenseTrackerRepository getInstance() {
        if (expenseInstance == null) {
            expenseInstance = new ExpenseTrackerRepository();
            return expenseInstance;
        }
        return expenseInstance;
    }

    private void defaultAdmins() {
        admin.add(new Admin("Deva", "DevaRDJ3"));
        admin.add(new Admin("Zoho", "GSTenkasi"));
    }

    public Admin getAdmin(String adminName, String adminPassword) {
        for (Admin admin : admin) {
            if (adminName.equalsIgnoreCase(admin.getAdminName()) && adminPassword.equals(admin.getAdminPassword())) {
                return admin;
            }
        }
        return null;
    }

    public void addUser(String usersFirstname, String usersLastname, String usersName, String password) {
        users.add(new User(usersFirstname, usersLastname, usersName, password));
    }

    public User getUser(String usersName, String password) {
        for (User user : users) {
            if (usersName.equalsIgnoreCase(user.getUserName()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public boolean checkUserName(String usersName) {
        for (User user : users) {
            if (usersName.equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }

    public void resetPassword(String usersName, String password) {
        for (User user : users) {
            if (usersName.equals(user.getUserName())) {
                user.setPassword(password);
            }
        }
    }

    public void initiateWallet(String walletName, int amount) {
        wallet = new Wallet(walletName, amount);
    }

    public void removeWallet() {
        wallet = null;
    }

    public Wallet getWallet() {
        return wallet;
    }

    private void expenseList() {
        try {
            Scanner ipFile = new Scanner(
                    new File("E:\\Console\\Expense Tracker\\src\\com\\MahadevanRDJ\\expensetracker\\Expenses.txt"));
            while (ipFile.hasNextLine())
                expenses.add(ipFile.next());
            ipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void debtList() {
        debts.add("ReturnDebt");
        debts.add("EMI");
        debts.add("Loans");
        debts.add("Others");
    }

    private void incomeList() {
        incomes.add("Salary");
        incomes.add("Interests");
        incomes.add("Credited");
        incomes.add("Others");
    }

    public List<String> getExpenseList() {
        return expenses;
    }

    public List<String> getDebtList() {
        return debts;
    }

    public List<String> getIncomeList() {
        return incomes;
    }

    public String getExpense(int expense) {
        for (int i = 0; i < expenses.size(); i++) {
            if (i == expense - 1)
                return expenses.get(i);
        }
        return null;
    }

    public String getIncome(int income) {
        for (int i = 0; i < incomes.size(); i++) {
            if (i == income - 1)
                return incomes.get(i);
        }
        return null;
    }

    public String getDebt(int debt) {
        for (int i = 0; i < debts.size(); i++) {
            if (i == debt - 1)
                return debts.get(i);
        }
        return null;
    }

    public void initializeTransactionDatabase() {
        LocalDate now = LocalDate.now();
        now = now.of(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());
        for (int i = 0; i < 3; i++) {
            transactions.add(new LinkedHashMap<Integer, List<Transactions>>());
            int days = now.lengthOfMonth();
            for (int j = 1; j <= days; j++) {
                transactions.get(i).put(j, new ArrayList<>());
            }
            now = now.of(now.getYear(), (now.getMonthValue() + 1), now.getDayOfMonth());
        }
        isInitialized = true;
    }

    public void addTransaction(int i, String debtName, LocalDate transactionDate, int amount) {
        if (!isInitialized)
            initializeTransactionDatabase();
        currenTransactions = new Transactions(i, debtName, transactionDate, amount);
        currentDayTransactions.add(currenTransactions);
        transactions.get(transactionDate.getMonthValue() - 1).put(transactionDate.getDayOfMonth(),
                currentDayTransactions);
        if (i == 0) {
            int addAmount = Wallet.getAmount();
            addAmount += amount;
            Wallet.setAmount(addAmount);
        } else {
            int deduceAmount = Wallet.getAmount();
            deduceAmount -= amount;
            Wallet.setAmount(deduceAmount);
        }
    }

    public Map<Integer, List<Transactions>> retunMonthTransactions(int month) {
        for (int i = 0; i < transactions.size(); i++) {
            if (i == month - 1) {
                return transactions.get(month - 1);
            }
        }
        return null;
    }

    public boolean removeTransaction(int transactionID) {
        Transactions temp = null;
        for (Map<Integer, List<Transactions>> transaction : transactions) {
            for (Map.Entry<Integer, List<Transactions>> entry : transaction.entrySet()) {
                if (entry.getValue().size() > 0) {
                    for (Transactions currenTransactions : entry.getValue()) {
                        if (currenTransactions.getTransactionID() == transactionID) {
                            temp = currenTransactions;
                            deleteAmount(currenTransactions);
                            entry.getValue().remove(temp);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void deleteAmount(Transactions transactions) {
        if (transactions.getTransactionCategory() == 1) {
            int addMoney = Wallet.getAmount();
            addMoney += transactions.getAmount();
            Wallet.setAmount(addMoney);
        } else {
            int deduceAmount = Wallet.getAmount();
            deduceAmount -= transactions.getAmount();
            Wallet.setAmount(deduceAmount);
        }
    }
}
