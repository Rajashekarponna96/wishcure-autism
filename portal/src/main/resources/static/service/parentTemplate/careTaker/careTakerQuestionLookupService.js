function careTakerQuestionLookupService(PATIENT_MODULE_CONFIG, $http) {
	
	this.addCaretakerQuestionLookup= function(questionLookup) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_CARETAKER_QUESTIONLOOKUP(),questionLookup);
	};
	this.getCaretakerQuestionLookups=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALL_CARETAKER_QUESTIONLOOKUPS());
	};
	this.updateCaretakerQuestionLookup=function(questionLookup){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_CARETAKER_QUESTIONLOOKUP(),questionLookup);
	};
	this.deleteCaretakerQuestionLookup=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_CARETAKER_QUESTIONLOOKUP(id));
	};
	this.getQuestionsBasedOnCategoryStatus=function(id){
		return $http.post(PATIENT_MODULE_CONFIG.GET_QUESTIONS_BASED_ON_CATEGORYSTATUS(id));
	}
	
}
angular.module('HealthApplication').service('careTakerQuestionLookupService', careTakerQuestionLookupService);