function isaaBehaviourQuestionLookUpService(MODULE_CONFIG,PATIENT_MODULE_CONFIG, $http) {
	this.addIsaaBehaviourQuestionLookup=function(iSAABehaviorQuestionLookup){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_CATEGORY_QUESTIONLOOKUP(),iSAABehaviorQuestionLookup);
	};
	this.getAllisaaBehaviourQuestionLookup=function(){
		return $http.get(PATIENT_MODULE_CONFIG.ALL_ISAAB_QUESTION_LOOKUPS());
	};
	this.updateIsaaBehaviourQuestionLookup=function(isaabQuestion){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_QUESTION_LOOKUP(),isaabQuestion);
	};
	this.deleteIsaaBehaviourQuestionlookup=function(isaaBehaviopurQuestionlookupid){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_CATEGORY_QUESTION_LOOKUP(isaaBehaviopurQuestionlookupid));
	};
	
	this.getAllIsaaBehavioralQuestionLookupsByCAtegoryLookup=function(categoryId){
		return $http.get(PATIENT_MODULE_CONFIG.ALL_ISAAB_QUESTION_LOOKUPS_BY_CATEGORYLOOKUP(categoryId));
	};
	
	this.getAllIsaaBehavioralQuestionsByPatientAndCategoryLookup=function(patientId,categoryId){
		return $http.get(PATIENT_MODULE_CONFIG.ALL_ISAAB_QUESTIONS_BY_PATIENT_AND_CATEGORYLOOKUP(patientId,categoryId));
	};
	
	
	this.assignIsaaBehavioralTemplateToPatient=function(iSAABehaviorObj){
		return $http.post(PATIENT_MODULE_CONFIG.ASSIGN_ISAABEHAVIORAL_TEMPLATE_TO_PATIENT(),iSAABehaviorObj);
	};
	
	this.getISAABehaviorReportByPatientId=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ISAA_BEHAVIOUR_REPORT_BY_PATIENT_ID(patientId));
	};
	
	this.isaaBehavioralResultBypatient=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ISAA_BEHAVIOUR_RESULT_BY_PATIENT(patientId));
	};
	
	
	
}

angular.module('HealthApplication').service('isaaBehaviourQuestionLookUpService', isaaBehaviourQuestionLookUpService);





