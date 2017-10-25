Feature: Create an expense
As a user of the ExpenseApp I should be able to create an expense
Scenario: Create an expense given a description, a value and a date
	Given I use a description <desc>, a value <value> and a date <date>
	When I request an expense creation
	Then I should get a response with HTTP status code 201
	And The response should contain the message Created