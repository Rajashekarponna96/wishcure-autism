//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function caretakerMilestonesCtrl($scope, $state, $rootScope,careTakerCategoryLookupService,careTakerQuestionLookupService,careTakerMilestonesService,careTakerQuestionLookupService, $stateParams) {
	$scope.currentCategory={}
	$scope.getQuestionsBasedOnCategoryStatus=function(id){
		$scope.adminUserName = $rootScope.loggedUsername;
		careTakerQuestionLookupService.getQuestionsBasedOnCategoryStatus(id).then(function(response){
		$scope.categorylookupList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getCaretakerCategoryLookups= function() {
		careTakerCategoryLookupService.getCaretakerCategoryLookups().then(function(response) {
			$scope.categorylookupLists = response.data;
			$scope.getAllCaretakerMilestones($scope.categorylookupLists[0]);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	//add Category
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
				$state.go('main.careTakerMilestones');
				$scope.getCaretakerCategoryLookups();
		}, function(error) {
		});
	};
	
	
	//Lookups
	$scope.getQuestionsBasedOnCategoryStatus=function(id){
		$scope.adminUserName = $rootScope.loggedUsername;
		careTakerQuestionLookupService.getQuestionsBasedOnCategoryStatus(id).then(function(response){
		$scope.categorylookupList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.goToBack = function() {
		$state.go('main.careTakerMilestones2');
	};
	$scope.gotomilstones = function() {
		$state.go('main.careTakerMilestones');
	};
	
	//General
	$scope.getAllCaretakerMilestones=function(category){
		
		$scope.currentCategory=category;
		$scope.adminUserName = $rootScope.loggedUsername;
		careTakerMilestonesService.getAllCaretakerMilestones(category.id,$scope.adminUserName).then(function(response) {
			$scope.categoryGeneralList = response.data;
		if($scope.categoryGeneralList.length<1){
			$scope.getQuestionsBasedOnCategoryStatus(category.id);
		}
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	
	$scope.saveCaretakerMilestones = function(categoryList) {
		$scope.adminUserName = $rootScope.loggedUsername;
		careTakerMilestonesService.saveCaretakerMilestones(categoryList,$scope.adminUserName ).then(function(response) {
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 3000
				};
				toastr.success('', 'CaretakerCategoryLookup Added Successfully!');
			
		}, function(error) {
		});
	};
	
	$scope.updateCaretakerMilestones = function(categoryList) {
		$scope.adminUserName = $rootScope.loggedUsername;
		careTakerMilestonesService.updateCaretakerMilestones(categoryList,$scope.adminUserName ).then(function(response) {
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 3000
				};
				toastr.success('', 'CaretakerCategory Updated Successfully!');
			
		}, function(error) {
		});
	};
	
	$scope.getCaretakerAnswersCountForDashboard= function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		careTakerMilestonesService.getCaretakerAnswersCountForDashboard($scope.adminUserName).then(function(response) {
			$scope.careTakerDashboardLists = response.data;
			
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.addCaretakerQuestionLookup = function(goal) {
		$scope.CaretakerQuestionLookup={
				"name":goal.questionName,
				"careTakerAnswerLookups":$scope.evalutions,
				"careTakerCategoryLookup":$scope.currentCategory	
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
				$scope.getAllCaretakerMilestones($scope.currentCategory);
		}, function(error) {
		});
	};
	
	
	var counter = 0;
	$scope.evalutions = [ {
		name : 'some'
	} ];

	$scope.newItem = function() {
		counter++;
		$scope.evalutions.push({
			name : 'some'
		});
	}
	$scope.deleteItem = function() {
		counter--;
		$scope.evalutions.pop({
			shortAnswer : 'some'
		});

	}
	$scope.show = function(evalutions) {

	}
}
angular.module("HealthApplication").controller("caretakerMilestonesCtrl",
		caretakerMilestonesCtrl);




