function motorClusterCtrl($scope, $state, $rootScope, $http,$stateParams,
		motorClusterService,successMessageHandler) {

	$scope.motorCluster = {};
	$scope.motorClustersList = [];
	$scope.mcluster = {};

	if ($stateParams.motocluster != undefined) {
		$scope.mcluster = $stateParams.motocluster;
	}

	// for save
	$scope.addMotorCluster = function() {
		motorClusterService.addMotorCluster($scope.motorCluster).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.MOTOR_CLUSTER_ADDED );
					$state.go('main.motorClusterList');
					
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

	$scope.gotoupdate = function(motorCluster) {
		$state.go('main.updateMotorCluster', {
			motocluster : motorCluster
		});
	};

	// for update motorCluster
	$scope.updateMotorCluster = function(motorCluster) {
		motorClusterService.updateMotorCluster(motorCluster).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.MOTOR_CLUSTER_UPDATED );
					$state.go('main.motorClusterList');

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

	// for detele motorCluster
	$scope.deleteMotorCluster = function(motorClusterId) {
		motorClusterService.deleteMotorCluster(motorClusterId).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.MOTOR_CLUSTER_DELETED);
					$scope.getMotorClusters();
				}, function(error) {

				})
	};

	$scope.gotoList = function() {
		$state.go('main.motorClusterList');
	};

	$scope.gotoback = function() {
		$state.go('main.motorClusterList');
	};

	$scope.gotoAdd = function() {
		$state.go('main.addMotorCluster');
	};

}
angular.module('HealthApplication').controller("motorClusterCtrl",
		motorClusterCtrl);