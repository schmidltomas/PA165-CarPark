'use strict';

var app = angular.module('app', ['ngRoute','ngResource', 'ui.bootstrap']);

app.config(function($routeProvider){
    $routeProvider
        .when('/cars',{
            templateUrl: '/templates/cars.html',
            controller: 'carsController'
        })
        .when('/employees',{
            templateUrl: '/templates/employees.html',
            controller: 'employeesController'
        })
        .when('/admins',{
            templateUrl: '/templates/admins.html',
            controller: 'adminsController'
        })
        .when('/reservations',{
            templateUrl: '/templates/reservations.html',
            controller: 'reservationsController'
        })
        .when('/', {
            controller: 'loginController',
            templateUrl: '/templates/login.html'
        })
//        .when('/login', {
//            controller: 'loginController',
//            templateUrl: '/templates/login.html'
//        })
        .otherwise({
            controller: 'homeController',
            templateUrl: '/templates/home.html'
        });
});

app.run(function ($rootScope) {
    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    };
});
