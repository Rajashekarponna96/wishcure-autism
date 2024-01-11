function currencyService(PATIENT_MODULE_CONFIG,MODULE_CONFIG, $http) {
	this.addCurrency = function(currency) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_CURRENCY(),currency);
	};
	this.getAllCurrencys=function(email){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_CURRENCYS(email));
	};
	this.getAllCurrencyList=function(email,page, size){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_CURRENCY_LIST(email,page, size));
	};
	this.updateCurrency=function(currency){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_CURRENCY(),currency);
	};
	this.deleteCurrency=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_CURRENCY(id));
	};
	
}
angular.module('HealthApplication').service('currencyService', currencyService);