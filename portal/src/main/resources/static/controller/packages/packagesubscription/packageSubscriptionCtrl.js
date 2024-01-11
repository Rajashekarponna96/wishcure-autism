

function packageSubscriptionCtrl($scope, $state, packageSubscriptionService,packageMasterService,subscriptionMasterService, $rootScope,$stateParams) {
	
	$scope.packageSubscription=$stateParams.packageSubscriptionObj;
	$scope.packageMaster=$stateParams.packageMasterObj;
	$scope.subscription=$stateParams.subscriptionObj;
	
	$scope.addPackageSubscription = function(packageSubscription) {
		
		packageSubscriptionService.addPackageSubscription(packageSubscription).then(function(response) {
			$state.go('main.packageSubscriptionList');
		}, function(error) {
		});
	};
	
	$scope.getAllPackageMAsters= function() {
		packageMasterService.getAllPackageMAsters().then(function(response) {
			$scope.packageMastersList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllSubscriptionMasters = function() {
		subscriptionMasterService.getAllSubscriptionMasters().then(function(response) {
			$scope.subscriptionMasterList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.getAllPackageSubscriptions = function() {
		packageSubscriptionService.getAllPackageSubscriptions().then(function(response) {
			$scope.packageSubscriptionList = response.data;
		}, function(error) {
		});
	};
	
	$scope.updatePackageSubscription = function(packageSubscription) {
		packageSubscriptionService.updatePackageSubscription(packageSubscription).then(function(response) {
			$state.go('main.packageSubscriptionList');
		}, function(error) {
		});
	};
	
	
	$scope.getAllSubscriptionMasters = function() {
		subscriptionMasterService.getAllSubscriptionMasters().then(function(response) {
			$scope.subscriptionMasterList = response.data;
		}, function(error) {
		});
	};
	
	$scope.getAllPackageMAsters= function() {
		packageMasterService.getAllPackageMAsters().then(function(response) {
			$scope.packageMastersList = response.data;
		}, function(error) {
			
		});
	};
	
	
	
	$scope.deletePackageSubscription = function(id) {
		packageSubscriptionService.deletePackageSubscription(id).then(function(response) {
			$scope.getAllPackageSubscriptions();
		}, function(error) {
		});
	};
	$scope.gotoList = function() {
		$state.go('main.packageSubscriptionList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addPackageSubscription');
	};
	$scope.gotoback = function() {
		$state.go('main.packageSubscriptionList');
	};
	$scope.gotoupdate = function(packageSubscription) {
		$state.go('main.updatePackageSubscription',{
			packageSubscriptionObj:	packageSubscription
		});
	};
}
angular.module("HealthApplication")
		.controller("packageSubscriptionCtrl", packageSubscriptionCtrl);
