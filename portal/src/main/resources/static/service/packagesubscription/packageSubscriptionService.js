function packageSubscriptionService(MODULE_CONFIG, $http) {
	this.addPackageSubscription = function(packageSubscription) {
	return 	$http.post(MODULE_CONFIG.ADDPACKAGESUBSCRIPTION(),packageSubscription);
	};
	this.getAllPackageSubscriptions=function(){
		return $http.get(MODULE_CONFIG.GETALLPACKAGESUBSCRIPTIONS());
	};
	this.updatePackageSubscription=function(packageSubscription){
		return $http.put(MODULE_CONFIG.UPDATEPACKAGESUBSCRIPTION(),packageSubscription);
	};
	this.deletePackageSubscription=function(id){
		return $http.delete(MODULE_CONFIG.DELETEPACKAGESUBSCRIPTION(id));
	};
}

angular.module('HealthApplication').service('packageSubscriptionService', packageSubscriptionService);