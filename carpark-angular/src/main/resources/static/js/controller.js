app.controller('employeesController', function($scope, $http, $rootScope) {
    $scope.headingTitle = "Employee list";
    loadAllEmployees($http, $rootScope);
    $scope.deleteEmployee = function (employee) {
        $http.delete('/pa165/rest/employee/remove/' + employee.id).then(function () {
            loadAllEmployees($http, $rootScope);},
            function error(response) {
                console.log("failed to remove employee " + employee.id);
                console.log(response);
                $rootScope.errorAlert = 'Cannot remove employee with a reservation!';
            });
    };
});

app.controller('adminsController', function($scope, $http, $rootScope) {
    $scope.headingTitle = "Admin list";
    loadAllAdmins($http, $rootScope);
    $scope.deleteAdmin = function (admin) {
        $http.delete('/pa165/rest/admin/remove/' + admin.id).then(function () {
            loadAllAdmins($http, $rootScope);},
            function error(response) {
                console.log("failed to remove admin " + admin.id);
                console.log(response);
                $rootScope.errorAlert = 'Cannot remove admin!';
            });
    };
});

app.controller('carsController', function($scope, $http, $rootScope) {
    $scope.headingTitle = "Car list";
    loadAllCars($http, $rootScope);
    $scope.deleteCar = function (car) {
        $http.delete('/pa165/rest/car/remove/' + car.id).then(function () {
            loadAllCars($http, $rootScope);},
            function error(response) {
                console.log("failed to remove car " + car.id);
                console.log(response);
                $rootScope.errorAlert = 'Cannot remove reserved car!';
            });
    };
});

function loadCar($http, $rootScope, $scope){
    $http.get('/pa165/rest/car/getById' + $rootScope.car.id).success(function (data){
        var car = data;
    });
};

function loadAllCars($http, $rootScope) {
    $http.get('/pa165/rest/car/getAll').success(function(data) {
        $rootScope.cars = data;
    });
}

function loadAllEmployees($http, $rootScope) {
    $http.get('/pa165/rest/employee/getAll').success(function(data) {
        $rootScope.employees = data;
    });
}

function loadAllAdmins($http, $rootScope) {
    $http.get('/pa165/rest/admin/getAll').success(function(data) {
        $rootScope.admins = data;
    });
}

function loadAllReservations($http, $rootScope) {
    $http.get('/pa165/rest/reservation/getAll').success(function(data) {
        $rootScope.reservations = data;
    });
}

app.controller('reservationsController', function($scope, $http, $rootScope) {
    $scope.headingTitle = "All reservations";
    $scope.disabled = true;
    $scope.updateButtonText = "Update";

    $scope.removeReservation = function (id) {
        $http.delete('/pa165/rest/reservation/remove/'+id).success(function(data) {
            loadAllReservations($http, $rootScope);
            $rootScope.successAlert = 'Reservation "'+id+'" was deleted';
        })
    };

    $scope.updateReservation = function (reservation) {
        if ($scope.disabled) {
            $scope.disabled = false;
            $scope.updateButtonText = "Submit";
        } else {
            $scope.disabled = true;
            $scope.updateButtonText = "Update";
            console.log(reservation.purpose);
            $http({
                method: "PUT",
                url: "/pa165/rest/reservation/update",
                data: reservation
            }).then(function success(response) {
                console.log('reservation updated');
                var updatedReservation = response.data;
                //refresh reservations
                loadAllReservations($http, $rootScope);
                //display confirmation alert
                $rootScope.successAlert = 'Reservation "'+updatedReservation.id+'" was updated';
            }, function error(response) {
                //display error
                $rootScope.errorAlert = 'Cannot update reservation !';
            });
        }
    };
    loadAllReservations($http, $rootScope);
});

function updateCar($http, responseObject, $rootScope) {
    $http({
        method: 'POST',
        url: '/pa165/rest/car/update',
        data: responseObject
    }).then(function success(response) {
                console.log('car updated');
                var updatedcar = response.data;

                loadAllCars($http, $rootScope);
                
                $rootScope.successAlert = 'car "'+updatedcar.id+'" was updated';
            }, function error(response) {
                $rootScope.errorAlert = 'Cannot update car !';
            });
}

function createCar($http, responseObject, $rootScope) {
    $http({
        method: 'POST',
        url: '/pa165/rest/car/create',
        data: responseObject
    }).then(function success(response) {
        console.log('car registered');
        var createdCar = response.data;
        //display confirmation alert
        $rootScope.successAlert = 'A new car "'+createdCar.evidence_number+'" was registered';
        loadAllCars($http, $rootScope);
    }, function error(response) {
        //display error
        $rootScope.errorAlert = 'Cannot register car !';
    });
}

function createEmployee($http, employee, $rootScope) {
    $http({
        method: 'POST',
        url: '/pa165/rest/employee/create',
        data: employee
    }).then(function success(response) {
        console.log('employee registered');
        var createdEmployee = response.data;
        //display confirmation alert
        $rootScope.successAlert = 'A new employee "'+createdEmployee.username+'" was registered';
        loadAllEmployees($http, $rootScope);
    }, function error(response) {
        //display error
        $rootScope.errorAlert = 'Cannot register employee !';
    });
}

function createAdmin($http, admin, $rootScope) {
    $http({
        method: 'POST',
        url: '/pa165/rest/admin/create',
        data: admin
    }).then(function success(response) {
        console.log('admin added');
        var createdAdmin = response.data;
        $rootScope.successAlert = 'A new admin "' + createdAdmin.username + '" was registered';
        loadAllAdmins($http, $rootScope);
    }, function error(response) {
        $rootScope.errorAlert = 'Cannot create new admin!';
    });
}

function createReservation($http, reservation, $rootScope) {
    $http({
        method: 'POST',
        url: '/pa165/rest/reservation/create',
        data: reservation
    }).then(function success(response) {
        console.log('reservation created');
        var createdReservation = response.data;
        loadAllReservations($http, $rootScope);
        //display confirmation alert
        $rootScope.successAlert = 'A new reservation "'+createdReservation.id+'" was created';
    }, function error(response) {
        //display error
        $rootScope.errorAlert = 'Cannot create reservation !';
    });
}

app.service('sharedProperties', function () {
    var _car = {
        'evidence_number': '',
        'brand': '',
        'fuel_type': 'Petrol',
        'fuel_consumption': 0,
        'seats': 1,
        'home_location': '',
        'current_location': ''
    };

    var _employee = {
        'firstName': '',
        'secondName': '',
        'email': '',
        'username': '',
        'password': '',
        'userRole': 'ROLE_ADMIN'
    };

    var _admin = {
        'firstName': '',
        'secondName': '',
        'email': '',
        'username': '',
        'password': '',
        'userRole': 'ROLE_ADMIN'
    };

    var _reservation = {
        'employee': '',
        'car': '',
        'startDate': '',
        'endDate': '',
        'distance': '',
        'purpose': ''
    };

    var _loginResponse = {
        'authenticated': '',
        'userRole': ''
    };

    this.reservation = _reservation;
    this.admin = _admin;
    this.employee = _employee;
    this.car = _car;
    this.loginResponse = _loginResponse;
});

app.run(function($rootScope, sharedProperties) {
    $rootScope.getResponseObject = function(objectName) {
        switch (objectName) {
            case 'car':
                return sharedProperties.car;
            case 'employee':
                return sharedProperties.employee;
            case 'admin':
                return sharedProperties.admin;
            case 'reservation':
                return sharedProperties.reservation;
            case 'loginResponse':
                return sharedProperties.loginResponse;
        }
    }
});

app.controller('homeController', ['$scope', 'flashService', function($scope, flashService) {
    $rootScope.successAlert = 'Login successful!';
    //flashService.Success("Login successful!");
}]);

app.controller('submitController', ['$scope', '$rootScope', '$http', '$location', 'flashService',
    function($scope, $rootScope, $http, $location, flashService) {
    $scope.formModel = {};
    $scope.loginResponse = {};

    $scope.onSubmit = function() {
        $scope.formModel.dataLoading = true;
        $http.post('/pa165/rest/login', $scope.formModel).
            success(function (data) {
                $scope.loginResponse = data;
                console.log("login request successful")
                console.log($scope.loginResponse);
                if ($scope.loginResponse.authenticated == true) {
                    $scope.formModel.dataLoading = false;
                    $location.path("/cars");
                    $rootScope.successAlert = 'Login successful!';
                } else {
                    $scope.formModel.dataLoading = false;
                    flashService.Error("Login failed! Incorrect email or password.");
                }
            }).error(function (data) {
                console.log("login request failed");
            });
    };
}]);

app.service('flashService', ['$rootScope', function ($rootScope) {
    var service = {};

    service.Success = Success;
    service.Error = Error;

    initService();

    return service;

    function initService() {
        $rootScope.$on('$locationChangeStart', function () {
            clearFlashMessage();
        });

        function clearFlashMessage() {
            var flash = $rootScope.flash;
            if (flash) {
                if (!flash.keepAfterLocationChange) {
                    delete $rootScope.flash;
                } else {
                    // only keep for a single location change
                    flash.keepAfterLocationChange = false;
                }
            }
        }
    }

    function Success(message, keepAfterLocationChange) {
        $rootScope.flash = {
            message: message,
            type: 'success',
            keepAfterLocationChange: keepAfterLocationChange
        };
    }

    function Error(message, keepAfterLocationChange) {
        $rootScope.flash = {
            message: message,
            type: 'error',
            keepAfterLocationChange: keepAfterLocationChange
        };
    }
}]);

app.controller('loginController', function($scope, $location) {

});

app.controller('ModalController', function ($scope, $uibModal, $log, sharedProperties, $http, $rootScope) {
    $scope.init = function (type, objectName) {
       $scope.responseMethodName = type;
       $scope.objectName = objectName;
    };
    
    $scope.init = function (type, objectName, car) {
       $scope.responseMethodName = type;
       $scope.objectName = objectName;
       $rootScope.car = car;
    };

    var $ctrl = this;
    $ctrl.animationsEnabled = true;

    $ctrl.open = function (size) {
        var modalInstance = $uibModal.open({
            animation: $ctrl.animationsEnabled,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            size: size,
            resolve: {
                responseObject: function () {
                    return $rootScope.getResponseObject($scope.objectName);
                }
            }
        });

        modalInstance.result.then(function (responseObject) {
            var resultFunction = window[$scope.responseMethodName];
            if (typeof resultFunction === "function") resultFunction($http, responseObject, $rootScope);
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
    
    $ctrl.openCreate = function (size) {
        var modalInstance = $uibModal.open({
            animation: $ctrl.animationsEnabled,
            ariaLabelledBy: 'modal-title-create',
            ariaDescribedBy: 'modal-body-create',
            templateUrl: 'myModalContent-create.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            size: size,
            resolve: {
                responseObject: function () {
                    return $rootScope.getResponseObject($scope.objectName);
                }
            }
        });

        modalInstance.result.then(function (responseObject) {
            var resultFunction = window[$scope.responseMethodName];
            if (typeof resultFunction === "function") resultFunction($http, responseObject, $rootScope);
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
});

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

app.controller('ModalInstanceCtrl', function ($uibModalInstance, responseObject) {
    var $ctrl = this;
    $ctrl.responseObject = responseObject;

    $ctrl.ok = function (responseObject) {
        $uibModalInstance.close(responseObject);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

// Please note that the close and dismiss bindings are from $uibModalInstance.

app.component('modalComponent', {
    templateUrl: 'myModalContent.html',
    bindings: {
        resolve: '<',
        close: '&',
        dismiss: '&'
    },
    controller: function () {
        var $ctrl = this;

        $ctrl.$onInit = function () {
        };

        $ctrl.ok = function (responseObject) {
            $ctrl.close(responseObject);
        };

        $ctrl.cancel = function () {
            $ctrl.dismiss({$value: 'cancel'});
        };
    }
});

var navbar = [
  '<nav class="navbar navbar-default">',
  '<div class="container">',
  '<div class="navbar-header">',
  '<a class="navbar-brand" href="#">{{$ctrl.brand}}</a>',
  '</div>',
  '<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">',
  '<ul class="nav navbar-nav">',
  '<li ng-repeat="menu in $ctrl.menu">',
  '<a href="{{menu.component}}">{{menu.name}} <span class="sr-only">(current)</span></a>',
  '</li>',
  '</ul>',
  '</div>',
  '</div>',
  '</nav>'
].join(' ')

app.component('menuBar', {
  template: navbar,
  controller: function() {
    this.menu = [{
      name: "Login",
      component: "#/"
    }, {
      name: "Home",
      component: "#/home"
    }, {
      name: "Cars",
      component: "#/cars"
    }, {
      name: "Employees",
      component: "#/employees"
    }, {
        name: "Admins",
        component: "#/admins"
    }, {
        name: "Reservations",
        component: "#/reservations"
    }
    ];
  }
});
