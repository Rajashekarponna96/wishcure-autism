//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function forgotpasswordCtrl($scope, $state, forgotpasswordService, successMessageHandler) {
	
	$scope.$on('loader_show', function(event, args) {
		$rootScope.loader = true;
	});
	$scope.$on('loader_hide', function(event, args) {
		$rootScope.loader = false;
	});
	
	$scope.doForgotPassword = function(email) {
		forgotpasswordService.doForgotPassword(email).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 5000
			};
			toastr.success('', successMessageHandler.FORGOTPASSWORD_SENDTOEMAIL_SUCCESS);
			$state.go('login');
		}, function(error) {
			/*
			 * toastr.options = { closeButton: true, progressBar: true,
			 * showMethod: 'slideDown', timeOut: 5000 }; toastr.error('Admin',
			 * 'Enter Registered Email Id');
			 */
			$scope.message1=JSON.stringify(error.data.message.trim());
			$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
		});
	};
	

	
}
angular.module("HealthApplication").controller("forgotpasswordCtrl",
		forgotpasswordCtrl);
