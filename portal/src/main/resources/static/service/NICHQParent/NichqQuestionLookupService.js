function NichqQuestionLookupService(PATIENT_MODULE_CONFIG, $http) {
	
	this.addNichqParentQuestionLookup = function(nichqParentQuestion) {
		return 	$http.post(PATIENT_MODULE_CONFIG.ADD_NICHQ_PARENT_QUESTION_LOOKUP(), nichqParentQuestion);
	};
	this.getAllNichqParentQuestionsLookup = function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_PARENT_QUESTION_LOOKUPS());
	};
	this.updateNichqParentQuestionLookup = function(nichq){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_NICHQ_PARENT_QUESTION_LOOKUP(), nichq);
	};
	this.deleteNichqParentQuestionLookup = function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_NICHQ_PARENT_QUESTION_LOOKUP(id));
	};
	
	this.getAllNichqparentQuestionLookupsByCategory = function(categoryId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_PARENT_QUESTION_LOOKUPS_BY_CATEGORY(categoryId));
	};
	
	this.assignNichqParentTemplateToPatient = function(nichqParentTemplateObj) {
		return 	$http.post(PATIENT_MODULE_CONFIG.ASSIGN_NICHQ_PARENT_TEMPLATE_TO_PATIENT(),nichqParentTemplateObj);
	};
	
	this.nichqParentTemplateResultByPatient = function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_PARENT_TEMPLATE_RESULT_BY_PATIENT(patientId));
	};
	
	
}
angular.module('HealthApplication').service('NichqQuestionLookupService', NichqQuestionLookupService);