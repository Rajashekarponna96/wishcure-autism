//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function evalutionQuestionCtrl($scope, $state, evalutionQuestionService,
		evalutionCategorService, $rootScope, $stateParams, successMessageHandler) {

	$scope.evalutionQuestion = $stateParams.evalutionQuestionObj;
	$scope.addEvalutionQuestion = function(evalutionQuestion) {
		evalutionQuestionService.addEvalutionQuestion(evalutionQuestion).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.EVALUTION_QUESTION_ADD_SUCCESS);
					$state.go('main.evalutionQuestionList');
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

	$scope.getAllEvalutionQuestions = function() {
		evalutionQuestionService.getAllEvalutionQuestions().then(
				function(response) {
					$scope.evalutionQuestionList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.updateEvalutionQuestion = function(evalutionQuestion) {
		evalutionQuestionService.updateEvalutionQuestion(evalutionQuestion)
				.then(function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('',successMessageHandler.EVALUTION_QUESTION_UPDATE_SUCCESS);
					$state.go('main.evalutionQuestionList');
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	$scope.deleteEvalutionQuestion = function(id) {
		evalutionQuestionService.deleteEvalutionQuestion(id).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.EVALUTION_QUESTION_DELETE_SUCCESS);
					$scope.getAllEvalutionQuestions();
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	$scope.gotoList = function() {
		$state.go('main.evalutionQuestionList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addEvalutionQuestion');
	};
	$scope.gotoback = function() {
		$state.go('main. evalutionQuestionList');
	};
	$scope.gotoupdate = function(evalutionQuestion) {
		$state.go('main.updateEvalutionQuestion', {
			evalutionQuestionObj : evalutionQuestion
		});
	};
}
angular.module("HealthApplication").controller("evalutionQuestionCtrl",
		evalutionQuestionCtrl);
