function schoolService(PATIENT_MODULE_CONFIG, $http) {
	this.addSchool = function(school) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_SCHOOL(),school);
	};
	/*this.getAllschools=function(){
		return $http.get(MODULE_CONFIG.GETALLSCHOOLS());
	};
	
	this.getAllschoolsByCompanyId=function(){
		return $http.get(MODULE_CONFIG.GETALLSCHOOLSBYCOMPANYID(id));
	};*/
	this.updateSchool=function(school){
		return $http.post(PATIENT_MODULE_CONFIG.UPDATE_SCHOOL(),school);
	};
	this.deleteSchool=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_SCHOOL(id));
	};
	
	
	//GET
	this.getAllSchoolsByCompanyUserNamePage=function(companyAdminUsername,page,size){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SCHOOLS_BY_COMPANY_USERNAME_PAGE(companyAdminUsername,page,size));
	};
	
	this.getAllSchoolsByCompany=function(companyAdminUsername){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SCHOOLS_BY_COMPANY(companyAdminUsername));
	};
	
	this.getAllSchoolsByCompanyUsername=function(companyAdminUsername){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_SCHOOLS_BY_COMPANY_USERNAME(companyAdminUsername));
	};
	
}

angular.module('HealthApplication').service('schoolService', schoolService);





