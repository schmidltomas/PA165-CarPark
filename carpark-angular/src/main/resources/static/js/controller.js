//app.controller('carsController', function($scope) {
//    $scope.headingTitle = "Car List";
//});

app.controller('employeesController', function($scope) {
    $scope.headingTitle = "Employee List";
});

app.controller('carsController', function($scope, $http) {
    $scope.headingTitle = "One car";
    $http.get('/pa165/rest/car/getById?id=1').success(function(data) {
        $scope.headingTitle = "One car succesfully loaded";
        $scope.id = data.id;
        $scope.brand = data.brand;
        $scope.seats = data.seats;
        $scope.evidence_number = data.evidence_number;
        $scope.fuel_type = data.fuel_type;
        $scope.fuel_consumption = data.fuel_consumption;
        $scope.home_location = data.home_location;
        $scope.current_location = data.current_location;
    })
});

app.controller('AdminNewEmployeeCtrl',
    function ($scope, $http, $location, $rootScope) {
        //set object bound to form fields
        $scope.employee = {
            'firstName': '',
            'secondName': '',
            'email': '',
            'username': '',
            'password': ''
        };
        // function called when submit button is clicked, creates product on server
        $scope.create = function (employee) {
            $http({
                method: 'POST',
                url: '/pa165/rest/employee/create',
                data: employee
            }).then(function success(response) {
                console.log('employee registered');
                var createdEmployee = response.data;
                //display confirmation alert
                $rootScope.successAlert = 'A new employee "'+createdEmployee.username+'" was regostered';
                //change view to list of products
                $location.path("/employees");
            }, function error(response) {
                //display error
                $scope.errorAlert = 'Cannot register employee !';
                $location.path("/cars");
            });
        };
    });
