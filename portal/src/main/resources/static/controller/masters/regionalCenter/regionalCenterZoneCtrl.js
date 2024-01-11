//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function regionalCenterZoneCtrl($scope, $state, regionalCenterZoneService, $rootScope,$stateParams, successMessageHandler) {
	
	$scope.page = 0;
	$scope.size = 5;
	
	
	$scope.rcz=$stateParams.obj;
	$scope.addRCZ = function(rcz) {
		regionalCenterZoneService.addRCZ(rcz).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.REGIONALCENTERZONE_SAVE_DATA_SUCCESS);
			
			$state.go('main.regionalCenterZoneList');
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

	$scope.getAllRCZ = function() {
		regionalCenterZoneService.getAllRCZ().then(function(response) {
			$scope.rczLists = response.data;
		}, function(error) {
		});
	};
	
	$scope.updateRCZ = function(rcz) {
		regionalCenterZoneService.updateRCZ(rcz).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('','Updated Successfully');
			$state.go('main.regionalCenterZoneList');
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
	$scope.deleteRCZ = function(id) {
		regionalCenterZoneService.deleteRCZ(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.REGIONALCENTERZONE_DELETE_DATA_SUCCESS);
			$scope.getAllRCZ();
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
				  regionalCenterZoneService.deleteRCZ(id).then(function(response) {
						 swal("Poof! Your RCZ has been deleted!", {
						      icon: "success",
						    });
						 $scope.getAllRCZ();
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
						 swal($scope.message , {
						      icon: "info",
						    });
						
					});
				
			
			    swal("Proof! Your RCZ has been deleted!", {
			      icon: "success",
			    });
			   
			  } else {
			    swal("Your RCZ is safe!");
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
					$scope.getAllRCZ();
				});

		
		
	$scope.gotoList = function() {
		$state.go('main.regionalCenterZoneList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addRegionalCenterZone');
	};
	$scope.gotoback = function() {
		$state.go('main.regionalCenterZoneList');
	};
	$scope.gotoupdate = function(rcz) {
		$state.go('main.updateRegionalCenterZone',{
			obj:rcz
		});
	};
}
angular.module("HealthApplication")
		.controller("regionalCenterZoneCtrl", regionalCenterZoneCtrl);
