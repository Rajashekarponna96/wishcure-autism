//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function regionalCenterLookupCtrl($scope, $state, regionalCenterZoneService,regionalCenterLookupService,$rootScope,$stateParams, successMessageHandler) {
	
	$scope.page = 0;
	$scope.size = 5;
	$scope.rc={};
	$scope.reginoalCenterAddress={};
	$scope.rc=$stateParams.obj;
	
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
	
	//get data from regional center zone lookup
	$scope.getAllRCZ = function() {
		regionalCenterZoneService.getAllRCZByStatus().then(function(response) {
			$scope.rczLists = response.data;
		}, function(error) {
		});
	};
	
	$scope.addRC = function(rc) {

		var address1 = document.getElementById('street_number').value;
		var address2 = document.getElementById('route').value;
		var city = document.getElementById('locality').value;
		var state = document.getElementById('administrative_area_level_1').value;
		var country = document.getElementById('country').value;
		var zipcode = document.getElementById('postal_code').value;
		$scope.reginoalCenterAddress.address1=address1;
		$scope.reginoalCenterAddress.address2=address2;
		$scope.reginoalCenterAddress.city=city;
		$scope.reginoalCenterAddress.state=state;
		$scope.reginoalCenterAddress.country=country;
		$scope.reginoalCenterAddress.zipcode=zipcode;
		$scope.rc.reginoalCenterAddress=$scope.reginoalCenterAddress;
		
		regionalCenterLookupService.addRC(rc).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.REGIONALCENTERLOOKUP_SAVE_DATA_SUCCESS);
			
			$state.go('main.regionalCenterLookupList');
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

	$scope.getAllRC = function() {
		regionalCenterLookupService.getAllRC().then(function(response) {
			$scope.rcLists = response.data;
		}, function(error) {
		});
	};
	
	$scope.updateRC = function(rc) {
		regionalCenterLookupService.updateRC(rc).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.REGIONALCENTERLOOKUP_UPDATE_DATA_SUCCESS);
			$state.go('main.regionalCenterLookupList');
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
	$scope.deleteRC = function(id) {
		regionalCenterLookupService.deleteRC(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.REGIONALCENTERLOOKUP_DELETE_DATA_SUCCESS);
			$scope.getAllRC();
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
	
	$scope.deletealert=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  regionalCenterLookupService.deleteRC(id).then(function(response) {
						 swal("Poof! Your RC has been deleted!", {
						      icon: "success",
						    });
						 $scope.getAllRC();
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
						 swal($scope.message , {
						      icon: "info",
						    });
						
					});
				
			
			    swal("Proof! Your RC has been deleted!", {
			      icon: "success",
			    });
			   
			  } else {
			    swal("Your RC is safe!");
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
					$scope.getAllRC();
				});

		
		
	$scope.gotoList = function() {
		$state.go('main.regionalCenterLookupList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addRegionalCenterLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.regionalCenterLookupList');
	};
	$scope.gotoupdate = function(rcz) {
		$state.go('main.updateRegionalCenterLookup',{
			obj:rcz
		});
	};
}
angular.module("HealthApplication")
		.controller("regionalCenterLookupCtrl", regionalCenterLookupCtrl);
