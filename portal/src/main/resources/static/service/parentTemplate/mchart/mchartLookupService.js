function mchartLookupService(PATIENT_MODULE_CONFIG, $http) {
	this.addMchart = function(mchart) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_MCHART_LOOKUP(),mchart);
	};
	this.getAllMcharts=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MCHART_LOOKUP());
	};
	this.updateMchart=function(mchart){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_MCHART_LOOKUP(),mchart);
	};
	this.deleteMchart=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_MCHART_LOOKUP(id));
	};	
	this.deleteIsaaBehaviourResultByPatientId=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_MCHART_RESULT_BY_PATIENT_ID(id));
	};	
}

angular.module('HealthApplication').service('mchartLookupService', mchartLookupService);