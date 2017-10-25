package com.edorasware.homework1.service;

import java.util.List;

import com.edorasware.homework1.model.Expense;

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
