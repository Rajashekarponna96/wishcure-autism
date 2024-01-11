function csbsLookupservice(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	this.addcsbs = function(csbs) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_CSBS_CATEGORY(),csbs);
	};
	this.getAllcsbs=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_CSBS_CATEGORY());
	};
	this.getAllcsbsByCategoryStatus=function(categorystatus){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_CSBS_CATEGORY_BY_ID(categorystatus));
	};
	this.updatecsbs=function(csbs){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_CSBS_CATEGORY(),csbs);
	};
	this.deletecsbs=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_CSBS_CATEGORY(id));
	};
}
angular.module('HealthApplication').service('csbsLookupservice', csbsLookupservice);