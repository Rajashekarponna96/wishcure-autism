function screeningTestService(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	
	this.addParentScreeningTestData = function(userName,parentgoals) {
		return $http.post(PATIENT_MODULE_CONFIG.SAVE_SCREENING_TEST_CATEGORY(userName), parentgoals);
	};
	this.getAllParentQuestionCategorysScreeningTest = function(getScreeningTestDataDto) {
		return $http.post(PATIENT_MODULE_CONFIG.GET_ALL_SCREENING_TEST_BY_CATEGORY_STATUS(), getScreeningTestDataDto);
	};
	this.getAllCreatedDatesByScreeningTest = function(userName,categoryStatus) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_CREATED_DATES_BY_SCREENING_TEST(userName,categoryStatus));
	};
	this.getAllScreeningTestSocialGraph = function(userName) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SCREENING_TEST_SOCIAL_GRAPH(userName));
	};
	this.getAllScreeningTestImpairmentGraph = function(userName) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SCREENING_TEST_IMPAIRMENT_GRAPH(userName));
	};
	this.getAllScreeningTestBehaviourGraph = function(userName) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SCREENING_TEST_BEHAVIOUR_GRAPH(userName));
	};

	
}
angular.module('HealthApplication').service('screeningTestService', screeningTestService);