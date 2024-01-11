function speechTheraphyCategoryCtrl($scope, $state, $rootScope, $http,
		$stateParams, speechtheraphyCategoryService, successMessageHandler) {

	$scope.speechTherahpyTeplateCategoriesList = [];
	if ($stateParams.stCategory != undefined) {
		$scope.speechTheraphyCategory = $stateParams.stCategory;
	}
	$scope.gotoList = function() {
		$state.go('main.speechTheraphyCategoryTemplateList');
	};
	$scope.gotoadd = function() {
		$state.go('main.addSpeechTheraphyCategoryTemplate');
	};
	$scope.gotoupdate = function(speechTheraphyCategory) {
		$state.go('main.updateSpeechTheraphyCategoryTemplate', {
			stCategory : speechTheraphyCategory
		});
	};
	$scope.addSpeechTheraphyCategory = function(speechTheraphyCategory) {
		speechtheraphyCategoryService
				.addSpeechTheraphyCategory(speechTheraphyCategory)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							toastr
									.success('',
											successMessageHandler.SPEECH_THERAPHY_CATEGORY_SAVE_SUCCESS);
							$state
									.go('main.speechTheraphyCategoryTemplateList');
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							$scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.error($scope.message, 'Error');

						})
	};

	$scope.getAllSpeechTheraphyCategorys = function() {
		speechtheraphyCategoryService.getAllSpeechTheraphyCategorys().then(
				function(response) {
					$scope.speechTherahpyTeplateCategoriesList = response.data;
				}, function(error) {

				})
	};

	$scope.updateSpeechTheraphyCategory = function(speechTheraphyCategory) {
		speechtheraphyCategoryService
				.updateSpeechTheraphyCategory(speechTheraphyCategory)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							toastr
									.success('',
											successMessageHandler.SPEECH_THERAPHY_CATEGORY_UPDATE_SUCCESS);
							$state
									.go('main.speechTheraphyCategoryTemplateList');
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							$scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.error($scope.message, 'Error');
						})
	};

	$scope.deleteSpeechTheraphyCategory = function(id) {
		speechtheraphyCategoryService
				.deleteSpeechTheraphyCategory(id)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							toastr
									.success('',
											successMessageHandler.SPEECH_THERAPHY_CATEGORY_DELETE_SUCCESS);
							$scope.getAllSpeechTheraphyCategorys();
						}, function(error) {
							toastr.options = {
				                    closeButton: true,
				                    progressBar: true,
				                    showMethod: 'slideDown',
				                    positionClass : "toast-top-right",
				                    timeOut: 2000
				                };
							$scope.message = JSON.stringify(error.data.message.trim());
							toastr.error($scope.message, 'Error');
						})
	}

}
angular.module("HealthApplication").controller("speechTheraphyCategoryCtrl",
		speechTheraphyCategoryCtrl);
