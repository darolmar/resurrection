package com.edorasware.homework1;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration 
public class CreatingExpenseSteps {
	  @Autowired
	  private TestRestTemplate restTemplate;
	  
	  //Inputs
	  private String description;
	  private Double value;
	  private Date date;
	// output
	  private ResponseEntity<String> response; 
	  @Given("I use a description (.*), a value (.*) and a date (.*)")
	  public void getExpenseData(String description, Double value, Date date) {
	    this.description = description;
	    this.value = value;
	    this.date = date;
	  }
	  @When("I request an expense creation")
	  public void requestExpenseCreation() {
		  HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		  MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
		  map.add("desc", this.description);
		  map.add("value", this.value);
		  map.add("date", this.date);
		  HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
		  response = restTemplate.postForEntity( "/expense/", request , String.class );
		  }	  
	  @Then("I should get a response with HTTP status code (.*)")
	  public void shouldGetResponseWithHttpStatusCode(int statusCode) {
	    assertThat(response.getStatusCodeValue()).isEqualTo(statusCode);
	  }	  
	  @And("The response should contain the message (.*)")
	  public void theResponseShouldContainTheMessage(String message) {
	    assertThat(response.getBody()).isEqualTo(message);
	  }
}
