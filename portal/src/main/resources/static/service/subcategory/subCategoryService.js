function subCategoryService(MODULE_CONFIG, $http) {
	
	this.addSubCategory = function(subCategory) {
		return 	$http.post(MODULE_CONFIG.ADDSUBCATEGORY(),subCategory);
		};
		
	this.getAllSubCategories=function(){
			return $http.get(MODULE_CONFIG.GETALLSUBCATEGORIES());
		};
		
		this.updateSubCategoryType=function(subcategoryObj){
			return $http.put(MODULE_CONFIG.UPDATESUBCATEGORYTYPE(), subcategoryObj);
		};
		
		this.deleteSubCategory=function(id){
			return $http.delete(MODULE_CONFIG.DELETESUBCATEGORY(id));
		};
		
		this.getAllSubCategoriesByCategorytype=function(id){
			return $http.get(MODULE_CONFIG.GETALLSUBCATEGORIESBYCATEGORYTYPE(id));
		};
		
	
}

angular.module('HealthApplication').service('subCategoryService', subCategoryService);