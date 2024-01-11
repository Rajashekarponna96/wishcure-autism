function screeningTestQuestionLookupService(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	this.addScreeningTestQuestion = function(screeningTestQuestion) {
	return 	$http.post(PATIENT_MODULE_CONFIG.SAVE_SCREENING_TEST_QUESTION_LOOKUP(),screeningTestQuestion);
	};
	this.getAllScreeningTestQuestions=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SCREENING_TEST_QUESTION_LOOKUP());
	};
	this.updateScreeningTestQuestion=function(screeningTest){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_SCREENING_TEST_QUESTION_LOOKUP(),screeningTest);
	};
	this.deleteScreeningTest=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_SCREENING_TEST_QUESTION_LOOKUP_BY_ID(id));
	};
}
angular.module('HealthApplication').service('screeningTestQuestionLookupService', screeningTestQuestionLookupService);