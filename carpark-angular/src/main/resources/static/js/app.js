'use strict';

var app = angular.module('app', ['ngRoute', 'ngResource', 'ui.bootstrap']);

app.config(function($routeProvider, $locationProvider){
    $locationProvider.html5Mode(true);
    $routeProvider
        .when('/pa165/admin/cars',{
            templateUrl: '/templates/admin-cars.html',
            controller: 'carsController'
        })
        .when('/pa165/admin/employees',{
            templateUrl: '/templates/employees.html',
            controller: 'employeesController'
        })
        .when('/pa165/admin/admins',{
            templateUrl: '/templates/admins.html',
            controller: 'adminsController'
        })
        .when('/pa165/admin/reservations',{
            templateUrl: '/templates/admin-reservations.html',
            controller: 'reservationsController'
        })
        .when('/pa165', {
            templateUrl: '/templates/login.html',
            controller: 'loginController'
        })
        .when('/pa165/employee/cars', {
            templateUrl: '/templates/employee-cars.html',
            controller: 'carsController'
        })
        .when('/pa165/employee/reservations', {
            templateUrl: '/templates/employee-reservations.html',
            controller: 'reservationsController'
        })
        .otherwise({
            redirectTo: '/pa165'
        });
});

app.run(['$rootScope', '$location', '$http', '$timeout', function ($rootScope, $location, $http, $timeout) {
    $rootScope.loggedIn = false;
    $rootScope.isAdmin = false;

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
        //var restrictedPages = ['/pa165/admin/employees', '/pa165/admin/admins', '/pa165/admin/cars', '/pa165/admin/reservations'];

        // redirect after page refresh
        if (!isLoggedIn()) {
            $location.path('/pa165');
        }

        // if the user is not logged in and trying to access a restricted page, switch to login page
        if ((isOnRestrictedPage() && !isAdmin()) || (!isOnRestrictedPage() && isAdmin())) {
            $location.path('/pa165');
            $rootScope.loggedIn = false;
            $rootScope.isAdmin = false;
            $rootScope.currentUser = undefined;
        }

        function isAdmin() {
            return $rootScope.isAdmin;
        }

        function isOnRestrictedPage() {
            return $location.path().indexOf('admin') > 0;
            //return restrictedPages.indexOf($location.path()) > 0;
        }

        function isLoggedIn() {
            return $rootScope.loggedIn;
        }
    });
}]);