function speechTheraphyCategoryCtrl($scope, $state, $rootScope, $http,
		 successMessageHandler) {

	
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
	

	

	

	

}
angular.module("HealthApplication").controller("speechTheraphyCategoryCtrl",
		speechTheraphyCategoryCtrl);
