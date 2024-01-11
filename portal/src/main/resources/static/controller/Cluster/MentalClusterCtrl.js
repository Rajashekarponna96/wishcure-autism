function MentalClusterCtrl($scope, $state,MentalClusterService, $rootScope,$stateParams,successMessageHandler) {
	
	$scope.cluster=$stateParams.mentalClusterObj;
	
	$scope.addMentalCluster = function(cluster) {
		MentalClusterService.addMentalCluster( cluster).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.MENTAL_CLUSTER_DATA_ADDED);
                $scope.getAllMentalCluster();
		}, function(error) {
			//$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllMentalCluster = function() {
		MentalClusterService.getAllMentalCluster().then(function(response) {
			$scope.mentalClusterList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.updateMentalCluster = function(cluster) {
		MentalClusterService.updateMentalCluster(cluster).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.MENTAL_CLUSTER_DATA_UPDATED);
			$state.go('main.MentalCluster');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deletementalCluster = function(id) {
		MentalClusterService.deletementalCluster(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.MENTAL_CLUSTER_DATA_DELETED);
			$scope.getAllMentalCluster();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.MentalCluster');
	};
	$scope.gotoAdd = function() {
		$state.go('main.MentalCluster');
	};
	$scope.gotoback = function() {
		$state.go('main.MentalCluster');
	};
	$scope.gotoupdate = function(cluster) {
		$state.go('main.UpdateMentalCluster',{
			 mentalClusterObj:cluster
		});
	};
}
angular.module("HealthApplication")
		.controller("MentalClusterCtrl", MentalClusterCtrl);
