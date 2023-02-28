package com.MahadevanRDJ.expensetracker.Transactions;

import java.time.LocalDate;

public interface TransactionsControllerCallBack {

    void doCategorize();

    void getExpenseList();

    void getDebtList();

    void getIncomeList();

    void getExpense(int expense);

    void getIncome(int income);

    void getDebt(int debt);

    void addTransaction(int i, String debtName, int amount, LocalDate transactionDate);

    void getMonthTransactions(int month);

    void removeTransaction(int transactionId);
    
}
