function careTakerCategoryLookupService(PATIENT_MODULE_CONFIG, $http) {
	
	this.addCaretakerCategoryLookup= function(categoryLookup) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_CARETAKER_CATEGORYLOOKUP(),categoryLookup);
	};
	this.getCaretakerCategoryLookups=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALL_CATEGORYLOOKUPS());
	};
	this.updateCaretakerCategoryLookup=function(categoryLookup){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_CATEGORYLOOKUP(),categoryLookup);
	};
	this.deleteCaretakerCategoryLookup=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_CATEGORYLOOKUP(id));
	}
	
}
angular.module('HealthApplication').service('careTakerCategoryLookupService', careTakerCategoryLookupService);