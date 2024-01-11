function mchartService(PATIENT_MODULE_CONFIG, $http) {
	this.addMchartPatient = function(mchart) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_MCHART(),mchart);
	};
	this.getAllMchartsByPatientId=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MCHART_BY_ID(id));
	};
	this.getMchartCountResult=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MCHART_COUNT_BY_PATIENT_ID(id));
	};	
	
	getAllMchartQuestionsByPatient=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MCHART_BY_PATIENT_ID(id));
	};
}

angular.module('HealthApplication').service('mchartService', mchartService);