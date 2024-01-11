function nichqTemplateService(PATIENT_MODULE_CONFIG, $http) {
	
	this.addParentNichqData = function(userName, parentgoals) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_NICHQ_CATEGORY_TEMPLATE(userName), parentgoals);
	};
	this.getAllParentQuestionCategorysNichq = function(username, id) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_DATA(username, id));
	};
	
	this.nichqParentResultBypatient = function(patientId) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_PARENTRESULT_BY_PATIENT(patientId));
	};
	
}
angular.module('HealthApplication').service('nichqTemplateService', nichqTemplateService);