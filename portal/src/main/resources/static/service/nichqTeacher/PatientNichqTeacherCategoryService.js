function PatientNichqTeacherCategoryservice($http,PATIENT_MODULE_CONFIG){
	this.addnichqteachertemplatetotheparent= function(PatientNichqTeacherCategoryDto){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_NICHQ_TEACHER_TEMPLATE_TO_THE_PARENT(),
				PatientNichqTeacherCategoryDto);
	};
	this.getAllNichqTeacherByPatientId= function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_TEACHER_TEMPLATE_TO_THE_PARENT(id));
	};
	this.getNichqTeacherResults= function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_TEACHER_RESULTS(id));
	}
	this.deleteTeacherResultByPatientId= function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_TEACHER_RESULT_BY_PATIENT_ID(id));
	}

}
angular.module("HealthApplication").service("PatientNichqTeacherCategoryservice",
		PatientNichqTeacherCategoryservice);