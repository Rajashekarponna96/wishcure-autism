function nichqTeacherQuestionLookupService($http,PATIENT_MODULE_CONFIG){

this.addNichqTeacherQuestionLookup= function(NichqTeacherQuestionLookup){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_NICHQ_TEACHER_QUESTION_LOOKUP(),
				NichqTeacherQuestionLookup);
	}
this.getAllTeacherQuestionLookup= function(){
	return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_TEACHER_QUESTION_LOOKUP(),
			);
}
this.deleteNichqteacherQuestionlookup= function(id){
	return $http.delete(PATIENT_MODULE_CONFIG.DELETE_NICHQ_TEACHER_QUESTION_LOOKUP(id),
			);
}	
this.updateTeacherQuestionLookup= function(nichqteacher1){
	return $http.put(PATIENT_MODULE_CONFIG.UPDATE_NICHQ_TEACHER_QUESTION_LOOKUP(),
			nichqteacher1);
}	

}

angular.module("HealthApplication").service("nichqTeacherQuestionLookupService",nichqTeacherQuestionLookupService);