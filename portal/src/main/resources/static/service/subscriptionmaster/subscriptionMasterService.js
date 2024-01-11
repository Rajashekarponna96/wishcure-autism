function subscriptionMasterService(MODULE_CONFIG, $http) {
	this.addSubscriptionMaster = function(subscription) {
	return 	$http.post(MODULE_CONFIG.ADDSUBSCRIPTIONMASTER(),subscription);
	};
	this.getAllSubscriptionMasters=function(page, size){
		return $http.get(MODULE_CONFIG.GETALLSUBSCRIPTIONMASTERS(page, size));
	};
	this.updateSubscriptionMaster=function(subscription){
		return $http.put(MODULE_CONFIG.UPDATESUBSCRIPTIONMASTER(),subscription);
	};
	this.deleteSubscriptionMaster=function(id){
		return $http.delete(MODULE_CONFIG.DELETESUBSCRIPTIONMASTER(id));
	};
	
	this.getAllSubscriptionMastersList=function(){
		return $http.get(MODULE_CONFIG.GETALLSUBSCRIPTIONMASTERSLIST());
	};
	
}

angular.module('HealthApplication').service('subscriptionMasterService', subscriptionMasterService);