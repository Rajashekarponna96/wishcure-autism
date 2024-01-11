function insuranceCtrl($scope, $state, insuranceService, $rootScope,$stateParams, $http,countryService, successMessageHandler) {
	
	$scope.page = 0;
	$scope.size = 5;
	$scope.insurance=$stateParams.insuranceObj;
	$scope.address1={};
	$scope.insuranceDataList=[];
	$scope.addInsurance = function(insurance) {
		var address1 = document.getElementById('street_number').value;
		var address2 = document.getElementById('route').value;
		var city = document.getElementById('locality').value;
		var state = document.getElementById('administrative_area_level_1').value;
		var country = document.getElementById('country').value;
		var zipcode = document.getElementById('postal_code').value;
		$scope.address1.address1 = address1;
		$scope.address1.address2 = address2;
		$scope.address1.city = city;
		$scope.address1.state = state;
		$scope.address1.country = country;
		$scope.address1.zipcode = zipcode;
		insurance.address1 = $scope.address1;
		insurance.adminUserName=$rootScope.loggedUsername;
		insuranceService.addInsurance(insurance).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.INSURANCE_ADD_SUCCESS);
			$state.go('main.insuranceList');
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
	$scope.getAllInsuranceList= function() {
		insuranceService.getAllInsuranceList($rootScope.loggedUsername,$scope.page, $scope.size).then(function(response) {
			$scope.insuranceListsPage = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages-1;
			$scope.noOfPgaes(response.data.totalPages);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllInsurances= function() {
		insuranceService.getAllInsurances().then(function(response) {
			$scope.insurancesList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.updateInsurance = function(insurance) {
		insuranceService.updateInsurance(insurance).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.INSURANCE_UPDATE_SUCCESS);
			$state.go('main.insuranceList');
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
	/*$scope.deleteInsurance = function(id) {
		insuranceService.deleteInsurance(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
			$scope.getAllInsuranceList();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	*/
	$scope.deleteInsurance=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  insuranceService.deleteInsurance(id).then(function(response) {
						 swal("Poof! It has been deleted!", {
						      icon: "success",
						    });
						 $scope.getAllInsuranceList();
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
	$scope.changeMobileNumber = function(mobileNumber) {
		if (mobileNumber == 0) {
			var number = "";
			return number;
		}
		var currentNumber = mobileNumber.toString();
		changedNumber = "(" + currentNumber.substring(0, 3) + ")"
				+ currentNumber.substring(3, 6) + "-"
				+ currentNumber.substring(6, 10);
		return changedNumber;
	}
	
	$scope.getAllInsurancesWithoutPagination=function(){
		insuranceService.getAllInsurancesWithoutPagination($rootScope.loggedUsername).then(function(response) {
			$scope.insuranceDataList=response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}
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
					$scope.getAllInsuranceList();
				});
	  
	$scope.gotoList = function() {
		$state.go('main.insuranceList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addInsurance');
	};
	$scope.gotoback = function() {
		$state.go('main.insuranceList');
	};
	$scope.gotoupdate = function(client) {
		$state.go('main.updateInsurance',{
			insuranceObj:	client
		});
	};
}
angular.module("HealthApplication")
		.controller("insuranceCtrl", insuranceCtrl);
