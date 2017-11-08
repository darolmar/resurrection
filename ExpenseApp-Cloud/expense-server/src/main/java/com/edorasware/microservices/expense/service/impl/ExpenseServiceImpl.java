package com.edorasware.microservices.expense.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edorasware.microservices.expense.model.Expense;
import com.edorasware.microservices.expense.repositories.ExpenseRepository;
import com.edorasware.microservices.expense.service.ExpenseService;



@Service("expenseService")
@Transactional
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository expenseRepository;	
	
	public Optional<Expense> findById(Integer id) {
		return expenseRepository.findById(id);
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
		expenseRepository.deleteById(id);
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
