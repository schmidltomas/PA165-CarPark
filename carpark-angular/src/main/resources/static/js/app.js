'use strict';

var app = angular.module('app', ['ngRoute','ngResource', 'ui.bootstrap']);

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
            controller: 'homeController',
            templateUrl: '/templates/home.html'
        });
});

app.run(function ($rootScope, $location, $http) {
    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    };
//    $rootScope.$on('$locationChangeStart', function (event, next, current) {
//        // redirect to login page if not logged in and trying to access a restricted page
//        var restrictedPage = $.inArray($location.path(), ['/pa165']) === -1;
//        var loggedIn = $rootScope.globals.currentUser;
//        console.log($rootScope.globals.currentUser);
//        if (restrictedPage && !loggedIn) {
//            $location.path('/pa165');
//        }
//    });
});
