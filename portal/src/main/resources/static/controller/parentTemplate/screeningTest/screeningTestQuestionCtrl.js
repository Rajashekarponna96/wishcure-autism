function screeningTestQuestionCtrl($scope, $state, screeningTestQuestionLookupService,screeningTestLookupservice, $rootScope,$stateParams) {
	
	$scope. screeningTest=$stateParams. screeningTestObj;
	$scope.screeningTestQuestion=$stateParams.screeningTestQuestionObj;
	$scope.answer=$stateParams.screeningAnswer;
	$scope.flag=false;
	
	$scope.addScreeningTestQuestion = function() {
		$scope.ScreeningTestQuestionLookup={
				"name":$scope.questionName,
				"screeningTestAnswerLookups":$scope.evalutions,
				"screeningTestCategoryLookup":$scope.screeningTest	
		}
		screeningTestQuestionLookupService.addScreeningTestQuestion($scope.ScreeningTestQuestionLookup).then(function(response) {
			$state.go('main.addScreeningTestQuestionList');
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Added Successfully');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.error($scope.message,'Error');
		});
	};
	$scope.getAllScreeningTest = function() {
		screeningTestLookupservice.getAllScreeningTest().then(function(response) {
			$scope.screeninhTestList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.error($scope.message,'Error');
		});
	};
	$scope.getAllScreeningTestQuestions = function() {
		screeningTestQuestionLookupService.getAllScreeningTestQuestions().then(function(response) {
			$scope.screeninhTestQuestionsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.error($scope.message,'Error');
		});
	};
	
	$scope.updateScreeningTestQuestion = function( screeningTest) {
		screeningTestQuestionLookupService.updateScreeningTestQuestion( screeningTest).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Updated Successfully');
                $state.go('main.addScreeningTestQuestionList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.error($scope.message,'Error');
		});
	};
	$scope.deleteScreeningTest = function(id) {
		screeningTestQuestionLookupService.deleteScreeningTest(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
                $scope.getAllScreeningTestQuestions();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.error($scope.message,'Error');
		});
	};
	$scope.gotoList = function() {
		$state.go('main.addScreeningTestQuestionList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addScreeningTestQuestion');
	};
	$scope.gotoback = function() {
		$state.go('main.addScreeningTestQuestion');
	};
	$scope.gotoupdate = function( screeningTest,scrAnswer) {
		$state.go('main.updateScreeningTestQuestion',{
			screeningTestQuestionObj:	 screeningTest,
			screeningAnswer         :    scrAnswer
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
			name : ''
		});

	}
}
angular.module("HealthApplication")
		.controller("screeningTestQuestionCtrl", screeningTestQuestionCtrl);