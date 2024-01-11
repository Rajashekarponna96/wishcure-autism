function NichqCategoryLookupService(PATIENT_MODULE_CONFIG, $http){
		this.addNichqCategoryLookup = function(nichq) {
			return 	$http.post(PATIENT_MODULE_CONFIG.ADD_NICHQ_CATEGORY_LOOKUP(), nichq);
		};
		this.getAllNichqCategoryLookupQuestions = function(){
			return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_CATEGORY_LOOKUP_QUESTIONS());
		};
		
		this.getAllNichqparentCategoryLookups = function(){
			return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_PARENT_CATEGORY_LOOKUPS());
		};
		this.updateNichqCategoryLookup = function(nichq){
			return $http.put(PATIENT_MODULE_CONFIG.UPDATE_NICHQ_CATEGORY_LOOKUP(), nichq);
		};
		this.deleteNichqCategoryLookup = function(id){
			return $http.delete(PATIENT_MODULE_CONFIG.DELETE_NICHQ_CATEGORY_LOOKUP(id));
		};
		this.getAllNichqByCategoryStatus=function(categorystatus){
			return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_CATEGORY_BY_ID(categorystatus));
		};
		this.getAllNichqParentCategoriesByPatient = function(patientId){
			return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_PARENT_CATEGORIES_BY_PATIENT(patientId));
		};
		
		this.getAllNichqParentQuestionsByPatientAndCategory = function(patientId,categoryId){
			return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_NICHQ_PARENT_QUESTIONS_BY_PATIENT_AND_CATEGORY(patientId,categoryId));
		};
		
}

angular.module('HealthApplication').service('NichqCategoryLookupService', NichqCategoryLookupService);