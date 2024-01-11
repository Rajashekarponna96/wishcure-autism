function speechTheraphyQuestionCtrl($scope, $state, $rootScope, $http,
		$stateParams, speechtheraphyQuestionService,
		speechtheraphyCategoryService, successMessageHandler) {
	$scope.question = $stateParams.question;

	$scope.speechTheraphyQuestion = {
		questionName : null,
		answerHeaders : [],
		speechTemplateCategory : null
	};

	$scope.addSPeechTheraphyTemplateQuestion = function() {
		$scope.speechTheraphyQuestion = {
			questionName : $scope.questionName,
			answerHeaders : $scope.evalutions,
			speechTemplateCategory : $scope.stTemplateCategory
		};
		alert(JSON.stringify($scope.speechTheraphyQuestion));
		speechtheraphyQuestionService.addSpeechTheraphyQuestion(
				$scope.speechTheraphyQuestion).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.SPEECH_THERAPHY_QUESTION_ADD_SUCCESS);
			$state.go('main.evalutionQuestionList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllSpeechTheraphyQuestions = function() {
		speechtheraphyQuestionService.getAllSpeechTheraphyQuestions().then(
				function(response) {
					$scope.questionList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.updateSpeachTheraphyhQuestion = function(question) {
		speechtheraphyQuestionService
				.updateSpeachTheraphyhQuestion(question).then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr.success('', successMessageHandler.SPEECH_THERAPHY_QUESTION_UPDATE_SUCCESS);
							$state.go('main.evalutionQuestionList');
						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
						});
	};
	$scope.deleteSpeechTheraphyQuestion = function(id) {
		speechtheraphyQuestionService.deleteSpeechTheraphyQuestion(id).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.SPEECH_THERAPHY_QUESTION_DELETE_SUCCESS);
					$scope.getAllEvalutionQuestions();
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	$scope.gotoList = function() {
		$state.go('main.evalutionQuestionList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addSpeechTheraphyQuestionTemplate');
	};
	$scope.gotoback = function() {
		$state.go('main.speechTheraphyQuestionTemplateList');
	};
	$scope.gotoupdate = function(question) {
		$state.go('main.updateSpeechTheraphyQuestionTemplate', {
			question : question
		});
	};

	$scope.getAllSpeechTheraphyCategorys = function() {
		speechtheraphyCategoryService.getAllSpeechTheraphyCategorys().then(
				function(response) {
					$scope.speechTherahpyTeplateCategoriesList = response.data;
				}, function(error) {

				})
	};

	// for list of input text boxes
	var counter = 0;
	$scope.evalutions = [ {
		answerName : ''
	} ];

	$scope.newItem = function() {
		counter++;
		$scope.evalutions.push({
			answerName : ''
		});
	}
	$scope.deleteItem = function() {
		counter--;
		$scope.evalutions.pop({
			answerName : ''
		});

	}

}
angular.module("HealthApplication").controller("speechTheraphyQuestionCtrl",
		speechTheraphyQuestionCtrl);