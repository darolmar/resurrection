var app = angular.module('expenseApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:9090/ExpenseApp',
    EXPENSE_SERVICE_API : 'http://localhost:9090/ExpenseApp/api/expense/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'ExpenseController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, ExpenseService) {
                        console.log('Load all expenses');
                        var deferred = $q.defer();
                        ExpenseService.loadAllExpenses().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

