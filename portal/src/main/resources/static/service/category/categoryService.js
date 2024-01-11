function categoryService(MODULE_CONFIG, $http) {
	this.addCategory = function(category) {
	return 	$http.post(MODULE_CONFIG.ADDCATEGORY(),category);
	};
	this.getAllCategories=function(){
		return $http.get(MODULE_CONFIG.GETALLCATEGORIES());
	};
	this.updateCategory=function(category){
		return $http.put(MODULE_CONFIG.UPDATECATEGORY(),category);
	};
	this.deleteCategory=function(id){
		return $http.delete(MODULE_CONFIG.DELETECATEGORY(id));
	};	
}

angular.module('HealthApplication').service('categoryService', categoryService);