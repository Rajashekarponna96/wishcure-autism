function regionalCenterLookupService(PATIENT_MODULE_CONFIG, $http) {
	this.addRC = function(rc) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_REGIONAL_CENTER_LOOKUP(),rc);
	};
	this.getAllRC=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALL_REGIONAL_CENTER_LOOKUP());
	};
	this.updateRC=function(rc){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_REGIONAL_CENTER_LOOKUP(),rc);
	};
	this.deleteRC=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_REGIONAL_CENTER_LOOKUP(id));
	};
	
}

angular.module('HealthApplication').service('regionalCenterLookupService', regionalCenterLookupService);