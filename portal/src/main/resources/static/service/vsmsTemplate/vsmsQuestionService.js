function vsmsQuestionService($http,PATIENT_MODULE_CONFIG) {
	this.addVSMSQuestion = function(vsmsQuestion) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_VSMS_QUESTION(),
				vsmsQuestion);
	}
	
	this.getVsmsQuestions=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_VSMS_QUESTIONS());
	}
	
	this.updateVSMSQuestion=function(vsmsQuestion){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_VSMS_QUESTION(),
				vsmsQuestion);
	}
	
	this.deleteVSMSQuestion=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_VSMS_QUESTION(id));
	}
	
	this.assignvsmsQuestionstoPatient = function(vsmsQuestionDto){
		return $http.post(PATIENT_MODULE_CONFIG.ASSIGN_VSMS_QUESTIONS_TO_PATIENT(),vsmsQuestionDto);
	}
	this.getAllPatientVsmsQuestionsByPatientId=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENT_VSMS_QUESTIONS_BYPATIENT_ID(id));
	}
	this.getVSMSReportData=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.VSMS_REPORT_DATA(id));
	};
	
}
angular.module('HealthApplication')
		.service("vsmsQuestionService", vsmsQuestionService);