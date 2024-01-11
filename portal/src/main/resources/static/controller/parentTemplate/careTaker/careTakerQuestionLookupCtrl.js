//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function careTakerQuestionLookupCtrl($scope, $state, $rootScope,careTakerCategoryLookupService,careTakerQuestionLookupService, $stateParams) {
	$scope.questionUpdate=$stateParams.questionObj;
	$scope.answerupdate=$stateParams.answerObj;
	
	$scope.getCaretakerCategoryLookups= function() {
		careTakerCategoryLookupService.getCaretakerCategoryLookups().then(function(response) {
			$scope.categoryLists = response.data;
		}, function(error) {
			
		});
	};
	
	$scope.getQuestionsBasedOnCategoryStatus=function(category){
		careTakerQuestionLookupService.getQuestionsBasedOnCategoryStatus(category).then(function(response) {
			$scope.questnLists = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getCaretakerQuestionLookups= function() {
		careTakerQuestionLookupService.getCaretakerQuestionLookups().then(function(response) {
			$scope.questionLists = response.data;
		}, function(error) {
			
		});
	};
	
	$scope.addCaretakerQuestionLookup = function() {
		$scope.CaretakerQuestionLookup={
				"name":$scope.questionName,
				"careTakerAnswerLookups":$scope.evalutions,
				"careTakerCategoryLookup":$scope.questionCategory	
		}
		careTakerQuestionLookupService.addCaretakerQuestionLookup($scope.CaretakerQuestionLookup).then(function(response) {
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 3000
				};
				toastr.success('', 'CaretakerCategoryLookup Added Successfully!');
				$state.go('main.careTakerQuestionLookup_list');
		}, function(error) {
		});
	};
	
	$scope.updateCaretakerQuestionLookup = function(question) {
		careTakerQuestionLookupService.updateCaretakerQuestionLookup(question).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' CaretakerCategoryLookup Update Successfully');
			$state.go('main.careTakerQuestionLookup_list');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteCaretakerQuestionLookup = function(id) {
		careTakerQuestionLookupService.deleteCaretakerQuestionLookup(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' CaretakerCategoryLookup Deleted Successfully');
                $scope.getCaretakerQuestionLookups();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {

		$state.go('main.careTakerQuestionLookup_list');
	};
	$scope.gotoAdd = function() {
		$state.go('main.CareTakerQuestionLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.careTakerQuestionLookup_list');
	};
	$scope.gotoupdate = function(question,answer) {
		$state.go('main.updateCareTakerQuestionLookup', {
			questionObj : question ,
			answerObj:answer
		});
	};
	
	var counter = 0;
	$scope.evalutions = [ {
		name : ''
	} ];

	$scope.newItem = function() {
		counter++;
		$scope.evalutions.push({
			name : ''
		});
	}
	$scope.deleteItem = function() {
		counter--;
		$scope.evalutions.pop({
			shortAnswer : ''
		});

	}
	$scope.show = function(evalutions) {

	}
}
angular.module("HealthApplication").controller("careTakerQuestionLookupCtrl",
		careTakerQuestionLookupCtrl);
