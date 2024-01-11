//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function careTakerCategoryLookupCtrl($scope, $state, $rootScope,careTakerCategoryLookupService, $stateParams) {
	$scope.categoryUpdate=$stateParams.categoryObj;
	$scope.getCaretakerCategoryLookups= function() {
		careTakerCategoryLookupService.getCaretakerCategoryLookups().then(function(response) {
			$scope.categoryLists = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.categoryLookup={};
	$scope.addCaretakerCategoryLookup = function() {
		careTakerCategoryLookupService.addCaretakerCategoryLookup($scope.categoryLookup).then(function(response) {
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 3000
				};
				toastr.success('', 'CaretakerCategoryLookup Added Successfully!');
				$scope.getCaretakerCategoryLookups();
		}, function(error) {
		});
	};
	
	$scope.updateCaretakerCategoryLookup = function(category) {
		careTakerCategoryLookupService.updateCaretakerCategoryLookup(category).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' CaretakerCategoryLookup Update Successfully');
			$state.go('main.CaretakerCategoryLookup');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteCaretakerCategoryLookup = function(id) {
		careTakerCategoryLookupService.deleteCaretakerCategoryLookup(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' CaretakerCategoryLookup Deleted Successfully');
                $scope.getCaretakerCategoryLookups();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {

		$state.go('main.CaretakerCategoryLookup');
	};
	$scope.gotoAdd = function() {
		$state.go('main.CaretakerCategoryLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.CaretakerCategoryLookup');
	};
	$scope.gotoupdate = function(category) {
		$state.go('main.updateCareTakerCategory', {
			categoryObj : category
		});
	};
	 
	
}
angular.module("HealthApplication").controller("careTakerCategoryLookupCtrl",
		careTakerCategoryLookupCtrl);
