function speechTheraphyService(PATIENT_MODULE_CONFIG, $http) {

	this.getSpeechTemplateBasedOnStatus = function(status) {
		return $http.get(PATIENT_MODULE_CONFIG
				.GET_SPEECHTHERAPHY_TEMPLATE_BASEDON_STATUS(status));
	};
	this.assignSpeechTemplatetoPatient = function(patientId,evalutionStatus, speechTemplates) {
		return $http.post(PATIENT_MODULE_CONFIG
				.ASSIGN_SPEECHTHERAPHY_TEMPLATE_TO_PATIENT(patientId,evalutionStatus),
				speechTemplates);
	};
}

angular.module('HealthApplication').service('speechTheraphyService',
		speechTheraphyService);