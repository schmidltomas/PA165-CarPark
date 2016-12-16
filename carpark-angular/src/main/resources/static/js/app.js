'use strict';

var app = angular.module('app', ['ngRoute','ngResource']);
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
        .when('/admin/newemployee', {
            templateUrl: '/templates/new_employee.html', 
            controller: 'newEmployeeController'})
        .when('/admin/newcar', {
            templateUrl: '/templates/new_car.html', 
            controller: 'newCarController'})
        .otherwise({
            redirectTo: '/'
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
