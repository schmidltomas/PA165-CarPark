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
        .when('/admins',{
            templateUrl: '/templates/admins.html',
            controller: 'adminsController'
        })
        .when('/reservations',{
            templateUrl: '/templates/reservations.html',
            controller: 'reservationsController'
        })
        .when('/admin/newemployee', {
            templateUrl: '/templates/new_employee.html', 
            controller: 'newEmployeeController'})
        .when('/admin/newcar', {
            templateUrl: '/templates/new_car.html',
            controller: 'newCarController'})
        .when('/admin/newreservation', {
            templateUrl: '/templates/new_reservation.html',
            controller: 'newReservationController'})
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
