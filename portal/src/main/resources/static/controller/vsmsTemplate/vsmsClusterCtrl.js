function vsmsClusterCtrl($scope, $state, $rootScope, $http, $stateParams,
		vsmsClusterService,successMessageHandler) {

	$scope.vsmsCluster = {};
	$scope.vsmsClustersList = [];
	$scope.mcluster = {};

	/*if ($stateParams.motocluster != undefined) {
		$scope.mcluster = $stateParams.motocluster;
	}*/

	// for save
	$scope.addVSMSCluster = function() {
		vsmsClusterService.addVSMSCluster($scope.vsmsCluster).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.VSMS_CLUSTER_ADDED );
					$state.go('main.vsmsClusterList');
					
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
	}

	// for getAll vsms clusters with out pagination
	$scope.getVsmsClusters = function() {
		vsmsClusterService.getVSMSClusters().then(function(response) {
			$scope.vsmsClustersList = response.data;
		}, function(error) {

		})
	}

	$scope.gotoupdate = function(vsmsCluster) {
		$state.go('main.updateVsmsCluster', {
			vsmsCluster : vsmsCluster
		});
	};

	// for update vsmsCluster
	$scope.updateVSMSCluster = function(vsmsCluster) {
		vsmsClusterService.updateVSMSCluster(vsmsCluster).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.VSMS_CLUSTER_UPDATED );
					$state.go('main.vsmsClusterList');

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
	}

	// for detele motorCluster
	$scope.deleteVSMSCluster = function(vsmsClusterId) {
		vsmsClusterService.deleteVSMSCluster(vsmsClusterId).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.VSMS_CLUSTER_DELETED);
					$scope.getVsmsClusters();
				}, function(error) {

				})
	}

	$scope.gotoList = function() {
		$state.go('main.vsmsClusterList');
	};

	$scope.gotoback = function() {
		$state.go('main.vsmsClusterList');
	};

	$scope.gotoAdd = function() {
		$state.go('main.addVsmsCluster');
	};

}
angular.module('HealthApplication').controller("vsmsClusterCtrl",
		vsmsClusterCtrl);