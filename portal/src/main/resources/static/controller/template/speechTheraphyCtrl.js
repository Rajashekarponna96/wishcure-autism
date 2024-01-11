//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function speechTheraphyCtrl($scope, $state, speechTheraphyService, $rootScope,
		$stateParams) {
	$scope.flag=false;
	if ($stateParams.patient != undefined) {
		$scope.patientObj = $stateParams.patient;
		$scope.flag=true;
	}
	if($stateParams.evalutionflag != undefined){
		$scope.evalutionStatus	=$stateParams.evalutionflag;
	}
	
	if($stateParams.exitflag != undefined){
		$scope.exitflag	=$stateParams.exitflag;
	}
	
	$scope.speechTemplates = [];
	$scope.patient = {
		speechTemplates : []
	};
	$scope.speechTheraphyTemplateOne = {};
	$scope.speechTheraphyTemplate1 = {};
	$scope.speechTheraphyTemplate2 = {};
	$scope.speechTheraphyTemplate3 = {};
	$scope.speechTheraphyTemplate4 = {};
	$scope.speechTheraphyTemplate5 = {};
	$scope.getSpeechTemplateBasedOnStatus = function(status) {
		speechTheraphyService.getSpeechTemplateBasedOnStatus(status).then(
				function(response) {
					if (status == "1") {
						$scope.speechTheraphyTemplate1 = response.data;
					}
					if (status == "2") {
						$scope.speechTheraphyTemplate2 = response.data;
					}
					if (status == "3") {
						$scope.speechTheraphyTemplate3 = response.data;
					}
					if (status == "4") {
						$scope.speechTheraphyTemplate4 = response.data;
					}
					if (status == "5") {
						$scope.speechTheraphyTemplate5 = response.data;
					}
				})
	};

	$scope.assignSpeechTemplatetoPatient = function() {
		if ($scope.patient.speechTemplates != undefined) {
			if($scope.evalutionStatus != undefined){
				$scope.evalutionSheetStatus="1";
			}
			else if($scope.exitflag==true){
				$scope.evalutionSheetStatus="3";
			}
			else{
				$scope.evalutionSheetStatus="2";
			}
			speechTheraphyService
					.assignSpeechTemplatetoPatient($scope.patientObj.id,$scope.evalutionSheetStatus,
							$scope.patient.speechTemplates)
					.then(
							function(response) {
								if($scope.evalutionSheetStatus=="1"){
									$state.go('main.patientviewtabs.patient_evaluation_sheet',{
										patientObj12 : $scope.patientObj
									});
								};
								if($scope.evalutionSheetStatus=="2"){
									$state.go('main.patientviewtabs.patient_progress_notes',{
										patientObj12 : $scope.patientObj
									});
								};
								if($scope.evalutionSheetStatus=="3"){
									$state.go('main.patientviewtabs.patient_exit_note',{
										patientObj12 : $scope.patientObj
									});
								};
							})
		}else{
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-right",
					timeOut : 2000
				};
				toastr.error("Please Select Atleast One Template", 'Error');
			
		}
	};
}
angular.module("HealthApplication").controller("speechTheraphyCtrl",
		speechTheraphyCtrl);
