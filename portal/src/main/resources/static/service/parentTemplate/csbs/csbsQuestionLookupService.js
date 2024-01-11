function csbsQuestionLookupService(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	this.addcsbsQuestion = function(csbsQuestion) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_CSBS_QUESTION(),csbsQuestion);
	};
	this.getAllcsbsQuestions=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_CSBS_QUESTIONS());
	};
	this.updatecsbsQuestion=function(csbs){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_CSBS_QUESTIONS(),csbs);
	};
	this.deletecsbs=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_CSBS_QUESTION(id));
	};
}
angular.module('HealthApplication').service('csbsQuestionLookupService', csbsQuestionLookupService);