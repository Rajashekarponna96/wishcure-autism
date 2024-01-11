function insuranceLookupService(MODULE_CONFIG,PATIENT_MODULE_CONFIG, $http) {
	this.addInsuranceLookup=function(insuranceLookup){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_INSURANCELOOKUP(),insuranceLookup);
	};
	this.getAllInsuranceLookups=function(){
		return $http.get(PATIENT_MODULE_CONFIG.ALL_INSURANCE_LOOKUPS());
	};
	this.updateInsuranceLookup=function(insurancelookup){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_INSURANCE_LOOKUP(),insurancelookup);
	};
	this.delteInsuranceLookup=function(insuranceLookupId){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_INSURANCELOOKUP(insuranceLookupId));
	};
}

angular.module('HealthApplication').service('insuranceLookupService', insuranceLookupService);





