function motorScaleCtrl($scope,$state,$rootScope,$stateParams,motorScaleService,motorClusterService,successMessageHandler){


	$scope.motorScale = {};
	$scope.motorScaleList = [];
	$scope.mscale = {};
	$scope.motorClustersList=[];
	$scope.motorScalObj={
			"motorScaleChecklist":[]
	}
	
	if ($stateParams.motoScale != undefined) {
		$scope.mscale = $stateParams.motoScale;
	}
	
	// for save
	$scope.addMotorScale = function() {
		motorScaleService.addMotorScale($scope.motorScale).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.MOTOR_SCALE_ADDED);
					$state.go('main.motorScaleList');
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

	// for getAll motor scales with out pagination
	$scope.getMotorScales = function() {
		motorScaleService.getMotorScales().then(function(response) {
			$scope.motorScaleList = response.data;
		}, function(error) {

		})
	}

	$scope.gotoupdate = function(motorScale) {
		$state.go('main.updateMotorScale', {
			motoScale : motorScale
		});
	};

	// for update motorCluster
	$scope.updateMotorScale = function(motorScale) {
		motorScaleService.updateMotorScale(motorScale).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.MOTOR_SCALE_UPDATED);
					$state.go('main.motorScaleList');
				}, function(error) {

				})
	};

	// for detele motorCluster
	$scope.deleteMotorScale = function(motorScaleId) {
		motorScaleService.deleteMotorScale(motorScaleId).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.MOTOR_SCALE_DELETED);
					$scope.getMotorScales();
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
	
	// for getAll motor clusters with out pagination
	$scope.getMotorClusters = function() {
		motorClusterService.getMotorClusters().then(function(response) {
			$scope.motorClustersList = response.data;
		}, function(error) {

		})
	}
	
	//assign motorScales to MotorCluster
	$scope.assignMotorScaleToCluster = function(motorClusterId) {
		motorScaleService.assignMotorScaleToCluster($scope.motorScalObj.motorScaleChecklist,motorClusterId).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.MOTOR_SCALE_ARE_ASSIGNED_TO_CLUSTER);
					
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
	
	

	$scope.gotoList = function() {
		$state.go('main.motorScaleList');
	};

	$scope.gotoback = function() {
		$state.go('main.motorScaleList');
	};

	$scope.gotoAdd = function() {
		$state.go('main.addMotorScale');
	};
	
	

}
angular.module('HealthApplication').controller("motorScaleCtrl", motorScaleCtrl);