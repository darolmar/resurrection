package com.edorasware.microservices.expense.service;

import java.util.List;

import com.edorasware.microservices.expense.model.Expense;

public interface ExpenseService {

	Expense findById(Integer id);

	List<Expense> findByDescription(String desc);

	void saveExpense(Expense expense);

	void updateExpense(Expense expense);

	void deleteExpenseById(Integer id);

	void deleteAllExpenses();

	List<Expense> findAllExpenses();

	boolean isExpenseExist(Expense expense);

}
