//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function paymethodCtrl($scope, $state, paymethodService, $rootScope,$stateParams, $http,successMessageHandler) {
	
	$scope.page = 0;
	$scope.size = 5;
	
	$scope.pay=$stateParams.paymethodObj;
	
	$scope.addPaymethod = function(paymethod) {
		paymethod.adminUserName=$rootScope.loggedUsername;
		paymethodService.addPaymethod(paymethod).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.PAYMETHOD_ADD_DATA_SUCCESS);
			$state.go('main.paymethodList');
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
                
			
		});
	};
	$scope.getAllPaymethodsList= function() {
		paymethodService.getAllPaymethodsList($rootScope.loggedUsername,$scope.page, $scope.size).then(function(response) {
			$scope.paymethodLists = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages-1;
			$scope.noOfPgaes(response.data.totalPages);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllPaymethods= function() {
		paymethodService.getAllPaymethods($rootScope.loggedUsername).then(function(response) {
			$scope.paymethodsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.updatePaymethod = function(paymethod) {
		paymethod.adminUserName=$rootScope.loggedUsername;
		paymethodService.updatePaymethod(paymethod).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.PAYMETHOD_UPDATE_DATA_SUCCESS);
			   $state.go('main.paymethodList');
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
                
			
		});
	};
	/*$scope.deletePaymethod = function(id) {
		paymethodService.deletePaymethod(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
			$scope.getAllPaymethodsList();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	*/
	$scope.deletePaymethod=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  paymethodService.deletePaymethod(id).then(function(response) {
						 swal("Poof! It has been deleted!", {
						      icon: "success",
						    });
						 $scope.getAllPaymethodsList();
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
					$scope.getAllPaymethodsList();
				});
	  
	$scope.gotoList = function() {
		$state.go('main.paymethodList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addPaymethod');
	};
	$scope.gotoback = function() {
		$state.go('main.paymethodList');
	};
	$scope.gotoupdate = function(client) {
		$state.go('main.updatePaymethod',{
			paymethodObj:	client
		});
	};
}
angular.module("HealthApplication")
		.controller("paymethodCtrl", paymethodCtrl);
