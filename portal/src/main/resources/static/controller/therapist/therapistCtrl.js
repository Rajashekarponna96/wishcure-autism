function therapistCtrl($scope, $state, therapistService,departmentService,countryService, $rootScope,$stateParams, successMessageHandler) {
	$scope.therapist={};
	$scope.therapist=$stateParams.therapistObj;
	$scope.addTherapist = function() {
		$scope.therapist.adminUsername=$rootScope.loggedUsername;
		therapistService.addTherapist($scope.therapist).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.THERAPIST_ADD_SUCCESS);
			$state.go('main.therapist_list');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getTherapistByUsername = function() {
		therapistService.getTherapistByUsername($rootScope.loggedUsername).then(
				function(response) {
					$scope.therapist = response.data;
					$scope.getAllStatesByCountryId($scope.therapist.countryName.id);
					$scope.getAllCitiesByStateId ($scope.therapist.stateName.id);
				}, function(error) {

				})
	};
	$scope.getAllTherapists= function() {
		$scope.adminUsername=$rootScope.loggedUsername;
		therapistService.getAllTherapists($scope.adminUsername).then(function(response) {
			$scope.therapistList = response.data;
			$scope.getAllStatesByCountryId($scope.therapistList.countryName.id);
			$scope.getAllCitiesByStateId($scope.therapistList.stateName.id);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllTherpistByDepartment= function(id) {
		
		therapistService.getAllTherpistByDepartment(id).then(function(response) {
			$scope.therapistList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllActiveTherapists= function() {
		$scope.adminUsername=$rootScope.loggedUsername;
		therapistService.getAllActiveTherapists($scope.adminUsername).then(function(response) {
			$scope.therapistList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.updateTherapist = function(therapist) {
		
		var address1 = document.getElementById('street_number').value;
		var address2 = document.getElementById('route').value;
		var city = document.getElementById('locality').value;
		var state = document.getElementById('administrative_area_level_1').value;
		var country = document.getElementById('country').value;
		var zipcode = document.getElementById('postal_code').value;
		$scope.therapist.address1=address1;
		$scope.therapist.address2=address2;
		$scope.therapist.city=city;
		$scope.therapist.state=state;
		$scope.therapist.country=country;
		$scope.therapist.zipcode=zipcode;
		
		therapistService.updateTherapist(therapist).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.THERAPIST_UPDATE_SUCCESS);
			$state.go('main.therapist_list');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteTherapist = function(id) {
		therapistService.deleteTherapist(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.deleteTherapistDataSuccessMessage);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllCountries = function() {
		countryService.getAllCountries().then(function(response) {
			$scope.countriesList = response.data;
		}, function(error) {

		})
	};
	$scope.getCountrysBasedOnIsdCode = function() {
		countryService.getCountrysBasedOnIsdCode().then(function(response) {
			$scope.countriesList = response.data;
		}, function(error) {

		})
	};
	$scope.getAllStatesByCountryId = function(id) {
		countryService.getAllStatesByCountryId(id).then(function(response) {
			$scope.statesList = response.data;
		}, function(error) {

		})
	};
	
	$scope.getAllCitiesByStateId = function(id) {
		countryService.getAllCitiesByStateId(id).then(function(response) {
			$scope.citiesList = response.data;
		}, function(error) {

		})
	};
	$scope.getAllDepartments= function() {
		departmentService.getAlldepartments().then(function(response) {
			$scope.departmentsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllDepartmentsByCmpany = function() {
		departmentService.getAllDepartmentsByCmpany($rootScope.loggedUsername).then(function(response) {
			$scope.departmentsListByCmpany = response.data;
			if($scope.departmentsListByCmpany.length<1){
				toastr.options = {
	                    closeButton: true,
	                    progressBar: true,
	                    showMethod: 'slideDown',
	                    positionClass : "toast-top-full-width",
	                    timeOut: 2000
	                };
				$scope.message = "Departments are  not configured! Please configure the Departments First!!"
				toastr.error($scope.message, '');
			}
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.therapist_list');
	};
	$scope.gotoAdd = function() {
		$state.go('main.add_therapist');
	};
	$scope.gotoback = function() {
		$state.go('main.therapist_list');
	};
	$scope.gotoupdate = function(therapist) {
		$state.go('main.updateTherapist',{
			therapistObj:therapist
		});
	};
}
angular.module("HealthApplication")
		.controller("therapistCtrl", therapistCtrl);
