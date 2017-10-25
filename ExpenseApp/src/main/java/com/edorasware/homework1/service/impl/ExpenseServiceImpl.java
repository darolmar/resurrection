package com.edorasware.homework1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edorasware.homework1.model.Expense;
import com.edorasware.homework1.repositories.ExpenseRepository;
import com.edorasware.homework1.service.ExpenseService;



@Service("expenseService")
@Transactional
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenseRepository;	
	
	public Expense findById(Integer id) {
		return expenseRepository.findOne(id);
	}

	public List<Expense> findByDescription(String desc) {
		return expenseRepository.findByDescription(desc);
	}

	public void saveExpense(Expense Expense) {
		expenseRepository.save(Expense);
	}

	public void updateExpense(Expense Expense){
		saveExpense(Expense);
	}

	public void deleteExpenseById(Integer id){
		expenseRepository.delete(id);
	}

	public void deleteAllExpenses(){
		expenseRepository.deleteAll();
	}

	public List<Expense> findAllExpenses(){
		return expenseRepository.findAll();
	}

	public boolean isExpenseExist(Expense expense) {
		if (expense.getId()==null){
			return false;
		}else{
			return findById(expense.getId()) != null;
		}
	}
}
