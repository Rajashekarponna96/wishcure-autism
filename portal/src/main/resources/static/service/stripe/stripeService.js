function stripeService(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	
	this.getAllStripePackages = function() {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_STRIPE_PACKAGES());
	};
	this.getAllStripePlanByProductId = function(productId) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_STRIPE_PLAN_BY_PRODUCT_ID(productId));
	};
	this.createAccount=function(tokenDato){
		return $http.post(PATIENT_MODULE_CONFIG.CREATE_ACCOUNT_IN_STRIPE(),tokenDato);
	};
	this.createAchBankAccount=function(achbankinformationDetails){
		return $http.post(PATIENT_MODULE_CONFIG.CREATE_ACH_BANKACCOUNT_DETAILS(),achbankinformationDetails);
	};
}

angular.module("HealthApplication").service("stripeService", stripeService);
