app.controller('employeesController', function($scope, $http, $rootScope, $timeout) {
    $scope.headingTitle = "Employee list";
    loadAllEmployees($http, $rootScope);
    $scope.deleteEmployee = function (employee) {
        $http.delete('/pa165/rest/employee/remove/' + employee.id).then(function () {
            loadAllEmployees($http, $rootScope);},
            function error(response) {
                console.log("failed to remove employee " + employee.id);
                console.log(response);
                $rootScope.errorAlert = 'Cannot remove employee with a reservation!';
                hideAlert($timeout, $rootScope, 'error');
            });
    };
});

app.controller('adminsController', function($scope, $http, $rootScope, $timeout) {
    $scope.headingTitle = "Admin list";
    loadAllAdmins($http, $rootScope);
    $scope.deleteAdmin = function (admin) {
        $http.delete('/pa165/rest/admin/remove/' + admin.id).then(function () {
            loadAllAdmins($http, $rootScope);},
            function error(response) {
                console.log("failed to remove admin " + admin.id);
                console.log(response);
                $rootScope.errorAlert = 'Cannot remove admin!';
                hideAlert($timeout, $rootScope, 'error');
            });
    };
});

app.controller('carsController', function($scope, $http, $rootScope, $timeout) {
    $scope.headingTitle = "Car list";
    loadAllCars($http, $rootScope);
    $scope.deleteCar = function (car) {
        $http.delete('/pa165/rest/car/remove/' + car.id).then(function () {
            loadAllCars($http, $rootScope);},
            function error(response) {
                console.log("failed to remove car " + car.id);
                console.log(response);
                $rootScope.errorAlert = 'Cannot remove reserved car!';
                hideAlert($timeout, $rootScope, 'error');
            });
    };
});

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
    $scope.headingTitle = "Reservation list";
    $scope.disabled = true;
    $scope.updateButtonText = "Update";

    $scope.deleteReservation = function (id) {
        $http.delete('/pa165/rest/reservation/remove/' + id).success(function(data) {
            loadAllReservations($http, $rootScope);
            $rootScope.successAlert = 'Reservation "' + id + '" was deleted.';
        })
    };
    loadAllReservations($http, $rootScope);
});

function createObjectNoChecks($http, responseObject, $rootScope) {
    $http({
        method: 'POST',
        url: '/pa165/rest/' + $rootScope.objectName + '/create',
        data: responseObject
    }).then(function success(response) {
        console.log($rootScope.objectName + ' created');
        console.log('/pa165/rest/'+ $rootScope.objectName + '/create');
        var createdObject = response.data;
        loadAll($http, $rootScope, $rootScope.objectName);
        $rootScope.successAlert = $rootScope.objectName + ' "' + createdObject.id + '" was created.';
    }, function error(response) {
        $rootScope.errorAlert = 'Cannot create ' + $rootScope.objectName + '.';
    });
}

function createObject($http, responseObject, $rootScope) {
    if ($rootScope.objectName === "reservation") {
        if($rootScope.isAdmin){
            $http.get('/pa165/rest/employee/getByEmail?email=' + responseObject.employee.email).success(function(data) {
                responseObject.employee = data;
                $http.get('/pa165/rest/car/getBySpz?spz=' + responseObject.car.evidence_number).success(function(data) {
                    responseObject.car = data;
                    createObjectNoChecks($http, responseObject, $rootScope);
                });
            });
        } else {
            $http.get('/pa165/rest/employee/getByEmail?email=' + $rootScope.currentUser).success(function(data) {
                responseObject.employee = data;
                $http.get('/pa165/rest/car/getBySpz?spz=' + responseObject.car.evidence_number).success(function(data) {
                    responseObject.car = data;
                    createObjectNoChecks($http, responseObject, $rootScope);
                });
            });
        }
    } else {
        createObjectNoChecks($http, responseObject, $rootScope);
    }
}

function updateObjectNoChecks($http, responseObject, $rootScope) {
    $http({
        method: 'PUT',
        url: '/pa165/rest/'+ $rootScope.objectName + '/update',
        data: responseObject
    }).then(function success(response) {
        console.log($rootScope.objectName + ' updated');
        console.log('/pa165/rest/'+ $rootScope.objectName + '/update');
        var updatedData = response.data;
        loadAll($http, $rootScope, $rootScope.objectName);
        $rootScope.successAlert = $rootScope.objectName + ' "' + updatedData.id + '" was updated.';
    }, function error(response) {
        $rootScope.errorAlert = 'Cannot update ' + $rootScope.objectName + '.';
    });
}

function updateObject($http, responseObject, $rootScope) {
    if ($rootScope.objectName === "reservation") {
        if($rootScope.isAdmin){
            $http.get('/pa165/rest/employee/getByEmail?email=' + responseObject.employee.email).success(function(data) {
                responseObject.employee = data;
                $http.get('/pa165/rest/car/getBySpz?spz=' + responseObject.car.evidence_number).success(function(data) {
                    responseObject.car = data;
                    updateObjectNoChecks($http, responseObject, $rootScope);
                });
            });
        } else {
            $http.get('/pa165/rest/car/getBySpz?spz=' + responseObject.car.evidence_number).success(function(data) {
                responseObject.car = data;
                updateObjectNoChecks($http, responseObject, $rootScope);
            });
        }
    } else {
        updateObjectNoChecks($http, responseObject, $rootScope);
    }
}

function clearForm($rootScope) {
    $rootScope.car = null;
    $rootScope.employee = null;
    $rootScope.admin = null;
    $rootScope.reservation = null;
}

function loadAll($http, $rootScope, objectName) {
    switch (objectName) {
        case 'car':
            return loadAllCars($http, $rootScope);
        case 'admin':
            return loadAllAdmins($http, $rootScope);
        case 'employee':
            return loadAllEmployees($http, $rootScope);
        case 'reservation':
            return loadAllReservations($http, $rootScope);
    }
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

    this.reservation = _reservation;
    this.admin = _admin;
    this.employee = _employee;
    this.car = _car;
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
        }
    }
});

app.controller('navbarController', ['$rootScope', '$scope', '$location', '$timeout',
    function ($rootScope, $scope, $location, $timeout) {
    var $ctrl = this;

    $ctrl.logout = function () {
        console.log("Logging out " + $rootScope.currentUser);
        $rootScope.successAlert = 'User logged out.';
        $rootScope.loggedIn = false;
        $rootScope.isAdmin = false;
        $rootScope.currentUser = undefined;
        hideAlert($timeout, $rootScope, 'success');
    };
}]);

app.controller('loginController', ['$scope', '$rootScope', '$http', '$location', 'flashService', '$timeout',
    function($scope, $rootScope, $http, $location, flashService, $timeout) {
    $scope.formModel = {};
    $scope.loginResponse = {};

    $scope.onSubmit = function() {
        $scope.formModel.dataLoading = true;
        $http.post('/pa165/rest/login', $scope.formModel).
            success(function (data) {
                $scope.loginResponse = data;
                console.log("login request successful");
                console.log($scope.loginResponse);
                if ($scope.loginResponse.authenticated == true) {
                    $scope.formModel.dataLoading = false;
                    $rootScope.successAlert = 'Login successful.';
                    $rootScope.currentUser = $scope.formModel.email;
                    $rootScope.loggedIn = true;
                    if ($scope.loginResponse.userRole == 'ROLE_ADMIN') {
                        $rootScope.isAdmin = true;
                        $location.path("/pa165/admin/cars");
                    } else {
                        $rootScope.isAdmin = false;
                        $location.path("/pa165/employee/cars");
                    }
                    hideAlert($timeout, $rootScope, 'success');
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

app.controller('ModalController', function ($scope, $uibModal, $log, sharedProperties, $http, $rootScope) {
    $scope.init = function (type, objectName) {
       $scope.responseMethodName = type;
       $rootScope.objectName = objectName;
    };

    $scope.init = function (type, objectName, originalObject) {
       switch (objectName) {
           case 'car':
               $rootScope.car = originalObject;
               break;
           case 'employee':
               $rootScope.employee = originalObject;
               break;
           case 'admin':
               $rootScope.admin = originalObject;
               break;
           case 'reservation':
               $rootScope.reservation = originalObject;
               break;
       }
        $scope.responseMethodName = type;
        $rootScope.objectName = objectName;
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
                    return $rootScope.getResponseObject($rootScope.objectName);
                }
            }
        });

        modalInstance.result.then(function (responseObject) {
            var resultFunction = window[$scope.responseMethodName];
            if (typeof resultFunction === "function") resultFunction($http, responseObject, $rootScope);
            $log.info('Modal dismissed at: ' + new Date());
        }, function () {
            clearForm($rootScope);
            loadAll($http, $rootScope, $rootScope.objectName);
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
                    return $rootScope.getResponseObject($rootScope.objectName);
                }
            }
        });

        modalInstance.result.then(function (responseObject) {
            var resultFunction = window[$scope.responseMethodName];
            if (typeof resultFunction === "function") resultFunction($http, responseObject, $rootScope);
            $log.info('Modal dismissed at: ' + new Date());
        }, function () {
            clearForm($rootScope);
            loadAll($http, $rootScope, $rootScope.objectName);
        });
    };
});

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

app.controller('ModalInstanceCtrl', function ($uibModalInstance, responseObject, $timeout, $rootScope) {
    var $ctrl = this;
    $ctrl.responseObject = responseObject;

    $ctrl.ok = function (responseObject) {
        $uibModalInstance.close(responseObject);
        hideAlert($timeout, $rootScope, 'success');
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

function hideAlert($timeout, $rootScope, alertType) {
    switch (alertType) {
        case 'success':
            $timeout(function() {
                $rootScope.successAlert = undefined;
            }, 3500);
            break;
        case 'error':
            $timeout(function() {
                $rootScope.errorAlert = undefined;
            }, 3500);
            break;
        }
}