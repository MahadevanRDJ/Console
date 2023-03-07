package com.MahadevanRDJ.expensetracker.Transactions;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.expensetracker.DTOs.Transactions;

public class TransactionsController implements TransactionsControllerCallBack, TransactionsModelControllerCallBack {
    private TransactionsViewCallBack transactionsView;
    private TransactionsModelCallBack transactionsModel;
    public TransactionsController(TransactionsView transactionsView) {
        this.transactionsView = transactionsView;
        this.transactionsModel = new TransactionsModel(this);
    }
    @Override
    public void getExpenseList() {
       transactionsModel.getExpenseList();
    }
    @Override
    public void getDebtList() {
       transactionsModel.getDebtList();
    }
    @Override
    public void getIncomeList() {
        transactionsModel.getIncomeList();
    }
    @Override
    public void showExpenseList(List<String> expenseList) {
        transactionsView.showExpenseList(expenseList);
    }
    @Override
    public void showIncomeList(List<String> incomeList) {
        transactionsView.showIncomeList(incomeList);
    }
    @Override
    public void showDebtList(List<String> debtList) {
        transactionsView.showDebtList(debtList);
    }
    @Override
    public void getExpense(int expense) {
        transactionsModel.expense(expense);    
    }
    @Override
    public void getIncome(int income) {
        transactionsModel.income(income);
    }
    @Override
    public void getDebt(int debt) {
        transactionsModel.debt(debt);
    }
    @Override
    public void debtNotFound() {
        transactionsView.debtNotFound();
    }
    @Override
    public void showDebt(String debtName) {
        transactionsView.showDebt(debtName);
    }
    @Override
    public void incomeNotFound() {
        transactionsView.incomeNotFound();
    }
    @Override
    public void showIncome(String incomeName) {
        transactionsView.showIncome(incomeName);
    }
    @Override
    public void expenseNotFound() {
        transactionsView.expenseNotFound();
    }
    @Override
    public void showExpense(String expenseName) {
        transactionsView.showExpense(expenseName);
    }
    @Override
    public void addTransaction(int i, String debtName, int amount, LocalDate transactionDate) {
       transactionsModel.addTransaction(i, debtName, amount, transactionDate);
    }
    @Override
    public void getMonthTransactions(int month) {
       transactionsModel.getMonthTransactions(month);
    }
    @Override
    public void monthNotFound() {
        transactionsView.monthNotFound();        
    }
    @Override
    public void showMonthTransactions(int month, Map<Integer, List<Transactions>> monthTransactions) {
        transactionsView.showMonthTransactions(month, monthTransactions);
    }
    @Override
    public void removeTransaction(int transactionId) {
        transactionsModel.removeTransaction(transactionId);
    }
    @Override
    public void failedToRemoveTransaction() {
        transactionsView.failedToRemoveTransaction();
    }
    @Override
    public void removedSuccessfully() {
        transactionsView.removedSuccessfully();
    }

}
