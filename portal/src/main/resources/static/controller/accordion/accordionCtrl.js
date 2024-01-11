/**
 * 
 */
function accordionCtrl($scope, $state, loginService, therapistService,$stateParams,$http,
		userManagementSerrvice, patientService, signupService, categoryService,
		$rootScope, companyService, $rootScope, $localStorage,PATIENT_MODULE_CONFIG, successMessageHandler) {
$scope.oneAtATime = true;
	  $scope.status = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status1 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status2 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status3 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status4 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status5 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status6 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status7 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };

	  $scope.status8 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status9 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };

	  $scope.status10 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status11 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };

	  $scope.status12 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status13= {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
	  $scope.status14 = {
	    isFirstOpen: true,
	    isFirstDisabled: false
	  };
  }

  angular.module("HealthApplication").controller("accordionCtrl", accordionCtrl);
