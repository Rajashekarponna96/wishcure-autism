function changepasswordService(MODULE_CONFIG, $http) {
	this.doChangePassword = function(changepassword) {
		return $http.post(MODULE_CONFIG.CHANGEPASSWORD(), changepassword);
	};
}

angular.module('HealthApplication').service('changepasswordService',
		changepasswordService);