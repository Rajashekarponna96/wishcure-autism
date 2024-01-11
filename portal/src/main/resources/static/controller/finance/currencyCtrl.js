//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function currencyCtrl($scope, $state, currencyService, $rootScope,$stateParams, $http, successMessageHandler) {
	
	$scope.page = 0;
	$scope.size = 5;
	
	$scope.currency=$stateParams.currencyObj;
	
	$scope.addCurrency = function(currency) {
		currency.adminUserName=$rootScope.loggedUsername;
		currencyService.addCurrency(currency).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.CURRENCY_ADD_DATA_SUCCESS);
			$state.go('main.currencyList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllCurrencyList= function() {
		currencyService.getAllCurrencyList($rootScope.loggedUsername,$scope.page, $scope.size).then(function(response) {
			$scope.currencyLists = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages-1;
			$scope.noOfPgaes(response.data.totalPages);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllCurrencys= function() {
		currencyService.getAllCurrencys($rootScope.loggedUsername).then(function(response) {
			$scope.currencysList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.updateCurrency = function(currency) {
		currencyService.updateCurrency(currency).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.CURRENCY_UPDATE_DATA_SUCCESS);
			$state.go('main.currencyList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	/*$scope.deleteCurrency = function(id) {
		currencyService.deleteCurrency(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
			$scope.getAllCurrencyList();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};*/
	$scope.deleteCurrency=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  currencyService.deleteCurrency(id).then(function(response) {
						 swal("Poof! It has been deleted!", {
						      icon: "success",
						    });
						 $scope.getAllCurrencyList();
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
						 swal($scope.message , {
						      icon: "info",
						    });
					});
			    swal("Proof!It has been deleted!", {
			      icon: "success",
			    });
			   
			  } else {
			    swal("It is safe!");
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
					$scope.getAllCurrencyList();
				});
	  
	$scope.gotoList = function() {
		$state.go('main.currencyList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addCurrency');
	};
	$scope.gotoback = function() {
		$state.go('main.currencyList');
	};
	$scope.gotoupdate = function(client) {
		$state.go('main.updateCurrency',{
			currencyObj:	client
		});
	};
}
angular.module("HealthApplication")
		.controller("currencyCtrl", currencyCtrl);
