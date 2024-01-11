function categoryTypeCtrl(categoryTypeService, departmentService, successMessageHandler, $scope, $state, $rootScope, $stateParams){
		
	$scope.cType = $stateParams.categoryTypeObj;
	/*if($stateParams.categoryTypeObj != undefined){
			
	}	*/	
	$scope.addCategoryType = function(categoryType) {
		
		categoryTypeService.addCategoryType(categoryType).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.CATEGORY_TYPE_ADDED_SUCCESSFULLY);
			$state.go('main.categoryTypeList');
		}, function(error) {
			
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message = $scope.message1.substring(1, $scope.message1.length - 1);
			toastr.error($scope.message, 'Error');
			
		});
	};
	
	$scope.getAllCategoryTypes = function() {
		categoryTypeService.getAllCategoryTypes().then(function(response) {
			$scope.categoryTypesList = response.data;
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message = $scope.message1.substring(1, $scope.message1.length - 1);
			toastr.error($scope.message, 'Error');
		});
	};
	
	$scope.updateCategoryType = function(categoryType) {
		categoryTypeService.updateCategoryType(categoryType).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.CATEGORY_TYPE_UPDATE_SUCCESSFULLY);
			$state.go('main.categoryTypeList');
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message = $scope.message1.substring(1, $scope.message1.length - 1);
			toastr.error($scope.message, 'Error');
			
		});
	};
	
	$scope.deleteCategoryType = function(id) {
		categoryTypeService.deleteCategoryType(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.CATEGORY_TYPE_DELETED_SUCCESSFULLY);
			$scope.getAllCategoryTypes();
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message = $scope.message1.substring(1, $scope.message1.length - 1);
			toastr.error($scope.message, 'Error');
		});
	};
	
	$scope.getAllCategoryTypesListSearch = function(search){
		categoryTypeService.getAllCategoryTypesSearch(search, $scope.page, $scope.size).then(
				function(response) {
					$scope.categoryTypesList = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				},function(error) {
					$scope.message1 = JSON.stringify(error.data.message.trim());
					$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
				});
	}
	
	$scope.getAllPatientRegistrationTypesList = function() {
		departmentService.getAllPatientRegistrationTypesList().then(
				function(response) {
					$scope.patientRegistrationTypes = response.data;
					
				}, function(error) {
					$scope.message1 = JSON.stringify(error.data.message.trim());
					$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
				});
	};
	
	// Pagination logic

	$scope.noOfPgaes = function(totalpages) {

		$scope.pageList = [];
		for (i = 0; i < totalpages; i++) {
			$scope.pageList.push(i);
		}

	};

	$scope.pageChanged = function(page) {
		$scope.page = page;
	};

	$scope.sizeChanged = function(size) {
		$scope.size = size;
	};
	$scope.firstPage = function() {
		$scope.page = 0;
	};

	$scope.lastPage = function() {
		$scope.page = $scope.totalPages;
	};

	$scope.previousPage = function() {
		if ($scope.page > 0) {
			$scope.page = $scope.page - 1;
		}
	};
	$scope.nextPage = function() {
		if ($scope.page < $scope.totalPages) {
			$scope.page = $scope.page + 1;
		}
	};
	$scope.$watchGroup([ 'size', 'page' ], function(oldSize, oldPage) {
		$scope.getAllCategoryTypes();
	});
	
	$scope.gotoList = function() {
		$state.go('main.categoryTypeList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addCategoryType');
	};
	$scope.gotoback = function() {
		$state.go('main.categoryTypeList');
	};
	$scope.gotoupdate = function(cType) {		
		$state.go('main.updateCategoryType',{
			categoryTypeObj: cType
		});
	};
	$scope.deletealert = function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this Category Type!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				$scope.deleteCategoryType(id);
			    swal("Category Type Deleted Successfully!", {
			      icon: "success",
			    });
			  } else {
			    swal("Your category type is safe!");
			  }
			});
		};
	}


angular.module("HealthApplication").controller("categoryTypeCtrl", categoryTypeCtrl);


