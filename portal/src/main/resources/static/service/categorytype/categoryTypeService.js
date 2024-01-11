function categoryTypeService(PATIENT_MODULE_CONFIG, $http){
	this.addCategoryType = function(categoryType) {
		
		return 	$http.post(PATIENT_MODULE_CONFIG.ADDCATEGORYTYPE(), categoryType);
		};
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
			//alert("called service")
			return $http.get(PATIENT_MODULE_CONFIG.GETALLCATEGORYTYPEBYSTATUS());
		};
		this.getAllCategoriesByRegistartiontype=function(id){
			return $http.get(PATIENT_MODULE_CONFIG.GETALLCATEGORIESBYREGISTRATIONTYPE(id));
		};
}

angular.module('HealthApplication').service('categoryTypeService', categoryTypeService);