//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function packageMasterCtrl($scope, $state, packageMasterService, $rootScope,$stateParams, successMessageHandler) {
	$scope.page = 0;
	$scope.size = 5;
	
	$scope.packageMaster=$stateParams.packageMasterObj;
	$scope.addPackageMaster = function(packageMaster) {
		packageMasterService.addPackageMaster(packageMaster).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.PACKAGEMASTER_ADD_SUCCESS);
			$state.go('main.packageNameMasterlist');
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
	$scope.getAllPackageMAsters= function() {
		packageMasterService.getAllPackageMAsters($scope.page, $scope.size).then(function(response) {
			$scope.packageMastersList = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages-1;
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
	
	$scope.updatepackageNameMaster = function(packageMaster) {
		packageMasterService.updatepackageNameMaster(packageMaster).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.PACKAGEMASTER_UPDATE_SUCCESS);
			$state.go('main.packageNameMasterlist');
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
	$scope.deletepackageNameMaster = function(id) {
		packageMasterService.deletepackageNameMaster(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.PACKAGEMASTER_DELETE_SUCCESS);
			$scope.getAllPackageMAsters();
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
				toastr.error("Package Name is Already in Use, Can't Delete!", 'Error');	
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
					$scope.getAllPackageMAsters();
				});

	$scope.gotoList = function() {
		$state.go('main.packageNameMasterlist');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addPackageNameMaster');
	};
	$scope.gotoback = function() {
		$state.go('main.packageNameMasterlist');
	};
	$scope.gotoupdate = function(packageMaster) {
		$state.go('main.updatepackageNameMaster',{
			packageMasterObj:packageMaster
		});
	};
}
angular.module("HealthApplication")
		.controller("packageMasterCtrl", packageMasterCtrl);
