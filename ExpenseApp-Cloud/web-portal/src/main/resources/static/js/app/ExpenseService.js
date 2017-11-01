'use strict';

angular.module('expenseApp').factory('ExpenseService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllExpenses: loadAllExpenses,
                getAllExpenses: getAllExpenses,
                getExpense: getExpense,
                createExpense: createExpense,
                updateExpense: updateExpense,
                removeExpense: removeExpense
            };

            return factory;

            function loadAllExpenses() {
                console.log('Fetching all expenses');
                var deferred = $q.defer();
                $http.get(urls.EXPENSE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all expenses');
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading users');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllExpenses(){
                return $localStorage.users;
            }

            function getExpense(id) {
                console.log('Fetching Expense with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.EXPENSE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Expense with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading expense with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createExpense(expense) {
                console.log('Creating Expense');
                var deferred = $q.defer();
                $http.post(urls.EXPENSE_SERVICE_API, expense)
                    .then(
                        function (response) {
                            loadAllExpenses();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Expense : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateExpense(expense, id) {
                console.log('Updating Expense with id '+id);
                var deferred = $q.defer();
                $http.put(urls.EXPENSE_SERVICE_API + id, expense)
                    .then(
                        function (response) {
                            loadAllExpenses();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Expense with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeExpense(id) {
                console.log('Removing Expense with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.EXPENSE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllExpenses();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Expense with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);