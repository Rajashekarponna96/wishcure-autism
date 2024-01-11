function nichqteachercategorylookupservice($http,PATIENT_MODULE_CONFIG){

this.addNichqteachercategorylookup= function(nichqteachercategorylookup){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_NICHQ_TEACHER_CATEGORY_LOOKUP(),
				nichqteachercategorylookup);
	}

this.getNichqteachercategorylookup= function(){
	return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_TEACHER_CATEGORY_LOOKUP());
}

this.deleteNichqteachercategorylookup= function(id){
	return $http.delete(PATIENT_MODULE_CONFIG.DELETE_NICHQ_TEACHER_CATEGORY_LOOKUP(id),
			);
}	
this.updateNichqteachercategorylookup= function(nichqteachercategorylookup1){
	return $http.put(PATIENT_MODULE_CONFIG.UPDATE_NICHQ_TEACHER_CATEGORY_LOOKUP(),
			nichqteachercategorylookup1);
}	

this.getAllNichqTeacherQuestionsByPatientAndCategory= function(patientId,categoryId){
	return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_TEACHER_QUESTIONS_BY_PATIENT_AND_CATEGORY(patientId,categoryId));
}


this.getAllNichqTeacherQuestionLookupsByCategory= function(categoryId){
	return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_TEACHER_QUESTION_LOOKUPS_BY_CATEGORY(categoryId));
}


this.assignNichqTeacherTemplateToPatient= function(nichqteachertemplate){
	return $http.post(PATIENT_MODULE_CONFIG.ASSIGN_NICHQ_TEACHER_TEMPLATE_TO_PATIENT(),nichqteachertemplate);
}

this.getNichqTeacherResultByPatientId= function(patientId){
	return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_TEACHER_TEMPLATE_RESULT_BY_PATIENT(patientId));
}

this.nichqTeacherReportBypatient= function(patientId){
	return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_TEACHER_TEMPLATE_REPORT_BY_PATIENT(patientId));
}





}

angular.module("HealthApplication").service("nichqteachercategorylookupservice",
		nichqteachercategorylookupservice);