//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function therapistHolidaysCtrl($scope, $state, therapistHolidaysService,therapistService, $rootScope,$stateParams, successMessageHandler) {
	
	$scope.holidays={};
	$scope.color="red";
	$scope. holidays=$stateParams. holidaysObj;
	
	$scope.addHolidays = function( holidays) {
		$scope.holidays.doctorUsername= $rootScope.loggedUsername;
		therapistHolidaysService.addHolidays($scope.holidays).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.THERAPISTHOLIDAY_ADD_DATA_SUCCESS);
                $scope.holidays=undefined;
                $scope.getAllHolidaysByDoctorUsername();
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.error($scope.message, 'Error');
                
			
		});
	};
	//$scope.holidays.color="red";
	$scope.addHolidaysByAdmin = function() {
		$scope.holidays.doctorUsername= $scope.therapist.email;
		therapistHolidaysService.addHolidays($scope.holidays).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.THERAPISTHOLIDAY_ADD_DATA_SUCCESS);
               /* $scope.scheduleObj=undefined;
                $scope.getAllHolidaysByDoctorUsername();*/
                $state.go('main.holidaysListAdmin');
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',

                    positionClass : "toast-top-right",

                    timeOut: 2000
                };
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.error($scope.message, 'Error');
		});
	};
	
	/*$scope.getAllHolidaysByDoctorUsername = function() {
		//$scope.companyAdminUsername= $rootScope.loggedUsername;
		therapistHolidaysService.getAllHolidaysByDoctorUsername($rootScope.loggedUsername).then(function(response) {
			$scope.holidaysList = response.data;
			
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllHolidays = function() {
		therapistHolidaysService.getAllHolidays().then(function(response) {
			$scope.holidaysList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};*/
	
	$scope.getAllHolidaysByRole = function() {
		therapistHolidaysService.getAllHolidaysByRole($rootScope.loggedUsername).then(function(response) {
			$scope.holidaysListAdmin = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.getAllTherapists= function() {
		$scope.adminUsername=$rootScope.loggedUsername;
		therapistService.getAllTherapists($scope.adminUsername).then(function(response) {
			$scope.therapistList = response.data;
		
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.deleteHolidays = function(id) {
		therapistHolidaysService.deleteHolidays(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.THERAPISTHOLIDAY_DELETE_DATA_SUCCESS);
			$scope.getAllHolidaysByRole();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.holidaysListAdmin');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addTherapistHolidays');
	};
	$scope.gotoback = function() {
		$state.go('main.holidaysListAdmin');
	};
	
}
angular.module("HealthApplication")
		.controller("therapistHolidaysCtrl", therapistHolidaysCtrl);

