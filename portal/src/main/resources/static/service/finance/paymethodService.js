function paymethodService(PATIENT_MODULE_CONFIG,MODULE_CONFIG, $http) {
	this.addPaymethod = function(paymetod) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_PAYMETHOD(),paymetod);
	};
	this.getAllPaymethods=function(email){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLPAYMETHODS(email));
	};
	this.getAllPaymethodsList=function(email,page, size){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PAYMETHODS_LIST(email,page, size));
	};
	this.updatePaymethod=function(paymetod){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_PAYMETHOD(),paymetod);
	};
	this.deletePaymethod=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_PAYMETHOD(id));
	};
	
}
angular.module('HealthApplication').service('paymethodService', paymethodService);