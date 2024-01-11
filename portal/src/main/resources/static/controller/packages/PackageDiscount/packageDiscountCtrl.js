//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function packageDiscountCtrl($scope, $state, packageDiscountService, $rootScope,$stateParams) {
	
	$scope.packageDiscount=$stateParams.packageDiscountObj;
	$scope.addPackageDiscount = function(packageDiscount) {
		packageDiscountService.addPackageDiscount(packageDiscount).then(function(response) {
			$state.go('main.packageDiscountlist');
		}, function(error) {
		});
	};
	$scope.getAllPackageDiscounts= function() {
		packageDiscountService.getAllPackageDiscounts().then(function(response) {
			$scope.packageDiscountList = response.data;
		}, function(error) {
		});
	};
	
	$scope.updatePackageDiscount = function(packageDiscount) {
		packageDiscountService.updatePackageDiscount(packageDiscount).then(function(response) {
			$state.go('main.packageDiscountlist');
		}, function(error) {
		});
	};
	$scope.deletePackageDiscount = function(id) {
		packageDiscountService.deletePackageDiscount(id).then(function(response) {
			$scope.getAllPackageDiscounts();
		}, function(error) {
		});
	};
	$scope.gotoList = function() {
		$state.go('main.packageDiscountlist');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addPackageDiscount');
	};
	$scope.gotoback = function() {
		$state.go('main.packageDiscountlist');
	};
	$scope.gotoupdate = function(packageDiscount) {
		$state.go('main.updatePackageDiscount',{
			packageDiscountObj:packageDiscount
		});
	};
}
angular.module("HealthApplication")
		.controller("packageDiscountCtrl", packageDiscountCtrl);
