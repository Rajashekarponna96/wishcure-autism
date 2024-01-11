function MentalScaleService(MODULE_CONFIG,PATIENT_MODULE_CONFIG,$http) {
	
	
	this.addMentalScale = function(Scale) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_MENTAL_SCALE(),Scale);
	};
	this.getAllMentalScale=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MENTAL_SCALE());
	};
	this.updateMentalScale=function(Scale){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_MENTAL_SCALE(),Scale);
	};
	this.updateMentalClusterScale=function(mentalScale){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_MENTAL_SCALE_WITH_CLUSTER(),mentalScale);
	};
	this.deletementalScale=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_MENTAL_SCALE(id));
	};
	this.assignMentalScalestoPatient=function(mentalScaleTemplateDto){
		return $http.post(PATIENT_MODULE_CONFIG.ASSIGN_MENTAL_SCALES_TO_PATIENT(),mentalScaleTemplateDto);
	};

}

angular.module('HealthApplication').service('MentalScaleService', MentalScaleService);