function companyTypeService(MODULE_CONFIG, $http) {
	this.addCompanyType = function(companytype) {
	return 	$http.post(MODULE_CONFIG.ADDCOMPANYTYPE(),companytype);
	};
	this.getAllCompaniesList=function(page, size){
		return $http.get(MODULE_CONFIG.GETALLCOMPANIESLIST(page, size));
	};
	this.getAllCompanies=function(){
		return $http.get(MODULE_CONFIG.GETALLCOMPANIES());
	};
	this.updateCompanyType=function(companytype){
		return $http.put(MODULE_CONFIG.UPDATECOMPANYTYPE(),companytype);
	};
	this.deleteCompanyType=function(id){
		return $http.delete(MODULE_CONFIG.DELETECOMPANYTYPE(id));
	};
	
}

angular.module('HealthApplication').service('companyTypeService', companyTypeService);