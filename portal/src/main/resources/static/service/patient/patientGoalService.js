function patientGoalService(PATIENT_MODULE_CONFIG, $http) {

	this.addPatientGoal = function(patientGoalObj, patient) {
		return $http.post(PATIENT_MODULE_CONFIG.SAVE_PATIENT_GOALS(patient.id),
				patientGoalObj);
	};

	this.findAllPatientGoalsByPatientIdAndCreatedDate = function(patientGoalDto) {
		return $http.post(PATIENT_MODULE_CONFIG
				.GET_PATIENT_GOALS_BY_PATIENTID_AND_DATE(), patientGoalDto);
	};

	this.getAllCratedDatesOfPatientGoals = function(patientId) {
		return $http.get(PATIENT_MODULE_CONFIG
				.GET_ALL_CREATEDDATES_OF_PATIENT_GOALS_BY_PATIENTID(patientId));
	};
	
	this.deletePatientGoailById=function(patientid,goalId){
		return $http.delete(PATIENT_MODULE_CONFIG
				.DELETE_PATIENT_GOALS_BY_ID(patientid,goalId));
	}
	
	this.getAllPatientGoalByPatientId = function(patientId) {
		return $http.get(PATIENT_MODULE_CONFIG
				.GET_ALL_PATIENT_GOALS_BY_PATIENT_ID(patientId));
	};
	

	/*
	 * this.findEvalutionSheetByStatusAndPatientId = function(patientId, staus) {
	 * return $http.get(PATIENT_MODULE_CONFIG
	 * .FIND_EVALUTIONSHEET_BY_PATIENTID_AND_STATUS(patientId, staus)); };
	 * this.getProgressSheetReportBy = function(patientId) { return
	 * $http.get(PATIENT_MODULE_CONFIG
	 * .GET_EVALUTION_SHEET_BY_PATIENTID_STATUS_INPROGRESSSHEET(patientId)); };
	 */
}

angular.module('HealthApplication').service('patientGoalService',
		patientGoalService);