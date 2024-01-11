function nichqTeacherQuestionLookupCtrl($scope, $state, $stateParams,
		nichqTeacherQuestionLookupService, nichqteachercategorylookupservice,successMessageHandler) {
	$scope.nichqteacher1 = $stateParams.updateTeacher;

$scope.addNichqTeacherQuestionLookup = function() {
		$scope.NichqTeacherQuestionLookup = {
			"name" : $scope.name,
			"nichqTeacherAnswerlookups" : $scope.evalutions,
			"nichqTeacherCategoryLookup" : $scope.nichqTeacher
		}
		nichqTeacherQuestionLookupService.addNichqTeacherQuestionLookup($scope.NichqTeacherQuestionLookup)
				.then(function(response) {
					$state.go('main.teacherQuestionLookupList');
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.NICHQ_TEACHER_QUESTION_LOOKUP_ADDED);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.error($scope.message, 'Error');
				});
	};
	$scope.getNichqteachercategorylookup = function() {
		nichqteachercategorylookupservice.getNichqteachercategorylookup().then(function(response) {
			$scope.nichqteachercategorylookupList = response.data;
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
	$scope.getAllTeacherQuestionLookup = function() {
		nichqTeacherQuestionLookupService.getAllTeacherQuestionLookup().then(function(response) {
			$scope.nichqteacherQuestionlookupList = response.data;
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
	
	$scope.deleteNichqteacherQuestionlookup = function(nichqteacherQuestionlookupid) {
		nichqTeacherQuestionLookupService.deleteNichqteacherQuestionlookup(nichqteacherQuestionlookupid).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.NICHQ_TEACHER_QUESTION_LOOKUP_DELETED);
			$scope.getAllTeacherQuestionLookup();
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
	

	$scope.updateTeacherQuestionLookup = function( nichqteacher1) {
		nichqTeacherQuestionLookupService.updateTeacherQuestionLookup(nichqteacher1).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.NICHQ_TEACHER_QUESTION_LOOKUP_UPDATED);
                $state.go('main.teacherQuestionLookupList');
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
		$state.go('main.teacherQuestionLookupList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addTeacherQuestionLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.teacherQuestionLookupList');
	};
	$scope.gotoupdate = function(nichqteacher1) {
		$state.go('main.updateTeacherQuestionLookup',{
			updateTeacher : nichqteacher1
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

angular.module("HealthApplication").controller("nichqTeacherQuestionLookupCtrl",nichqTeacherQuestionLookupCtrl);