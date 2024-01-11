function evalutionSheetService(PATIENT_MODULE_CONFIG, $http) {

	this.addprogressSheet = function(evalution) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_PATIENT_EVALUTION_SHEET(),evalution);
	};
	this.addProgressNote = function(progressNote1) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_PATIENT_PROGRESS_SHEET(),progressNote1);
	};
	this.addExitNote = function(exitNote) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_PATIENT_PROGRESS_SHEET(),exitNote);
	};
	this.findEvalutionSheetByStatusAndPatientId = function(patientId, staus) {
		return $http.get(PATIENT_MODULE_CONFIG
				.FIND_EVALUTIONSHEET_BY_PATIENTID_AND_STATUS(patientId, staus));
	};
	this.getProgressSheetReportBy = function(patientId) {
		return $http.get(PATIENT_MODULE_CONFIG
				.GET_EVALUTION_SHEET_BY_PATIENTID_STATUS_INPROGRESSSHEET(patientId));
	};
	this.getExitSheetReportBy = function(patientId) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_EVALUTION_SHEET_BY_PATIENTID_STATUS_INEXITSHEET(patientId));
	};
	
	this.getAllCratedDatesOfProgressnote=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GETALL_CREATED_DATES_OF_PROGRESSNOTE(patientId));
	};
	
	this.getAllCratedDatesOfEvalutionSheet=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GETALL_CREATED_DATES_OF_EVALUTION_SHEET(patientId));
	};
	
	this.findAllPatientProgressSheetByPatientIdAndCreatedDate=function(progressSheetDateDto){
		return $http.post(PATIENT_MODULE_CONFIG.FIND_PROGRESS_SHEET_BY_PATIENT_IDAND_CREATEDDATE(),progressSheetDateDto);
	};
	this.findAllPatientEvalutionSheetByPatientIdAndCreatedDate=function(evalutionSheetDateDto){
		return $http.post(PATIENT_MODULE_CONFIG.FIND_EVALUTION_SHEET_BY_PATIENT_IDAND_CREATEDDATE(),evalutionSheetDateDto);
	};
	
	this.savePatientSpeechEvalution = function(patientId, speechEvaluation){
		return $http.post(PATIENT_MODULE_CONFIG.SAVE_PATIENT_SPEECH_EVALUTION_SHEET(patientId),speechEvaluation);
	};
	this.getAllPatientSpeechEvaluationByPatientId=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENT_SPEECH_EVALUTION_BY_ID(patientId));
	};
	
	this.savePatientBehaviourManagement = function(patientId, behaviourlManagement){
		return $http.post(PATIENT_MODULE_CONFIG.SAVE_PATIENT_BEHAVIOURAL_MANAGEMENT_SHEET(patientId),behaviourlManagement);
	};
	this.getAllPatientBehaviouralProgrammeByPatientId=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENT_BEHAVIOURAL_PROGRAMME_BY_ID(patientId));
	};

	
}

angular.module('HealthApplication').service('evalutionSheetService',
		evalutionSheetService);