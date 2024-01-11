function screeningTestLookupservice(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	this.addScreeningTest = function(screeningTest) {
	return 	$http.post(PATIENT_MODULE_CONFIG.SAVE_SCREENING_TEST_LOOKUP(),screeningTest);
	};
	this.getAllScreeningTest=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SCREENING_TEST_LOOKUP());
	};
	this.getAllScreeningTestByCategoryStatus=function(categorystatus){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SCREENING_TEST_LOOKUP_BY_CATEGORY_STATUS(categorystatus));
	};
	this.updateScreeningTest=function(screeningTest){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_SCREENING_TEST_LOOKUP(),screeningTest);
	};
	this.deleteScreeningTest=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_SCREENING_TEST_LOOKUP_BY_ID(id));
	};
}
angular.module('HealthApplication').service('screeningTestLookupservice', screeningTestLookupservice);