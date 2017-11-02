var app = angular.module('expenseApp',['ui.router','ngStorage','pascalprecht.translate','ngCookies']);

app.constant('urls', {
    BASE: 'http://localhost:9090/ExpenseApp',
    EXPENSE_SERVICE_API : 'http://localhost:9090/ExpenseApp/api/expense/'
});

app.config(['$stateProvider', '$urlRouterProvider','$translateProvider',
    function($stateProvider, $urlRouterProvider, $translateProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: '/ExpenseApp',
                controller:'ExpenseController',
                controllerAs:'ctrl',
                resolve: {
                    expenses: function ($q, ExpenseService) {
                        console.log('Load all expenses');
                        var deferred = $q.defer();
                        ExpenseService.loadAllExpenses().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
      $urlRouterProvider.otherwise('/');
  	  $translateProvider.translations('en', {
  	    "EXPENSE": "Expense",
  	    "DESCRIPTION": "Description",
  	    "VALUE": "Value",
  	    "DATE": "Date",
  	    "BUTTON_EDIT": "Edit",
  	    "BUTTON_REMOVE": "Remove",
  	    "BUTTON_RESET": "Reset Form",
  	    "BUTTON_ADD": "Add",
  	    "BUTTON_UPDATE": "Update",
  	    "ENTER_DESC": "Enter expense description",
  	    "ENTER_VALUE": "Enter expense value",
  	    "ENTER_DATE": "Enter expense date (yyyy-MM-dd)",
  	    "LIST_EXPENSES": "List of Expenses"
  	  });
  	  $translateProvider.translations('es', {
  		    "EXPENSE": "Gasto",
  		    "DESCRIPTION": "Descripción",
  		    "VALUE": "Valor",
  		    "DATE": "Fecha",
  		    "BUTTON_EDIT": "Editar",
  		    "BUTTON_REMOVE": "Borrar",
  		    "BUTTON_RESET": "Limpiar formulario",
  		    "BUTTON_ADD": "Añadir",
  		    "BUTTON_UPDATE": "Actualizar",
  	  	    "ENTER_DESC": "Introduzca la descripción del gasto",
  	  	    "ENTER_VALUE": "Introduzca el valor del gasto",
  	  	    "ENTER_DATE": "Introduzca la fecha del gasto (yyyy-MM-dd)",
  	  	    "LIST_EXPENSES": "Lista de Gastos"
  	  });
  	  $translateProvider.preferredLanguage('es');
  	  $translateProvider.useCookieStorage();
}]);

app.controller('LanguageController', function ($scope, $translate) {
	  $scope.changeLanguage = function (key) {
	    $translate.use(key);
	  };
});	  