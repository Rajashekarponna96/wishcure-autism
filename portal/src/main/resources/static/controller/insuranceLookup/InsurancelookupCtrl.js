function insuranceLookupCtrl($scope, $state, insuranceLookupService,
		$rootScope, $stateParams, successMessageHandler) {
	$scope.allInsuracelookups = [];
	$scope.insurancelookup = {};
	$scope.address1 = {};
	if ($stateParams.insLookup != undefined){
		$scope.insurancelookup1=$stateParams.insLookup;
	}

		$scope.gotoAddInsuranceLookup = function() {
			$state.go('main.addInsuranceLookup');
		};
	$scope.gotoInsuranceLookupList = function() {
		$state.go('main.insuranceLookupList');
	};
	$scope.gotoupdate = function(insuranceLookup) {
		$state.go('main.updateInsuranceLookup', {
			insLookup : insuranceLookup
		})
	}

	$scope.addInsuranceLookup = function() {
		var address1 = document.getElementById('street_number').value;
		var address2 = document.getElementById('route').value;
		var city = document.getElementById('locality').value;
		var state = document.getElementById('administrative_area_level_1').value;
		var country = document.getElementById('country').value;
		var zipcode = document.getElementById('postal_code').value;
		$scope.address1.address1 = address1;
		$scope.address1.address2 = address2;
		$scope.address1.city = city;
		$scope.address1.state = state;
		$scope.address1.country = country;
		$scope.address1.zipcode = zipcode;
		$scope.insurancelookup.address1 = $scope.address1;
		insuranceLookupService.addInsuranceLookup($scope.insurancelookup).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.INSURANCELOOKUP_ADD_SUCCESS);
					$state.go('main.insuranceLookupList');
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
				})
	};

	$scope.changeMobileNumber = function(mobileNumber) {
		if (mobileNumber == 0) {
			var number = "";
			return number;
		}
		var currentNumber = mobileNumber.toString();
		changedNumber = "(" + currentNumber.substring(0, 3) + ")"
				+ currentNumber.substring(3, 6) + "-"
				+ currentNumber.substring(6, 10);
		return changedNumber;
	}
	
	
	$scope.getAllInsuranceLookups = function() {
		insuranceLookupService.getAllInsuranceLookups().then(
				function(response) {
					$scope.allInsuracelookups = response.data;
				}, function(error) {

				})
	};

	$scope.updateInsuranceLookup = function(insurance) {
		insuranceLookupService.updateInsuranceLookup(insurance).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.INSURANCELOOKUP_UPDATE_SUCCESS);
		                $state.go('main.insuranceLookupList');
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
				})
	};

	$scope.delteInsuranceLookup = function(insuranceLookupId) {
		insuranceLookupService.delteInsuranceLookup(insuranceLookupId).then(
				function(response) {
					$scope.getAllInsuranceLookups();
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.INSURANCELOOKUP_DELETE_SUCCESS);
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
				})
	};

}
angular.module("HealthApplication").controller("insuranceLookupCtrl",
		insuranceLookupCtrl);
