function evalutionService(MODULE_CONFIG, $http) {
	this.addQuestionEvalution = function(evalution) {
	return 	$http.post(MODULE_CONFIG.ADDQUESTION(),evalution);
	};
	this.getAllEvalutionCategories=function(){
		return $http.get(MODULE_CONFIG.GETALLQUESTIONS());
	};
	this.getAllListEvalutionCategories=function(){
		return $http.get(MODULE_CONFIG.GETALLLISTEVALUTIONCATEGORIES());
	};
	this.updateEvalutionCategory=function(evalutionCategory){
		return $http.put(MODULE_CONFIG.UPDATEQUESTION(),evalutionCategory);
	};
	this.deleteEvalutionCategory=function(id){
		return $http.delete(MODULE_CONFIG.DELETEQUESTION(id));
	};
	
}


angular.module('HealthApplication').service('evalutionService', evalutionService);