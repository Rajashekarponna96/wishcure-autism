function evalutionCategorService(MODULE_CONFIG,PATIENT_MODULE_CONFIG,$http) {
	this.addEvalutionCategory = function(evalutionCategory) {
	return 	$http.post(MODULE_CONFIG.ADDEVALUTIONCATEGORY(),evalutionCategory);
	};
	this.getAllCategory=function(){
		return $http.get(MODULE_CONFIG.GETALLCATEGORY());
	};
	this.updateEvalutionCategory=function(evalutionCategory){
		return $http.put(MODULE_CONFIG.UPDATEEVALUTIONCATEGORY(),evalutionCategory);
	};
	this.deleteEvalutionCategory=function(id){
		return $http.delete(MODULE_CONFIG.DELETEEVALUTIONCATEGORY(id));
	};
	
	this.getAnswers=function(id){
		return $http.get(MODULE_CONFIG.GETALLANSWERSBYQUESIONID(id));
	};
	
	this.getAllEvalutionCategories=function(){
		return $http.get(MODULE_CONFIG.GETALLEVALUTIONCATEGORIES());
	};
	this.getAllCategorysByPatientId=function(patientid){
		return $http.get(MODULE_CONFIG.GET_ALL_QUESTION_CATEGORYS_BY_PATIENT_ID(patientid));
	};
	
}

angular.module('HealthApplication').service('evalutionCategorService', evalutionCategorService);