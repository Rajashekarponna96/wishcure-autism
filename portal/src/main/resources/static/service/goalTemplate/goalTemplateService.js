function goalTemplateService(MODULE_CONFIG, $http) {
	this.addGoal = function(goal) {
		return $http.post(MODULE_CONFIG.ADDGOALTEMPLATE(),goal);
	};
	this.getAllgoals = function() {
		return $http.get(MODULE_CONFIG.GETALLGOALTEMPLATES());
	};
	this.getAllgoalsByPatient_Id = function(patientid) {
		return $http.get(MODULE_CONFIG.GET_ALL_GOAL_TEMPLATES_BY_PATIENT_ID(patientid));
	};
	this.updateGoal=function(goal){
		
		return $http.put(MODULE_CONFIG.UPDATEGOALTEMPLATE(),goal);
	};
	this.deleteGoal=function(id){
		return $http.delete(MODULE_CONFIG.DELETEGOALTEMPLATE(id));
	};
	this.getAllGoalsByStatus=function(status){
		return $http.get(MODULE_CONFIG.GET_ALL_GOALTEMPLATES_BY_STATUS(status));
	};
	this.addGoalCategoryLookup= function(goalCategoryLookup) {
		return 	$http.post(MODULE_CONFIG.ADD_GOAL_CATEGORYLOOKUP(),goalCategoryLookup);
	};
	this.getGoalCategoryLookups=function(){
			return $http.get(MODULE_CONFIG.GETALL_GOAL_CATEGORYLOOKUPS());
	};
	this.updateGoalCategory=function(goalCategory){
		
		return $http.put(MODULE_CONFIG.UPDATE_GOAL_CATEGORY(),goalCategory);
	};
	this.deleteGoalCategory=function(id){
		return $http.delete(MODULE_CONFIG.DELETE_GOAL_CATEGORY(id));
	};
}

angular.module('HealthApplication').service('goalTemplateService', goalTemplateService);