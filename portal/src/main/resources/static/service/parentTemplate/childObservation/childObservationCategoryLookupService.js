function childObservationCategoryLookupService(PATIENT_MODULE_CONFIG, $http) {
	this.addChildObservationCategoryLookup = function(childObservationCategoryLookup) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADDCHILDOBSERVATIONCATEGORYLOOKUP(),childObservationCategoryLookup);
	};
	this.getAllChildObservationCategoryLookups=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLCHILDOBSERVATIONCATEGORYLOOKUPS());
	};
	this.getAllChildObservationCategoryLookupsIds=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLCHILDOBSERVATIONCATEGORYLOOKUPSIDS());
	};
	/*this.getAlldepartmentsList=function(page, size){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLDEPARTMENTSLIST(page, size));
	};*/
	this.updateChildObservationCategoryLookup=function(childObservationCategoryLookup){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATECHILDOBSERVATIONCATEGORYLOOKUP(),childObservationCategoryLookup);
	};
	this.deleteChildObservationCategoryLookup=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETECHILDOBSERVATIONCATEGORYLOOKUP(id));
	};
	/*this.findAllDepartments = function(page, size) {
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_DEPARTMENTS(page, size));
	};*/
}
angular.module('HealthApplication').service('childObservationCategoryLookupService', childObservationCategoryLookupService);