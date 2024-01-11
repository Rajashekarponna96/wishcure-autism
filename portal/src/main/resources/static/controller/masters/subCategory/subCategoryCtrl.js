function subCategoryCtrl($scope, $state, subCategoryService,
		categoryTypeService, departmentService, $rootScope, $stateParams, successMessageHandler) {

	$scope.subcategory = {};
	
	if ($stateParams.subcategoryObj != undefined) {
		$scope.subCategory = $stateParams.subcategoryObj;
	}

	$scope.addSubCategory = function(subCategory) {
		subCategoryService
				.addSubCategory(subCategory)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr
									.success(
											'',
											successMessageHandler.SUB_CATEGORY_ADD_DATA_SUCCESS);
							$state.go('main.subCategoryList');
						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
						});
	};

	$scope.getAllCategoryTypes = function() {
		categoryTypeService.getAllCategoryTypes().then(function(response) {
			$scope.categoryTypesList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	

	$scope.getAllSubCategories = function() {
		subCategoryService.getAllSubCategories().then(function(response) {
			$scope.subcategoryList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.updateSubCategoryType = function(subcategoryObj) {
		subCategoryService
				.updateSubCategoryType(subcategoryObj)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr
									.success(
											'',
											successMessageHandler.SUB_CATEGORY_TYPE_UPDATE_SUCCESSFULLY);
							$state.go('main.subCategoryList');
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							$scope.message1 = JSON.stringify(error.data.message
									.trim());
							$scope.message = $scope.message1.substring(1,
									$scope.message1.length - 1);
							toastr.error($scope.message, 'Error');

						});
	};

	$scope.deleteSubCategory = function(id) {
		subCategoryService
				.deleteSubCategory(id)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr
									.success(
											'',
											successMessageHandler.SUB_CATEGORY_TYPE_DELETED_SUCCESSFULLY);
							$scope.getAllSubCategories();
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							$scope.message1 = JSON.stringify(error.data.message
									.trim());
							$scope.message = $scope.message1.substring(1,
									$scope.message1.length - 1);
							toastr.error($scope.message, 'Error');
						});
	};
	
	
	$scope.getAllDepartments = function() {
		departmentService.getAlldepartments().then(function(response) {
			$scope.departmentsList = response.data;
		}, function(error) {
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
		});
	};

	$scope.gotoAddSub = function() {
		$state.go('main.addSubCategory');
	};
	$scope.gotoSubCatList = function() {
		$state.go('main.subCategoryList');
	};
	$scope.gotoSubCatback = function() {
		$state.go('main.subCategoryList');
	};

	$scope.gotoupdate = function(subcategory) {
		$state.go('main.updateSubCategory', {
			subcategoryObj : subcategory
		});
	};

}
angular.module("HealthApplication").controller("subCategoryCtrl",
		subCategoryCtrl);