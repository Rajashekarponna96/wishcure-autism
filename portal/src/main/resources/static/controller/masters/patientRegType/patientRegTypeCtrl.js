function patientRegTypeCtrl($scope, $state, $rootScope, patientRegTypeService, $stateParams, successMessageHandler) {
	
		$scope.patientRegType = $stateParams.patientRegTypeObj;
	
		$scope.addPatientRegtype = function(patientRegType) {
			patientRegTypeService.addPatientRegtype(patientRegType).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.PATIENT_REGISTRATION_TYPE_ADDED_SUCCESSFULLY);
			$state.go('main.patientRegTypeList');
		}, function(error) {
			
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message = $scope.message1.substring(1, $scope.message1.length - 1);
			toastr.error($scope.message, 'Error');
			
		});
	};
	
	$scope.deleteRegistrationType = function(id) {
		patientRegTypeService.deleteRegistrationType(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.PATIENT_REGISTRATION_TYPE_DELETED_SUCCESSFULLY);
			$scope.getAllPatientRegTypes();
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message = $scope.message1.substring(1, $scope.message1.length - 1);
			toastr.error($scope.message, 'Error');
		});
	};
	
	$scope.updatePatientRegType = function(pRegType) {
		patientRegTypeService.updatePatientRegType(pRegType).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.PATIENT_REGISTRATION_TYPE_UPDATED_SUCCESSFULLY);
			$state.go('main.patientRegTypeList');
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message = $scope.message1.substring(1, $scope.message1.length - 1);
			toastr.error($scope.message, 'Error');
			
		});
	};
	
		$scope.getAllPatientRegTypes = function() {
		patientRegTypeService.getAllPatientRegTypes().then(function(response) {
			$scope.patientRegTypesList = response.data;
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message = $scope.message1.substring(1, $scope.message1.length - 1);
			toastr.error($scope.message, 'Error');
		});
	};
	
		$scope.deletealert = function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this Registration Type!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				$scope.deleteRegistrationType(id);
			    swal("Registration Type Deleted Successfully!", {
			      icon: "success",
			    });
			  } else {
			    swal("Your Registration Type is safe!");
			  }
			});
		};
	
	$scope.gotoAdd = function() {
		$state.go('main.addPatientRegType');
	};
	
	$scope.gotoRegList = function() {
		$state.go('main.patientRegTypeList');
	};
	$scope.gotoupdate = function(patientRegType) {	
		$state.go('main.updatePatientRegType',{
			patientRegTypeObj: patientRegType
		});
	};
	$scope.gotoback = function() {
		$state.go('main.patientRegTypeList');
	};
	
	
	
}
angular.module("HealthApplication")
		.controller("patientRegTypeCtrl", patientRegTypeCtrl);
