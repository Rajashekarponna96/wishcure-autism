function packagePricingCtrl($scope, $state, subscriptionMasterService,
		packageMasterService, packagePricingService, $rootScope, $stateParams, successMessageHandler) {

	$scope.page = 0;
	$scope.size = 5;
	
	$scope.packagePricing = $stateParams.packagePricingObj;

	$scope.addPackagePricing = function(packagePricing) {
		packagePricingService.addPackagePricing(packagePricing).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-full-width",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.PACKAGEPRICING_SAVE_SUCCESS);
					$state.go('main.packagePricingList');
				}, function(error) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
					var message2 = JSON.stringify(error.data.message.trim());
					$scope.message=message2.substring(1,message2.length-1);
					toastr.error($scope.message, 'Error');					
				});
	};

	$scope.getAllSubscriptionMastersList = function() {
		subscriptionMasterService.getAllSubscriptionMastersList().then(function(response) {
			$scope.subscriptionMasterLists = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllPackageMAstersList= function() {
		packageMasterService.getAllPackageMAstersList().then(function(response) {
			$scope.packageMastersLists = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllPackagePricings = function() {
		packagePricingService.getAllPackagePricings($scope.page, $scope.size).then(function(response) {
			$scope.packagePricingList = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages-1;
		}, function(error) {

		});
	};

	$scope.getPackagePriceMaster = function() {
		packagePricingService.getPackagePriceMaster($scope.packagePricing.id).then(function(response) {
			$scope.packagePriceMasterObj = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.updatePackagePricingmethod = function(PricingObj) {
		packagePricingService.updatePackagePricing(PricingObj).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-full-width",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.PACKAGEPRICING_UPDATE_SUCCESS);
					$state.go('main.packagePricingList');
				}, function(error) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
					var message2 = JSON.stringify(error.data.message.trim());
					$scope.message=message2.substring(1,message2.length-1);
					toastr.error($scope.message, 'Error');
				});
	};

	$scope.deletePackagePricing = function(id) {
		packagePricingService.deletePackagePricing(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.PACKAGEPRICING_DELETE_SUCCESS);
			$scope.getAllPackagePricings();
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			var message2 = JSON.stringify(error.data.message.trim());
			$scope.message=message2.substring(1,message2.length-1);
			toastr.error($scope.message, 'Error');
		});
	};
	
	
	// Pagination logic

	$scope.noOfPgaes=function(totalpages){
		
		$scope.pageList=[];
		for(i=0;i<totalpages;i++){
			$scope.pageList.push(i);
		}
		
	   };
	
	   $scope.pageChanged=function(page){
		$scope.page=page;
	   };
	   
	   $scope.sizeChanged=function(size){
			$scope.size=size;
		};
		$scope.firstPage = function() {
			$scope.page = 0;
		};
		
		$scope.lastPage = function() {
			$scope.page = $scope.totalPages;
		};

		$scope.previousPage = function() {
			if ($scope.page >0) {
				$scope.page = $scope.page - 1;
			}
		};
		$scope.nextPage = function() {
			if ($scope.page < $scope.totalPages) {
				$scope.page = $scope.page + 1;
			}
		};
		$scope.$watchGroup(['size','page'],
				function(oldSize, oldPage) {
					$scope.getAllPackagePricings();
				});
	  

	$scope.gotoList = function() {
		$state.go('main.packagePricingList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addPackagePricing');
	};
	$scope.gotoback = function() {
		$state.go('main.packagePricingList');
	};
	$scope.gotoupdate = function(packagePricing) {
		$state.go('main.updatePackagePricing', {
			packagePricingObj : packagePricing
		});
	};
	$scope.getPackagePriceMatserInfo = function(subscriptionName, packagename) {
		packagePricingService.getPackagePriceMatserInfo(subscriptionName,
				packagename).then(function(response) {
			$scope.packagePricing1 = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());

		});

	};
}
angular.module("HealthApplication").controller("packagePricingCtrl",
		packagePricingCtrl);