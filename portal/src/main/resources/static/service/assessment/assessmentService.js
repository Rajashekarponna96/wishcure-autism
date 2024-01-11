function assessmentService(PATIENT_MODULE_CONFIG, $http){
	
		this.getAllCategoryTypes=function(){
			return $http.get(PATIENT_MODULE_CONFIG.GETALLCATEGORYTYPES());
		};
		
		this.updateCategoryType=function(categoryType){
			return $http.put(PATIENT_MODULE_CONFIG.UPDATECATEGORYTYPE(), categoryType);
		};
		
		this.deleteCategoryType=function(id){
			return $http.delete(PATIENT_MODULE_CONFIG.DELETECATEGORYTYPE(id));
		};
		
		this.getAllCategoryTypeByStatus=function(){
			return $http.get(PATIENT_MODULE_CONFIG.GETALLCATEGORYTYPEBYSTATUS());
		};
		
}

angular.module('HealthApplication').service('assessmentService', assessmentService);