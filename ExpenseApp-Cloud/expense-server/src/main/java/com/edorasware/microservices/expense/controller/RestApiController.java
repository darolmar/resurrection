package com.edorasware.microservices.expense.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edorasware.microservices.expense.model.Expense;
import com.edorasware.microservices.expense.service.ExpenseService;
import com.edorasware.microservices.expense.util.CustomErrorType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Expenses microservice", description="This API has a CRUD for expenses")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	ExpenseService expenseService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Expenses---------------------------------------------

	@RequestMapping(value = "/expense/", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieve all expenses", notes="Return all expenses")
	public ResponseEntity<List<Expense>> listAllExpenses() {
		List<Expense> expenses = expenseService.findAllExpenses();
		if (expenses.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
	}

	// -------------------Retrieve Single Expense------------------------------------------

	@RequestMapping(value = "/expense/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Find an expense", notes="Return an expense by Id")
	public ResponseEntity<?> getExpense(@PathVariable("id") int id) {
		logger.info("Fetching Expense with id {}", id);
		Expense expense = expenseService.findById(id);
		if (expense == null) {
			logger.error("Expense with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Expense with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}

	// -------------------Create a Expense-------------------------------------------

	@RequestMapping(value = "/expense/", method = RequestMethod.POST)
	@ApiOperation(value = "Create an expense", notes = "Create a new expense")
	public ResponseEntity<?> createExpense(@RequestBody Expense expense, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Expense : {}", expense);

		if (expenseService.isExpenseExist(expense)) {
			logger.error("Unable to create. A Expense with description {} already exist", expense.getDescription());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Expense with description " + 
			expense.getDescription() + " already exist."),HttpStatus.CONFLICT);
		}
		expenseService.saveExpense(expense);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/expense/{id}").buildAndExpand(expense.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Expense ------------------------------------------------

	@RequestMapping(value = "/expense/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update an expense", notes = "Update an expense by Id")
	public ResponseEntity<?> updateExpense(@PathVariable("id") int id, @RequestBody Expense expense) {
		logger.info("Updating Expense with id {}", id);

		Expense currentExpense = expenseService.findById(id);

		if (currentExpense == null) {
			logger.error("Unable to update. Expense with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Expense with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentExpense.setDescription(expense.getDescription());
		currentExpense.setValue(expense.getValue());
		currentExpense.setDate(expense.getDate());

		expenseService.updateExpense(currentExpense);
		return new ResponseEntity<Expense>(currentExpense, HttpStatus.OK);
	}

	// ------------------- Delete a Expense-----------------------------------------

	@RequestMapping(value = "/expense/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete an expense", notes = "Delete an expense by Id")
	public ResponseEntity<?> deleteExpense(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Expense with id {}", id);

		Expense expense = expenseService.findById(id);
		if (expense == null) {
			logger.error("Unable to delete. Expense with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Expense with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		expenseService.deleteExpenseById(id);
		return new ResponseEntity<Expense>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Expenses-----------------------------

	@RequestMapping(value = "/expense/", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete all expenses", notes = "Delete all expenses in the system")
	public ResponseEntity<Expense> deleteAllExpenses() {
		logger.info("Deleting All Expenses");

		expenseService.deleteAllExpenses();
		return new ResponseEntity<Expense>(HttpStatus.NO_CONTENT);
	}

}