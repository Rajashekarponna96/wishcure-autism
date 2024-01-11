function insuranceService(PATIENT_MODULE_CONFIG,MODULE_CONFIG, $http) {
	this.addInsurance = function(insurance) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_INSURANCE(),insurance);
	};
	this.getAllInsurances=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_INSURANCES());
	};
	this.getAllInsuranceList=function(email,page, size){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_INSURANCE_LIST(email,page, size));
	};
	this.updateInsurance=function(insurance){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_INSURANCE(),insurance);
	};
	this.deleteInsurance=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_INSURANCE(id));
	};
	this.getAllInsurancesWithoutPagination=function(email){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_INSURANCES_WITHOUT_PAGINATION(email));
	};
	this.getAllInsurancesByCompany=function(companyAdminUsername){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_INSURANCES_BY_COMPANY(companyAdminUsername));
	};
}
angular.module('HealthApplication').service('insuranceService', insuranceService);