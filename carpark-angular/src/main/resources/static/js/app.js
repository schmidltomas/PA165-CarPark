'use strict';

var app = angular.module('app', ['ngRoute', 'ngResource', 'ui.bootstrap']);

app.config(function($routeProvider){
    $routeProvider
        .when('/pa165/cars',{
            templateUrl: '/templates/cars.html',
            controller: 'carsController'
        })
        .when('/pa165/employees',{
            templateUrl: '/templates/employees.html',
            controller: 'employeesController'
        })
        .when('/pa165/admins',{
            templateUrl: '/templates/admins.html',
            controller: 'adminsController'
        })
        .when('/pa165/reservations',{
            templateUrl: '/templates/reservations.html',
            controller: 'reservationsController'
        })
        .when('/pa165', {
            controller: 'loginController',
            templateUrl: '/templates/login.html'
        })
        .otherwise({
            templateUrl: '/templates/login.html'
        });
});

app.run(['$rootScope', '$location', '$http', '$timeout', function ($rootScope, $location, $http, $timeout) {
    $rootScope.loggedIn = false;

    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    };

    // triggered on every location change
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        var restrictedPages = ['/pa165/employees', '/pa165/admins', '/pa165/cars', '/pa165/reservations'];

        // if the user is not logged in and trying to access a restricted page, switch to login page
        if (isOnRestrictedPage() && !isLoggedIn()) {
            $location.path('/pa165');
        }

        function isOnRestrictedPage() {
            return restrictedPages.indexOf($location.path()) > 0;
        }

        function isLoggedIn() {
            return $rootScope.loggedIn;
        }
    });
}]);