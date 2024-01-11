function evalutionQuestionService(MODULE_CONFIG, $http) {
	this.addEvalutionQuestion = function(evalutionQuestion) {
	return 	$http.post(MODULE_CONFIG.ADDEVALUTIONQUESTION(),evalutionQuestion);
	};
	this.getAllEvalutionQuestions=function(){
		return $http.get(MODULE_CONFIG.GETALLEVALUTIONQUESTIONS());
	};
	this.updateEvalutionQuestion=function(evalutionQuestion){
		return $http.put(MODULE_CONFIG.UPDATEEVALUTIONQUESTION(),evalutionQuestion);
	};
	this.deleteEvalutionQuestion=function(id){
		return $http.delete(MODULE_CONFIG.DELETEEVALUTIONQUESTION(id));
	};
	this.getAllEvalutionQuestionsByCategoryId=function(id){
		return $http.get(MODULE_CONFIG.GETALLEVALUTIONQUESTIONSBYCATEGORYID(id));
	};
	
	
	
	
	
	
}

angular.module('HealthApplication').service('evalutionQuestionService', evalutionQuestionService);