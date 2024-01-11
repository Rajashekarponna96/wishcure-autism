//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function taxCtrl($scope, $state, taxService, $rootScope,$stateParams, $http,successMessageHandler) {
	
	$scope.page = 0;
	$scope.size = 5;
	
	$scope.tax=$stateParams.taxObj;
	
	$scope.addtax = function(tax) {
		tax.adminUserName=$rootScope.loggedUsername;
		taxService.addtax(tax).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.TAX_ADD_DATA_SUCCESS);
			$state.go('main.taxList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAlltaxList= function() {
		taxService.getAlltaxList($rootScope.loggedUsername,$scope.page, $scope.size).then(function(response) {
			$scope.taxLists = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages-1;
			$scope.noOfPgaes(response.data.totalPages);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAlltaxs= function() {
		taxService.getAlltaxs().then(function(response) {
			$scope.taxsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.updatetax = function(tax) {
		taxService.updatetax(tax).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.TAX_UPDATE_DATA_SUCCESS);
			$state.go('main.taxList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deletetax = function(id) {
		taxService.deletetax(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
			$scope.getAlltaxList();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
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
					$scope.getAlltaxList();
				});
	  
	$scope.gotoList = function() {
		$state.go('main.taxList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addTax');
	};
	$scope.gotoback = function() {
		$state.go('main.taxList');
	};
	$scope.gotoupdate = function(client) {
		$state.go('main.updateTax',{
			taxObj:	client
		});
	};
}
angular.module("HealthApplication")
		.controller("taxCtrl", taxCtrl);
