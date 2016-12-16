'use strict';

var pa165carPark = angular.module('pa165carPark', ['ngRouter', 'carParkControllers']);
var carParkControllers = angular.module('carParkControllers', []);

<!-- routing -->

pa165carPark.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/cars', {templateUrl: '/templates/cars.html', controller: 'carCtrl'}).
            otherwise({redirectTo: '/'});
    }]);

pa165carPark.run(function ($rootScope) {
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

<!-- controllers -->

carParkControllers.controller('carCtrl', function ($scope) {
    $scope.headingTitle = "Car list";
});

