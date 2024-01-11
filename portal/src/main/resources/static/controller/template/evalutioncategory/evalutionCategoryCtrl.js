//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function evalutionCategoryCtrl($scope, $state, evalutionCategorService, $rootScope,$stateParams, successMessageHandler) {
	
	$scope. evalutionCategory=$stateParams. evalutionCategoryObj;
	$scope.addEvalutionCategory = function( evalutionCategory) {
		evalutionCategorService.addEvalutionCategory( evalutionCategory).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.EVALUTION_CATEGORY_ADD_SUCCESS);
			$state.go('main.evalutionCategoryList');
		}, function(error) {
			//$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllEvalutionCategories = function() {
		evalutionCategorService.getAllCategory().then(function(response) {
			$scope.evalutionCategoryList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.updateEvalutionCategory = function( evalutionCategory) {
		evalutionCategorService.updateEvalutionCategory( evalutionCategory).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.CATEGORY_TYPE_UPDATE_SUCCESSFULLY);
			$state.go('main.evalutionCategoryList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteEvalutionCategory = function(id) {
		evalutionCategorService.deleteEvalutionCategory(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.EVALUATION_CATEGORY_DELETE_SUCCESS);
			$scope.getAllEvalutionCategories();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.evalutionCategoryList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addEvalutionCategory');
	};
	$scope.gotoback = function() {
		$state.go('main.evalutionCategoryList');
	};
	$scope.gotoupdate = function( evalutionCategory) {
		$state.go('main.updateEvalutionCategory',{
			 evalutionCategoryObj:	 evalutionCategory
		});
	};
}
angular.module("HealthApplication")
		.controller("evalutionCategoryCtrl", evalutionCategoryCtrl);
