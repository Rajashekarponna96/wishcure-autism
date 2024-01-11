function careTakerMilestonesService(PATIENT_MODULE_CONFIG, $http) {
	
	this.saveCaretakerMilestones= function(categoryList,adminUserName ) {
	return 	$http.post(PATIENT_MODULE_CONFIG.SAVE_CARETAKER_MILESTONES(adminUserName),categoryList);
	};
	this.getAllCaretakerMilestones=function(id,adminUserName){
		return $http.post(PATIENT_MODULE_CONFIG.GETALL_CARETAKER_MILESTONES(id,adminUserName));
	};
	this.updateCaretakerMilestones=function(categoryList,adminUserName){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_CARETAKER_MILESTONES(adminUserName),categoryList);
	};
	this.getCaretakerAnswersCountForDashboard=function(adminUserName){
		return $http.get(PATIENT_MODULE_CONFIG.CARETAKER_MILESTONES_DASHBOARD(adminUserName));
	}
	
}
angular.module('HealthApplication').service('careTakerMilestonesService', careTakerMilestonesService);