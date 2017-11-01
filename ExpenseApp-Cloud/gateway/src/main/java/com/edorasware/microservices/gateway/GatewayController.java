package com.edorasware.microservices.gateway;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.edorasware.microservices.expense.model.Expense;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/gateway")
@Api(value = "Expenses microservice", description="This API has a CRUD for expenses")
public class GatewayController {

	public static final Logger logger = LoggerFactory.getLogger(GatewayController.class);

	@Autowired
	RestTemplate restTemplate;

	// -------------------Retrieve All Expenses---------------------------------------------

	@RequestMapping(value = "/expense/", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieve all expenses", notes="Return all expenses")
	@HystrixCommand(fallbackMethod="fallbackMethod")
	public ResponseEntity<List<Expense>> listAllExpenses() {
		ParameterizedTypeReference<List<Expense>> responseType = new ParameterizedTypeReference<List<Expense>>() {};
		ResponseEntity<List<Expense>> response = restTemplate.exchange("/expense/", HttpMethod.GET, null, responseType);
		return response;
	}
	// -------------------Retrieve Single Expense------------------------------------------

	@RequestMapping(value = "/expense/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Find an expense", notes="Return an expense by Id")
	@HystrixCommand(fallbackMethod="fallbackMethod")
	public ResponseEntity<?> getExpense(@PathVariable("id") int id) {
		logger.info("Fetching Expense with id {}", id);
		ResponseEntity<?> response = restTemplate.getForEntity("/expense/" + id, String.class);;
		return response;
	}

	// -------------------Create a Expense-------------------------------------------

	@RequestMapping(value = "/expense/", method = RequestMethod.POST)
	@ApiOperation(value = "Create an expense", notes = "Create a new expense")
	@HystrixCommand(fallbackMethod="fallbackMethod")
	public ResponseEntity<?> createExpense(@RequestBody Expense expense, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Expense : {}", expense);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setLocation(ucBuilder.path("/api/expense/{id}").buildAndExpand(expense.getId()).toUri());
		  MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
		  map.add("expense", expense);
		  HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
		  ResponseEntity<?> response = restTemplate.postForEntity( "/expense/", request , String.class );
		  return response;
	}

	// ------------------- Update a Expense ------------------------------------------------

	@RequestMapping(value = "/expense/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update an expense", notes = "Update an expense by Id")
	@HystrixCommand(fallbackMethod="fallbackMethod")
	public ResponseEntity<?> updateExpense(@PathVariable("id") int id, @RequestBody Expense expense) {
		logger.info("Updating Expense with id {}", id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		
		Expense currentExpense = new Expense();
		currentExpense.setDescription(expense.getDescription());
		currentExpense.setValue(expense.getValue());
		currentExpense.setDate(expense.getDate());
		
		HttpEntity<Expense> requestUpdate = new HttpEntity<>(currentExpense, headers);
		ResponseEntity<?> response = restTemplate.exchange("/expense/" + id, HttpMethod.PUT, requestUpdate, Void.class);
		return response;
	}

	// ------------------- Delete a Expense-----------------------------------------

	@RequestMapping(value = "/expense/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete an expense", notes = "Delete an expense by Id")
	@HystrixCommand(fallbackMethod="fallbackMethod")
	public ResponseEntity<?> deleteExpense(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Expense with id {}", id);
		restTemplate.delete("/expense/" + id);
		return new ResponseEntity<Expense>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Expenses-----------------------------

	@RequestMapping(value = "/expense/", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete all expenses", notes = "Delete all expenses in the system")
	@HystrixCommand(fallbackMethod="fallbackMethod")
	public ResponseEntity<Expense> deleteAllExpenses() {
		logger.info("Deleting All Expenses");
		restTemplate.delete("/expense/");
		return new ResponseEntity<Expense>(HttpStatus.NO_CONTENT);
	}
	// ------------------ Fallback method ---------------------------
	public ResponseEntity<List<Expense>> fallbackMethod(){
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
}