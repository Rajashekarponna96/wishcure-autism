function parentInfoService(PATIENT_MODULE_CONFIG, $http) {
	
	/*this.getParentInfo= function(categoryLookup) {
	return 	$http.post(PATIENT_MODULE_CONFIG.GETALL_PARENTINFO(),categoryLookup);
	};*/
	this.getParentInfo=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALL_PARENTINFO());
	};
}
angular.module('HealthApplication').service('parentInfoService', parentInfoService);