function nichqParentGeneralService(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	
	this.addNichq = function(nichq) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_NICHQ_CATEGORY(),csbs);
	};
	this.getAllNichqs=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_CATEGORY());
	};
	this.getAllNichqByCategoryStatus=function(categorystatus){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_CATEGORY_BY_ID(categorystatus));
	};
	this.updateNichq=function(nichq){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_NICHQ_CATEGORY(), nichq);
	};
	this.deleteNichq=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_NICHQ_CATEGORY(id));
	};
}
angular.module('HealthApplication').service('nichqParentGeneralService', nichqParentGeneralService);