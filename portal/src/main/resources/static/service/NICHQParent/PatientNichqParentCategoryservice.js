 function PatientNichqParentCategoryservice($http,PATIENT_MODULE_CONFIG){
	this.addNichqParenttemplate= function(nichqParentCategoryDto){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_NICHQ_PARENT_TEMPLATE(),
				nichqParentCategoryDto);
	}
	this.getAllNichqParentByPatientId= function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_PARENT_TEMPLATE_TO_THE_PARENT(id));
	}
	this.getNichqParentResults= function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_PARENT_RESULTS(id));
	}
	this.deleteParentResultByPatientId=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_PARENT_RESULT_BY_PATIENT_ID(id))
	}

}
angular.module("HealthApplication").service("PatientNichqParentCategoryservice",
		PatientNichqParentCategoryservice);