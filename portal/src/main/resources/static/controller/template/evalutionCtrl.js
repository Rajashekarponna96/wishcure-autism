//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function evalutionCtrl($scope, $state, evalutionService,
		evalutionCategorService, $rootScope, $stateParams, successMessageHandler) {
	$scope.question = {
		questionName : null,
		answers : [],
		questionCategory : null
	};
	$scope.evalutionQuestion = $stateParams.evalutionQuestionObj;
	$scope.addQuestionEvalution = function() {
		$scope.question = {
				"questionName" : $scope.questionName,
				"answers" : $scope.evalutions,
				"questionCategory" : $scope.questionCategory
			};
		evalutionService.addQuestionEvalution($scope.question).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('',successMessageHandler.EVALUTION_ADD_SUCCESS);
					$state.go('main.evalutionQuestionList');
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	$scope.getAllEvalutionCategories = function() {
		evalutionCategorService.getAllEvalutionCategories().then(
				function(response) {
					$scope.evalutionCategoryAllList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.getAllListEvalutionCategories = function() {
		evalutionService.getAllListEvalutionCategories().then(function(response) {
			$scope.questionCategoryAllList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllEvalutionCategories = function() {
		evalutionCategorService.getAllEvalutionCategories().then(
				function(response) {
					$scope.evalutionCategoryList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.updateEvalutionQuestion = function(evalutionQuestion) {
		evalutionService.updateEvalutionQuestion(evalutionQuestion).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.EVALUTION_UPDATE_SUCCESS);
					$state.go('main.evalutionQuestionList');
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	$scope.deleteEvalutionQuestion = function(id) {
		evalutionService.deleteEvalutionQuestion(id).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.deleteEvalutionSuccessMessage);
			$scope.getAllEvalutionQuestions();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.evalutionQuestionList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.evaluation');
	};
	$scope.gotoback = function() {
		$state.go('main. evalutionQuestionList');
	};
	$scope.gotoupdate = function(evalutionQuestion) {
		$state.go('main.updateEvalutionQuestion', {
			evalutionQuestionObj : evalutionQuestion
		});
	};

	var counter = 0;
	$scope.evalutions = [ {
		shortAnswer : ''
	} ];

	$scope.newItem = function() {
		counter++;
		$scope.evalutions.push({
			shortAnswer : ''
		});
	}
	$scope.deleteItem = function() {
		counter--;
		$scope.evalutions.pop({
			shortAnswer : ''
		});

	}
	$scope.show = function(evalutions) {
		alert(evalutions);

	}
}
angular.module("HealthApplication").controller("evalutionCtrl", evalutionCtrl);
