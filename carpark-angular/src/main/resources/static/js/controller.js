//app.controller('carsController', function($scope) {
//    $scope.headingTitle = "Car List";
//});

app.controller('employeesController', function($scope) {
    $scope.headingTitle = "Employee List";
});

app.controller('carsController', function($scope, $http, $rootScope) {
    $scope.headingTitle = "Car list";
    loadAllCars($http, $scope);
    $scope.deleteCar = function (car) {
        $http.delete('/pa165/rest/car/remove/' + car.id).then(function () {
            loadAllCars($http, $scope);},
            function error(response) {
                console.log("failed to remove car " + car.id);
                console.log(response);
                $rootScope.errorAlert = 'Cannot remove reserved car!';
            });
    };
});

function loadAllCars($http, $scope) {
    $http.get('/pa165/rest/car/getAll').success(function(data) {
        $scope.cars = data;
    });
}

app.controller('newEmployeeController',
    function ($scope, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.employee = {
            'firstName': '',
            'secondName': '',
            'email': '',
            'username': '',
            'password': ''
        };
        
        $scope.create = function (employee) {
            $http({
                method: 'POST',
                url: '/pa165/rest/employee/create',
                data: employee
            }).then(function success(response) {
                console.log('employee registered');
                var createdEmployee = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new employee "'+createdEmployee.username+'" was registered';
                //change view 
                $location.path("/employees");
            }, function error(response) {
                //display error
                $scope.errorAlert = 'Cannot register employee !';
            });
        };
    });

app.controller('newCarController',
    function ($scope, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.car = {
            'evidence_number': '',
            'brand': '',
            'fuel_type': 'Petrol',
            'fuel_consumption': 0,
            'seats': 1,
            'home_location': '',
            'current_location': ''            
        };
        
        $scope.create = function (car) {
            $http({
                method: 'POST',
                url: '/pa165/rest/car/create',
                data: car
            }).then(function success(response) {
                console.log('car registered');
                var createdCar = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new car "'+createdCar.evidence_number+'" was registered';
                //change view 
                $location.path("/cars");
            }, function error(response) {
                //display error
                $scope.errorAlert = 'Cannot register car !';
            });
        };
    });

app.controller('reservationsController', function($scope, $http) {
    $scope.headingTitle = "One reservation";
    $http.get('/pa165/rest/reservation/getAll').success(function(data) {
        $scope.headingTitle = "All reservations";
        $scope.reservations = data;
    })
});

app.controller('newReservationController',
    function ($scope, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.reservation = {
            'employee': '',
            'car': '',
            'startDate': 0,
            'endDate': 0,
            'distance': 1,
            'purpose': '',
        };

        $scope.create = function (reservation) {
            $http({
                method: 'POST',
                url: '/pa165/rest/reservation/create',
                data: reservation
            }).then(function success(response) {
                console.log('reservation created');
                var createdReservation = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new reservation "'+createdReservation.id+'" was created';
                //change view 
                $location.path("/reservations");
            }, function error(response) {
                //display error
                $scope.errorAlert = 'Cannot create reservation !';
            });
        };
    });