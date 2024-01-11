function teacherCategoryLoockupCtrl($scope, $state, csbsQuestionLookupService,csbsLookupservice, $rootScope,$stateParams) {
	
	$scope. csbs=$stateParams. csbsObj;
	$scope.csbsQuestion=$stateParams.csbsQuestionObj;
	$scope.answer=$stateParams.screeningAnswer;
	$scope.flag=false;
	
	$scope.addcsbsQuestion = function() {
		$scope.csbsQuestionLookup={
				"name":$scope.questionName,
				"csbsAnswerLookups":$scope.evalutions,
				"csbsCategoryLookup":$scope.csbs	
		}
		csbsQuestionLookupService.addcsbsQuestion($scope.csbsQuestionLookup).then(function(response) {
			$state.go('main.listCsbsQuestion');
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
	$scope.getAllcsbs = function() {
		csbsLookupservice.getAllcsbs().then(function(response) {
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
	$scope.getAllcsbsQuestions = function() {
		csbsQuestionLookupService.getAllcsbsQuestions().then(function(response) {
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
	
	$scope.updatecsbsQuestion = function( csbs) {
		csbsQuestionLookupService.updatecsbsQuestion( csbs).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Updated Successfully');
                $state.go('main.listCsbsQuestion');
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
	$scope.deletecsbs = function(id) {
		csbsQuestionLookupService.deletecsbs(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
                $scope.getAllcsbsQuestions();
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
		$state.go('main.teacherQuestionList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addTeacherQuestion');
	};
	$scope.gotoback = function() {
		$state.go('main.teacherQuestionList');
	};
	$scope.gotoupdate = function( csbs,scrAnswer) {
		$state.go('main.updateCsbsQuestion',{
			csbsQuestionObj:	 csbs,
			screeningAnswer:scrAnswer
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
angular.module("HealthApplication").controller("teacherCategoryLoockupCtrl", teacherCategoryLoockupCtrl);