function speechtheraphyCategoryService(PATIENT_MODULE_CONFIG, $http) {
	this.addSpeechTheraphyCategory = function(speechTheraphyCategory) {
		return 	$http.post(PATIENT_MODULE_CONFIG.ADD_SPEECHTHERAPHY_CATEGORY(),speechTheraphyCategory);
		};
	
	this.getAllSpeechTheraphyCategorys = function() {
		return $http.get(PATIENT_MODULE_CONFIG
				.GET_ALL_SPEECHTHERAPHY_CATEGORYS());
		};
		this.updateSpeechTheraphyCategory = function(speechTheraphyCategory) {
			return $http.put(PATIENT_MODULE_CONFIG
					.UPDATE_SPEECHTHERAPHY_CATEGORY(),speechTheraphyCategory);
			};	
			this.deleteSpeechTheraphyCategory = function(id) {
				return $http.delete(PATIENT_MODULE_CONFIG
						.DELETE_SPEECHTHERAPHY_CATEGORY(id));
				};	
			
			
		
	
	
	
	
}

angular.module('HealthApplication').service('speechtheraphyCategoryService', speechtheraphyCategoryService);





