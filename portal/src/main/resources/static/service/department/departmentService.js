function departmentService(MODULE_CONFIG, $http, PATIENT_MODULE_CONFIG) {
	this.addDepartment = function(department) {
	return 	$http.post(MODULE_CONFIG.ADD_DEPARTMENT(),department);
	};
	this.getAlldepartments=function(){
		return $http.get(MODULE_CONFIG.GETALLDEPARTMENT());
	};
	
	this.getAllDepartmentsByRegistartiontype=function(id,username){
		return $http.get(MODULE_CONFIG.GETALLDEPARTMENTBYREGISTRATIONTYPE(id,username));
	};
	this.getAlldepartmentsList=function(username,page, size){
		return $http.get(MODULE_CONFIG.GETALLDEPARTMENTSLIST(username,page, size));
	};
	this.getAlldepartmentsListSearch=function(username,search,page, size){
		return $http.get(MODULE_CONFIG.GETALLDEPARTMENTSLIST_SEARCH(username,search,page, size));
	};
	this.updateDepartment=function(department){
		return $http.put(MODULE_CONFIG.UPDATEDEPARTMENT(),department);
	};
	this.deleteDepartment=function(id){
		return $http.delete(MODULE_CONFIG.DELETEDEPARTMENT(id));
	};
	
	this.getAllDepartmentsByCmpany=function(username){
		return $http.get(MODULE_CONFIG.GETALLDEPARTMENTSLISTBYCOMPANY(username));
	};
	
	this.getAllDepartmentsByCmpanyAndRole=function(username,roleId){
		return $http.get(MODULE_CONFIG.GETALLDEPARTMENTSLISTBYCOMPANYANDROLE(username,roleId));
	};
	/*this.findAllDepartments = function(page, size) {
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_DEPARTMENTS(page, size));
	};*/
	
	this.getAllPatientRegistrationTypesList=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENT_REEGISTRATION_TYPES());
	};
	
	
}
angular.module('HealthApplication').service('departmentService', departmentService);