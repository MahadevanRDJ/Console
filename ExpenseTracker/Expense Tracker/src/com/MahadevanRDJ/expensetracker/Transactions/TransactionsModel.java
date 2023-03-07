package com.MahadevanRDJ.expensetracker.Transactions;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.expensetracker.DTOs.Transactions;
import com.MahadevanRDJ.expensetracker.ExpenseTrackerRepository.ExpenseTrackerRepository;

public class TransactionsModel implements TransactionsModelCallBack {
    private TransactionsModelControllerCallBack transactionController;
    public TransactionsModel(TransactionsModelControllerCallBack transactionsController) {
        this.transactionController = transactionsController;
    }
    @Override
    public void getExpenseList() {
        List<String> expenseList = ExpenseTrackerRepository.getInstance().getExpenseList(); 
        transactionController.showExpenseList(expenseList);
    }
    @Override
    public void getDebtList() {
       List<String> debtList = ExpenseTrackerRepository.getInstance().getDebtList();
       transactionController.showDebtList(debtList);
    }
    @Override
    public void getIncomeList() {
        List<String> incomeList = ExpenseTrackerRepository.getInstance().getIncomeList();
        transactionController.showIncomeList(incomeList);
    }
    @Override
    public void expense(int expense) {
        String expenseName = ExpenseTrackerRepository.getInstance().getExpense(expense);
        if(expenseName != null) {
            transactionController.showExpense(expenseName);
        } else {
            transactionController.expenseNotFound();
        }
    }
    @Override
    public void income(int income) {
       String incomeName = ExpenseTrackerRepository.getInstance().getIncome(income);
       if(incomeName != null) {
            transactionController.showIncome(incomeName);
        } else {
            transactionController.incomeNotFound();
        }
    }
    @Override
    public void debt(int debt) {
        String debtName = ExpenseTrackerRepository.getInstance().getDebt(debt);
        if(debtName != null) {
            transactionController.showDebt(debtName);
        } else {
            transactionController.debtNotFound();
        }
    }
    @Override
    public void addTransaction(int i, String debtName, int amount, LocalDate transactionDate) {
        ExpenseTrackerRepository.getInstance().addTransaction(i, debtName, transactionDate, amount);
    }
    @Override
    public void getMonthTransactions(int month) {
        Map<Integer, List<Transactions>> monthTransactions = ExpenseTrackerRepository.getInstance().retunMonthTransactions(month);
        if(monthTransactions != null) {
            transactionController.showMonthTransactions(month, monthTransactions);
        } else {
            transactionController.monthNotFound();
        }
    }
    @Override
    public void removeTransaction(int transactionId) {
        boolean isRemoved = ExpenseTrackerRepository.getInstance().removeTransaction(transactionId);
        if(isRemoved) {
            transactionController.removedSuccessfully();
        } else {
            transactionController.failedToRemoveTransaction();
        }
    }
}
interface TransactionsModelControllerCallBack {

    void showExpenseList(List<String> expenseList);

    void failedToRemoveTransaction();

    void removedSuccessfully();

    void monthNotFound();

    void showMonthTransactions(int month, Map<Integer, List<Transactions>> monthTransactions);

    void debtNotFound();

    void showDebt(String debtName);

    void incomeNotFound();

    void showIncome(String incomeName);

    void expenseNotFound();

    void showExpense(String expenseName);

    void showIncomeList(List<String> incomeList);

    void showDebtList(List<String> debtList);

    
}