function isaaBehaviourLookUpService(MODULE_CONFIG,PATIENT_MODULE_CONFIG, $http) {
	this.addCategoryLookup=function(categoryLookup){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_CATEGORYLOOKUP(),categoryLookup);
	};
	this.getAllCategorylookup=function(){
		return $http.get(PATIENT_MODULE_CONFIG.ALL_CATEGORY_LOOKUPS());
	};
	this.updateCategoryLookup=function(categoryLookup){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_CATEGORY_LOOKUP(),categoryLookup);
	};
	this.deleteCategoryLookup=function(categoryLookupId){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_CATEGORYLOOKUP(categoryLookupId));
	};
	this.getIsaaBehaviourResultByPatientId=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ISAA_BEHAVIOUR_RESULT_BY_PATIENT_ID(id));
	};
	this.deleteIsaaBehaviourResultByPatientId=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_ISAA_BEHAVIOUR_BY_PATIENT_ID(id));
	};
	
	this.getAllIsaaBehavioralCategorylookups=function(){
		return $http.get(PATIENT_MODULE_CONFIG.ALL_ISAABEHAVIORAL_CATEGORY_LOOKUPS());
	};
	
	
}

angular.module('HealthApplication').service('isaaBehaviourLookUpService', isaaBehaviourLookUpService);





