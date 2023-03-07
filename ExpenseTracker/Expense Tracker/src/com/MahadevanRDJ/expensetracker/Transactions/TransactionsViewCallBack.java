package com.MahadevanRDJ.expensetracker.Transactions;

import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.expensetracker.DTOs.Transactions;

public interface TransactionsViewCallBack {

    void showExpenseList(List<String> expenseList);

    void showIncomeList(List<String> incomeList);

    void showDebtList(List<String> debtList);

    void debtNotFound();

    void showDebt(String debtName);

    void incomeNotFound();

    void showIncome(String incomeName);

    void expenseNotFound();

    void showExpense(String expenseName);

    void monthNotFound();

    void showMonthTransactions(int month, Map<Integer, List<Transactions>> monthTransactions);

    void failedToRemoveTransaction();

    void removedSuccessfully();

    
    
}
