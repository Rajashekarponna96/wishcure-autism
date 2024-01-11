function taxService(PATIENT_MODULE_CONFIG,MODULE_CONFIG, $http) {
	this.addtax = function(tax) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_TAX(),tax);
	};
	this.getAlltaxs=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLTAX());
	};
	this.getAlltaxList=function(email,page, size){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLTAXSLIST(email,page, size));
	};
	this.updatetax=function(tax){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATETAX(),tax);
	};
	this.deletetax=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETETAX(id));
	};
	
}
angular.module('HealthApplication').service('taxService', taxService);