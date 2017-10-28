'use strict';

angular.module('expenseApp').controller('ExpenseController',
    ['ExpenseService', '$scope',  function( ExpenseService, $scope) {

        var self = this;
        self.expense = {};
        self.expenses=[];

        self.submit = submit;
        self.getAllExpenses = getAllExpenses;
        self.createExpense = createExpense;
        self.updateExpense = updateExpense;
        self.removeExpense = removeExpense;
        self.editExpense = editExpense;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.expense.id === undefined || self.expense.id === null) {
                console.log('Saving New Expense', self.expense);
                createExpense(self.expense);
            } else {
                updateExpense(self.expense, self.expense.id);
                console.log('Expense updated with id ', self.expense.id);
            }
        }

        function createExpense(expense) {
            console.log('About to create expense');
            ExpenseService.createExpense(expense)
                .then(
                    function (response) {
                        console.log('Expense created successfully');
                        self.successMessage = 'Expense created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.expense={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Expense');
                        self.errorMessage = 'Error while creating Expense: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateExpense(expense, id){
            console.log('About to update expense');
            ExpenseService.updateExpense(expense, id)
                .then(
                    function (response){
                        console.log('Expense updated successfully');
                        self.successMessage='Expense updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Expense');
                        self.errorMessage='Error while updating Expense '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeExpense(id){
            console.log('About to remove Expense with id '+id);
            ExpenseService.removeExpense(id)
                .then(
                    function(){
                        console.log('Expense '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing expense '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllExpenses(){
            return ExpenseService.getAllExpenses();
        }

        function editExpense(id) {
            self.successMessage='';
            self.errorMessage='';
            ExpenseService.getExpense(id).then(
                function (expense) {
                    self.expense = expense;
                },
                function (errResponse) {
                    console.error('Error while removing expense ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.expense={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);