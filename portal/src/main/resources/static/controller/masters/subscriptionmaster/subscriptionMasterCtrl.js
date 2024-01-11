//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function subscriptionMasterCtrl($scope, $state, subscriptionMasterService, $rootScope,$stateParams, successMessageHandler) {
	
	$scope.page = 0;
	$scope.size = 5;
	
	$scope.subscription=$stateParams.subscriptionObj;
	$scope.addSubscriptionMaster = function(subscription) {
		//alert("in company Type controller");
		subscriptionMasterService.addSubscriptionMaster(subscription).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.SUBSCRIPTION_SAVE_NAME_SUCCESS);
			$state.go('main.subscriptionMasterList');
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
			var message2 = JSON.stringify(error.data.message.trim());
			$scope.message=message2.substring(1,message2.length-1);
			toastr.error($scope.message, 'Error');
		});
	};
	$scope.getAllSubscriptionMasters = function() {
		subscriptionMasterService.getAllSubscriptionMasters($scope.page, $scope.size).then(function(response) {
			$scope.subscriptionMasterList = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages-1;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllSubscriptionMastersList = function() {
		subscriptionMasterService.getAllSubscriptionMastersList().then(function(response) {
			$scope.subscriptionMasterLists = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.updateSubscriptionMaster = function(subscription) {
		subscriptionMasterService.updateSubscriptionMaster(subscription).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.SUBSCRIPTION_UPDATE_NAME_SUCCESS);
			$state.go('main.subscriptionMasterList');
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
			var message2 = JSON.stringify(error.data.message.trim());
			$scope.message=message2.substring(1,message2.length-1);
			toastr.error($scope.message, 'Error');
		});
	};
	$scope.deleteSubscriptionMaster = function(id) {
		subscriptionMasterService.deleteSubscriptionMaster(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.SUBSCRIPTION_DELETE_NAME_SUCCESS);
			$scope.getAllSubscriptionMasters();
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
			var message2 = JSON.stringify(error.data.message.trim());
			$scope.message=message2.substring(1,message2.length-1);
			if($scope.message != undefined){
				toastr.error("SubScription Name is Already in Use, Can't Delete!", 'Error');	
			}			
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
					$scope.getAllSubscriptionMasters();
				});

	$scope.gotoList = function() {
		$state.go('main.subscriptionMasterList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addSubscriptionMaster');
	};
	$scope.gotoback = function() {
		$state.go('main.subscriptionMasterList');
	};
	$scope.gotoupdate = function(subscription) {
		$state.go('main.updateSubscriptionMaster',{
			subscriptionObj:	subscription
		});
	};
}
angular.module("HealthApplication")
		.controller("subscriptionMasterCtrl", subscriptionMasterCtrl);
