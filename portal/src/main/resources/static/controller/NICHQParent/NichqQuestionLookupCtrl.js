function NichqQuestionLookupCtrl($scope, $state, NichqQuestionLookupService,
		NichqCategoryLookupService, $rootScope, $stateParams,successMessageHandler) {
$scope.nichq=$stateParams.updateNichqParentQuestionLookupObj;
	$scope.addNichqParentQuestionLookup = function() {
		$scope.NICHQParentQuestionLookup = {
			"name" : $scope.questionName,
			"nichqParentAnswerLookup" : $scope.evalutions,
			"nichqParentCategoryLookup" : $scope.nichq
		}
		NichqQuestionLookupService.addNichqParentQuestionLookup(
				$scope.NICHQParentQuestionLookup).then(function(response) {
			$state.go('main.nichqParentQuestionLookupList');
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.NICHQ_PARENT_QUESTION_LOOKUP_ADDED);
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

	$scope.getAllNichqCategoryQuestions = function() {
		NichqCategoryLookupService.getAllNichqCategoryLookupQuestions().then(
				function(response) {
					$scope.nichqParentCategoryQuestionList = response.data;
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
	}

	$scope.getAllNichqParentQuestionsLookup = function() {
		NichqQuestionLookupService.getAllNichqParentQuestionsLookup().then(
				function(response) {
					$scope.screeninhTestQuestionsList = response.data;
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

	$scope.updateNichqParentQuestionLookup = function(nichq) {
		NichqQuestionLookupService.updateNichqParentQuestionLookup(nichq).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.NICHQ_PARENT_QUESTION_LOOKUP_UPDATED);
					$state.go('main.nichqParentQuestionLookupList');
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
	$scope.deleteNichqParentQuestionLookup = function(id) {
		NichqQuestionLookupService.deleteNichqParentQuestionLookup(id).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('',successMessageHandler.NICHQ_PARENT_QUESTION_LOOKUP_DELETED);
					$scope.getAllNichqParentQuestionsLookup();
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
	$scope.gotoList = function() {
		$state.go('main.nichqParentQuestionLookupList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addNichqParentQuestionLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.nichqParentQuestionLookupList');
	};
	$scope.gotoupdate = function(nichq) {
		$state.go('main.updateNichqQuestionLookup', {
			updateNichqParentQuestionLookupObj : nichq,
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
angular.module("HealthApplication").controller("NichqQuestionLookupCtrl",
		NichqQuestionLookupCtrl);