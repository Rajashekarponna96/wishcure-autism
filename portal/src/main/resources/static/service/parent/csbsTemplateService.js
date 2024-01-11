function csbsTemplateService(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	
	this.addParentcsbsData = function(userName,parentgoals) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_CSBS_CATEGORY_TEMPLATE(userName), parentgoals);
	};
	this.getAllParentQuestionCategoryscsbs = function( categoryId) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_CSBS_DATA( categoryId));
	};
	this.getAllcsbsQuestionLookupByCategoryId = function( categoryId) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_CSBS_QUESTIONS_BY_CATEGORY_ID( categoryId));
	};
}
angular.module('HealthApplication').service('csbsTemplateService', csbsTemplateService);