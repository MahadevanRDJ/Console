package com.MahadevanRDJ.expensetracker.Transactions;

import java.time.LocalDate;

public interface TransactionsModelCallBack {


    void getExpenseList();

    void getDebtList();

    void getIncomeList();

    void expense(int expense);

    void income(int income);

    void debt(int debt);

    void addTransaction(int i, String debtName, int amount, LocalDate transactionDate);

    void getMonthTransactions(int month);

    void removeTransaction(int transactionId);
    
}
