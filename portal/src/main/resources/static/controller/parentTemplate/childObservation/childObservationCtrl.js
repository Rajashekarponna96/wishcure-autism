//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function childObservationCategoryLookupCtrl($scope, $state, childObservationCategoryLookupService, $rootScope,$stateParams) {
	
	$scope.page = 0;
	$scope.size = 5;
	
	$scope.childObservationCategoryLookup=$stateParams.childObservationCategoryLookupObject;
	$scope.addChildObservationCategoryLookup = function(childObservationCategoryLookup) {
		childObservationCategoryLookupService.addChildObservationCategoryLookup(childObservationCategoryLookup).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Added Successfully');
			//$state.go('main.addChildObservationCategoryLookup');
			$scope.childObservationCategoryLookup={};
			$scope.getAllChildObservationCategoryLookups();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
/*	$scope.getAllDepartmentsList= function() {
		departmentService.getAlldepartmentsList($scope.page, $scope.size).then(function(response) {
			$scope.departmentsLists = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages-1;
			$scope.noOfPgaes(response.data.totalPages);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};*/
	
	$scope.getAllChildObservationCategoryLookups= function() {
		childObservationCategoryLookupService.getAllChildObservationCategoryLookups().then(function(response) {
			$scope.childObservationCategoryLookupsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	$scope.updateChildObservationCategoryLookup = function(childObservationCategoryLookup) {
		alert("Update method");
		childObservationCategoryLookupService.updateChildObservationCategoryLookup(childObservationCategoryLookup).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Update Successfully');
			$state.go('main.addChildObservationCategoryLookup');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteChildObservationCategoryLookup = function(id) {
		alert("delete Called");
		childObservationCategoryLookupService.deleteChildObservationCategoryLookup(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
			$scope.getAllChildObservationCategoryLookups();
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
				//	$scope.getAllDepartmentsList();
				});
	  
	$scope.gotoList = function() {
		$state.go('main.addChildObservationCategoryLookup');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addChildObservationCategoryLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.addChildObservationCategoryLookup');
	};
	$scope.gotoupdate = function(childObservationCategoryLookup) {
		alert("gotoupdate");
		$state.go('main.updateChildObservationCategoryLookup',{
			childObservationCategoryLookupObject:	childObservationCategoryLookup
		});
	};
}
angular.module("HealthApplication")
		.controller("childObservationCategoryLookupCtrl", childObservationCategoryLookupCtrl);
