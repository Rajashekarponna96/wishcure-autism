function speechtheraphyQuestionService(PATIENT_MODULE_CONFIG, $http) {
	    this.addSpeechTheraphyQuestion= function(question) {
		return 	$http.post(PATIENT_MODULE_CONFIG.ADD_SPEECHTHERAPHY_QUESTION(),question);
		};
		this.getAllSpeechTheraphyQuestions=function(){
			return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SPEECHTHERAPHY_QUESTION());
			
		};
		this.updateSpeachTheraphyhQuestion=function(question){
			return $http.put(PATIENT_MODULE_CONFIG.UPDATE_SPEECHTHERAPHY_QUESTION(),question);
		};
		this.deleteSpeechTheraphyQuestion=function(id){
			return $http.delete(PATIENT_MODULE_CONFIG.DELETE_SPEECHTHERAPHY_QUESTION(id));
		};
	
}
angular.module('HealthApplication').service('speechtheraphyQuestionService', speechtheraphyQuestionService);




